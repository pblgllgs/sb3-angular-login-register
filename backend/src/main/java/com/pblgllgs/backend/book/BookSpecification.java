package com.pblgllgs.backend.book;
/*
 *
 * @author pblgl
 * Created on 26-04-2024
 *
 */

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withOwnerId(Integer ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
    }
}
