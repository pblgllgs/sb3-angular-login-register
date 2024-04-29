package com.pblgllgs.backend.feedback;
/*
 *
 * @author pblgl
 * Created on 29-04-2024
 *
 */

import com.pblgllgs.backend.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
@Tag(name = "Feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Integer> saveFeedback(
            @Valid @RequestBody FeedbackRequest feedbackRequest,
            Authentication connectedUser
    ) {
    return new ResponseEntity<>(feedbackService.save(feedbackRequest, connectedUser), HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<PageResponse<FeedBackResponse>> findById(
            @PathVariable("bookId") Integer bookId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return new ResponseEntity<>(feedbackService.findAllFeedbacksByBook(bookId, page, size, connectedUser), HttpStatus.OK);
    }
}
