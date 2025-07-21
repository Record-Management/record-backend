package com.recordmanagement.habitlog.config.exception;

import lombok.Getter;

/**
 * CustomException - 비즈니스 로직 전용 커스텀 예외 클래스
 *
 * ErrorCode를 통해 HTTP 상태, 에러 코드, 메시지를 일관되게 관리하며
 * 서비스 및 도메인 계층에서 발생하는 비즈니스 예외를 명확히 표현한다.
 */
@Getter
public class CustomException extends RuntimeException {

    /** 에러 코드 객체 */
    private final ErrorCode errorCode;

    /**
     * CustomException 생성자
     *
     * @param errorCode 발생한 에러 코드
     */
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
