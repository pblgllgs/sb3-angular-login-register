package com.pblgllgs.backend.book;
/*
 *
 * @author pblgl
 * Created on 26-04-2024
 *
 */

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withOwnerId(String ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdBy"), ownerId);
    }
}
