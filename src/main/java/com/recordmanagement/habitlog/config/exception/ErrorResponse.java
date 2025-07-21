package com.recordmanagement.habitlog.config.exception;

import java.time.LocalDateTime;

/**
 * ErrorResponse - 예외 응답 DTO
 *
 * 클라이언트에 일관된 형식의 오류 응답을 전달하기 위한 클래스
 *
 * @param status    HTTP 상태 코드
 * @param message   예외 메시지
 * @param timestamp 오류 발생 시간
 */
public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp
) {
    /**
     * ErrorResponse 객체를 생성하는 정적 메서드
     *
     * @param status  HTTP 상태 코드
     * @param message 예외 메시지
     * @return ErrorResponse 객체
     */
    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message, LocalDateTime.now());
    }
}
