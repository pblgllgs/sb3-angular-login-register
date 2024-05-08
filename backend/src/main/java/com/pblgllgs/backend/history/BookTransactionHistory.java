package com.pblgllgs.backend.history;
/*
 *
 * @author pblgl
 * Created on 26-04-2024
 *
 */

import com.pblgllgs.backend.book.Book;
import com.pblgllgs.backend.common.BaseEntity;
import com.pblgllgs.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "book_transaction_history")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookTransactionHistory extends BaseEntity {

//    @ManyToOne
//    @JoinColumn(
//            name = "user_id"
//    )
//    private User user;
    @Column(name = "user_id")
    private String userId;
    @ManyToOne
    @JoinColumn(
            name = "book_id"
    )
    private Book book;

    private boolean returned;
    private boolean returnApproved;

}
