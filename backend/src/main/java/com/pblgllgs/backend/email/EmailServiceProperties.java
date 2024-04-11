package com.pblgllgs.backend.email;
/*
 *
 * @author pblgl
 * Created on 11-04-2024
 *
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application.mailing.frontend")
public class EmailServiceProperties {
    private String activationUrl;
}
