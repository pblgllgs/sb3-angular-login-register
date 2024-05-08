package com.pblgllgs.backend.feedback;
/*
 *
 * @author pblgl
 * Created on 29-04-2024
 *
 */

import com.pblgllgs.backend.book.Book;
import com.pblgllgs.backend.book.BookRepository;
import com.pblgllgs.backend.common.PageResponse;
import com.pblgllgs.backend.exception.OperationNotPermittedException;
import com.pblgllgs.backend.exception.ResourceNotFoundException;
import com.pblgllgs.backend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    public Integer save(FeedbackRequest feedbackRequest, Authentication connectedUser) {
        Book book = bookRepository.findById(feedbackRequest.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("No book found with id: " + feedbackRequest.bookId()));
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException(
                    "You can not give a feedback for an archived or not shareable book"
            );
        }
        if (Objects.equals(book.getCreatedBy(), connectedUser.getName())) {
            throw new OperationNotPermittedException(
                    "You can not give a feedback to your own book"
            );
        }
        Feedback feedback = feedbackMapper.toFeedback(feedbackRequest);
        Feedback saveFeedback = feedbackRepository.save(feedback);
        return saveFeedback.getId();
    }

    public PageResponse<FeedBackResponse> findAllFeedbacksByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = (User) connectedUser.getPrincipal();
        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId, pageable);
        List<FeedBackResponse> feedBackResponses =  feedbacks.stream()
                .map(feedback -> feedbackMapper.toFeedbackResponse(feedback, user.getId()))
                .toList();
        return new PageResponse<>(
                feedBackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
