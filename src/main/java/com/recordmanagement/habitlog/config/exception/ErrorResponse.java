package com.recordmanagement.habitlog.config.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * ErrorResponse - API 예외 발생 시 클라이언트에게 전달되는 에러 응답 DTO
 *
 * HTTP 상태 코드, 에러 코드, 메시지, 발생 시간을 포함한다.
 * 정적 팩토리 메서드 of()를 통해 객체를 생성하며, 불변 구조로 설계되었다.
 * Swagger 문서 자동화를 위해 @Schema 어노테이션이 사용된다.
 */
@Getter
@Schema(description = "예외 응답 DTO")
public class ErrorResponse {

    @Schema(description = "HTTP 상태 코드", example = "400")
    private final int status;

    @Schema(description = "에러 코드", example = "E40001")
    private final String code;

    @Schema(description = "에러 메시지", example = "입력값이 유효하지 않습니다.")
    private final String message;

    @Schema(description = "오류 발생 시간")
    private final LocalDateTime timestamp;

    /**
     * 생성자 - 정적 메서드를 통해서만 생성되도록 private으로 제한한다.
     *
     * @param status    HTTP 상태 코드
     * @param code      에러 코드
     * @param message   에러 메시지
     * @param timestamp 에러 발생 시간
     */
    private ErrorResponse(int status, String code, String message, LocalDateTime timestamp) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * 에러 코드 기반 에러 응답 객체 생성
     *
     * @param errorCode ErrorCode enum
     * @return ErrorResponse
     */
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                LocalDateTime.now()
        );
    }

    /**
     * 에러 코드와 커스텀 메시지를 포함한 에러 응답 객체 생성
     *
     * @param errorCode ErrorCode enum
     * @param message 사용자 정의 메시지
     * @return ErrorResponse
     */
    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(
                errorCode.getStatus(),
                errorCode.getCode(),
                message,
                LocalDateTime.now()
        );
    }
}