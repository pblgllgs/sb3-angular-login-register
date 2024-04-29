package com.pblgllgs.backend.feedback;
/*
 *
 * @author pblgl
 * Created on 29-04-2024
 *
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedBackResponse {
    private Double note;
    private String comment;
    private boolean ownFeedback;
}
