package com.ms001.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity  //annotated a class with @EnableWebSecurity, the configurations defined within that class will affect the entire project.
public class SecurityConfiguration {
}
