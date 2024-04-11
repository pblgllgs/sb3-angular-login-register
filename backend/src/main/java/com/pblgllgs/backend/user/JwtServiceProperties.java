package com.pblgllgs.backend.user;
/*
 *
 * @author pblgl
 * Created on 10-04-2024
 *
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtServiceProperties {
    private long expiration;
    private String secret;
}
