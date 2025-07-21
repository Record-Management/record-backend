package com.recordmanagement.habitlog.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler - 전역 예외 처리 클래스
 *
 * 모든 컨트롤러에서 발생하는 예외를 하나의 클래스에서 통합 처리한다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 커스텀 예외 처리 핸들러
     *
     * CustomException 발생 시 클라이언트에 에러 메시지와 상태 코드를 전달한다.
     *
     * @param ex CustomException
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustom(CustomException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ErrorResponse.of(ex.getStatus(), ex.getMessage()));
    }

    /**
     * 일반 예외 처리 핸들러
     *
     * 예상하지 못한 예외가 발생한 경우 500 에러로 응답한다.
     *
     * @param ex Exception
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.of(500, "서버 오류가 발생했습니다: " + ex.getMessage()));
    }
}
