package com.recordmanagement.habitlog.config.exception;

import lombok.Getter;

/**
 * ErrorCode - 공통 에러 코드 정의 Enum
 *
 * 서비스 전역에서 발생할 수 있는 예외 상황을 정의한다.
 * 각 항목은 HTTP 상태 코드, 프론트 처리용 에러 코드 문자열, 사용자 메시지를 포함한다.
 *
 * 프론트엔드는 code로 분기 처리하고, 백엔드는 상태 코드와 메시지로 응답을 제어한다.
 */
@Getter
public enum ErrorCode {

    // 400 BAD REQUEST
    INVALID_INPUT_VALUE(400, "E40001", "잘못된 입력입니다."),
    VALIDATION_FAIL(400, "E40002", "입력값이 유효하지 않습니다."),

    // 401 UNAUTHORIZED
    UNAUTHORIZED(401, "E40101", "인증이 필요합니다."),

    // 403 FORBIDDEN
    FORBIDDEN(403, "E40301", "접근이 거부되었습니다."),

    // 404 NOT FOUND
    NOT_FOUND(404, "E40401", "요청한 자원을 찾을 수 없습니다."),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "E50001", "서버 오류가 발생했습니다.");

    /** HTTP 상태 코드 */
    private final int status;

    /** 프론트 처리용 에러 코드 문자열 */
    private final String code;

    /** 사용자 전달 메시지 */
    private final String message;

    /**
     * @param status  HTTP 상태 코드
     * @param code    에러 코드 문자열
     * @param message 사용자 메시지
     */
    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
