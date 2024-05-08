package com.pblgllgs.backend.book;
/*
 *
 * @author pblgl
 * Created on 26-04-2024
 *
 */

import com.pblgllgs.backend.feedback.Feedback;
import com.pblgllgs.backend.common.BaseEntity;
import com.pblgllgs.backend.history.BookTransactionHistory;
import com.pblgllgs.backend.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends BaseEntity {
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

//    @ManyToOne
//    @JoinColumn(
//            name = "owner_id"
//    )
//    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @Transient
    public double getRate(){
        if (feedbacks == null || feedbacks.isEmpty()){
            return 0.0;
        }
        var rate = this.feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        return Math.round(rate * 10.0) / 10.0;
    }
}