package com.pblgllgs.backend.config;
/*
 *
 * @author pblgl
 * Created on 26-04-2024
 *
 */

import com.pblgllgs.backend.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<String> {


    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        if (
                authentication == null ||
                        !authentication.isAuthenticated() ||
                        authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }
        return Optional.ofNullable(authentication.getName());
    }
}
