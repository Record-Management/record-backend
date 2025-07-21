package com.recordmanagement.habitlog.config.swagger;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApiConfig - Swagger UI 및 문서화 설정 클래스
 *
 * SpringDoc(OpenAPI 3)를 사용하여 API 문서 자동 생성
 */
@Configuration
public class OpenApiConfig {

    /**
     * OpenAPI 기본 정보 설정
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HabitLog API 문서")
                        .description("기록 습관 앱의 백엔드 API 명세서입니다.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("전우선")
                                .email("wooxexn@gmail.com")
                        )
                );
    }
}
