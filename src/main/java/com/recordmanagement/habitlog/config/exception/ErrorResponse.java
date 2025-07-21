package com.recordmanagement.habitlog.config.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * ErrorResponse - API 예외 발생 시 클라이언트에 반환할 에러 응답 데이터 구조를 정의한 DTO 클래스이다.
 *
 * <p>HTTP 상태 코드, 에러 메시지, 에러 발생 시간을 포함하여
 * 클라이언트가 에러를 일관된 형식으로 처리할 수 있도록 설계되었다.</p>
 *
 * Swagger API 문서 생성 시 응답 스키마로 활용된다.
 */
@Schema(description = "예외 응답 DTO")
public class ErrorResponse {

    /** HTTP 응답 상태 코드 (ex: 400, 404, 500 등) */
    @Schema(description = "HTTP 상태 코드", example = "400")
    private int status;

    /** 클라이언트에 전달할 에러 메시지 */
    @Schema(description = "예외 메시지", example = "잘못된 요청입니다.")
    private String message;

    /** 에러 발생 시각 (서버 시간 기준) */
    @Schema(description = "오류 발생 시간")
    private LocalDateTime timestamp;

    /**
     * 기본 생성자 - JSON 직렬화/역직렬화 시 사용된다.
     */
    public ErrorResponse() {}

    /**
     * 전체 필드를 초기화하는 생성자
     *
     * @param status HTTP 상태 코드
     * @param message 에러 메시지
     * @param timestamp 에러 발생 시각
     */
    public ErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    /** HTTP 상태 코드 반환 */
    public int getStatus() {
        return status;
    }

    /** HTTP 상태 코드 설정 */
    public void setStatus(int status) {
        this.status = status;
    }

    /** 에러 메시지 반환 */
    public String getMessage() {
        return message;
    }

    /** 에러 메시지 설정 */
    public void setMessage(String message) {
        this.message = message;
    }

    /** 에러 발생 시간 반환 */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /** 에러 발생 시간 설정 */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 정적 생성 메서드 - 현재 시간으로 timestamp를 자동 세팅하여
     * 편리하게 ErrorResponse 객체를 생성할 수 있다.
     *
     * @param status HTTP 상태 코드
     * @param message 에러 메시지
     * @return 생성된 ErrorResponse 객체
     */
    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message, LocalDateTime.now());
    }
}
