package com.pblgllgs.backend.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pblgllgs.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * @author pblgl
 * Created on 10-04-2024
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "userId",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "user_id_user_role")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "roleId",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "role_id_user_role")
            )
    )
    private List<User> users = new ArrayList<>();
}
