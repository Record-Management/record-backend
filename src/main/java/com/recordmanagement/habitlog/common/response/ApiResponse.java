package com.recordmanagement.habitlog.common.response;

import com.recordmanagement.habitlog.config.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * ApiResponse - 모든 API 응답을 감싸는 공통 응답 클래스
 *
 * 성공 여부, 응답 코드, 메시지, 실제 데이터를 포함하며,
 * 프론트엔드는 항상 동일한 응답 구조를 기준으로 처리할 수 있다.
 *
 * @param <T> 응답 데이터의 타입
 */
@Getter
@Schema(description = "공통 API 응답 포맷")
public class ApiResponse<T> {

    @Schema(description = "요청 처리 성공 여부", example = "true")
    private final boolean success;

    @Schema(description = "응답 코드", example = "S200 또는 E40001")
    private final String code;

    @Schema(description = "응답 메시지", example = "요청이 성공적으로 처리되었습니다.")
    private final String message;

    @Schema(description = "응답 데이터")
    private final T data;

    /**
     * 생성자 - @Builder 사용 시 내부적으로 호출됨
     */
    @Builder
    public ApiResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 기본 성공 응답 생성 (기본 메시지 포함)
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "S200", "요청이 성공적으로 처리되었습니다.", data);
    }

    /**
     * 커스텀 메시지를 포함한 성공 응답 생성
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, "S200", message, data);
    }

    /**
     * 실패 응답 생성
     */
    public static <T> ApiResponse<T> failure(String code, String message) {
        return new ApiResponse<>(false, code, message, null);
    }

    /**
     * ErrorCode 기반 실패 응답 생성
     */
    public static <T> ApiResponse<T> from(ErrorCode errorCode) {
        return failure(errorCode.getCode(), errorCode.getMessage());
    }
}