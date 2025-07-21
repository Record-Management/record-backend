package com.recordmanagement.habitlog.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
     * 전역 예외 처리 핸들러 (예상하지 못한 모든 예외)
     *
     * - 컨트롤러에서 처리되지 않은 예외가 발생할 경우 이 메서드에서 처리된다.
     * - 서버 내부 오류(HTTP 500)를 클라이언트에 일관된 형태로 응답한다.
     * - 로그에 요청 URI와 예외 내용을 기록하여 추후 디버깅에 활용할 수 있도록 한다.
     *
     * @param e       발생한 예외 객체
     * @param request 현재 요청 객체 (요청 URI 확인용)
     * @return        ErrorResponse (HTTP 500 + 에러 코드 및 메시지)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        String uri = request.getRequestURI();
        log.error("서버 내부 오류 발생 - 요청 URI: {}", uri, e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }

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
}