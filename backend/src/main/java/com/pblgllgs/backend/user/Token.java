package com.pblgllgs.backend.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*
 *
 * @author pblgl
 * Created on 10-04-2024
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

//    @ManyToOne
//    @JoinColumn(
//            name = "userId",
//            referencedColumnName = "id",
//            nullable = false
//    )
//    private User user;
}
