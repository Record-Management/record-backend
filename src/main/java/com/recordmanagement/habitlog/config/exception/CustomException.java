package com.recordmanagement.habitlog.config.exception;

/**
 * CustomException - 커스텀 예외 클래스
 *
 * 비즈니스 로직에서 발생 가능한 예외를 커스텀으로 정의하고,
 * HTTP 상태 코드와 메시지를 함께 전달할 수 있도록 한다.
 */
public class CustomException extends RuntimeException {

    private final int status;

    /**
     * 상태 코드와 메시지를 포함한 예외 객체 생성자
     *
     * @param message 예외 메시지
     * @param status  HTTP 상태 코드 (예: 400, 404, 500 등)
     */
    public CustomException(String message, int status) {
        super(message);
        this.status = status;
    }

    /**
     * 상태 코드 반환
     *
     * @return int status
     */
    public int getStatus() {
        return status;
    }
}
