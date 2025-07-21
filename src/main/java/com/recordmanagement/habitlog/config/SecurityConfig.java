package com.recordmanagement.habitlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 기본 설정 클래스
 *
 * - 현재는 개발 초기 단계로 모든 요청을 인증 없이 허용
 * - 추후 JWT 기반 인증 체계로 교체 예정
 */
@Configuration
public class SecurityConfig {

    /**
     * SecurityFilterChain 설정
     *
     * @param http HttpSecurity 설정 객체
     * @return SecurityFilterChain
     * @throws Exception 예외 발생 시 던짐
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보안 기능 비활성화
                .csrf(AbstractHttpConfigurer::disable)

                // 모든 요청에 대해 인증 없이 허용
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
