// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
package com.gitlab.csrfservlet;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;

@Configuration
@EnableWebSecurity
@Profile("csrf-configurer")
public class SpringCSRFDisabled extends WebSecurityConfigurerAdapter {

    //Starting from Spring Security 4.x, the CSRF protection is enabled by default.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CsrfConfigurer<HttpSecurity> csrf = http.csrf();
        // ruleid: java_csrf_rule-SpringCSRFDisabled
        csrf.disable();

        // ruleid: java_csrf_rule-SpringCSRFDisabled
        http.csrf().requireCsrfProtectionMatcher(new RegexRequestMatcher("/csrf/submit", "POST")).disable();

        // ruleid: java_csrf_rule-SpringCSRFDisabled
        http.csrf().requireCsrfProtectionMatcher(new RegexRequestMatcher("/csrf/submit", "POST"));
    }
}