package com.recordmanagement.habitlog.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler - 전역 예외 처리 클래스
 *
 * 컨트롤러에서 발생하는 모든 예외를 한 곳에서 처리하여
 * 일관된 에러 응답을 제공하고 서버 로그를 기록한다.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 비즈니스 예외 처리 핸들러
     *
     * @param ex CustomException
     * @return ErrorResponse
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorCode code = ex.getErrorCode();
        log.warn("비즈니스 예외 발생: {}", code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(ErrorResponse.of(code));
    }

    /**
     * 유효성 검사 실패 핸들러 (DTO @Valid 실패 등)
     *
     * @param ex MethodArgumentNotValidException
     * @return ErrorResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("유효성 검사 실패: {}", errorMsg);
        return ResponseEntity.badRequest().body(ErrorResponse.of(ErrorCode.VALIDATION_FAIL, errorMsg));
    }

    /**
     * 인가 실패 핸들러 (보안상 접근 권한 없음)
     *
     * @param ex AccessDeniedException
     * @return ErrorResponse
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        log.warn("접근 거부: {}", ex.getMessage());
        return ResponseEntity
                .status(ErrorCode.FORBIDDEN.getStatus())
                .body(ErrorResponse.of(ErrorCode.FORBIDDEN));
    }

    /**
     * 예상치 못한 서버 내부 예외 처리 핸들러
     *
     * @param ex Exception
     * @return ErrorResponse
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        log.error("서버 내부 오류 발생", ex);
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}