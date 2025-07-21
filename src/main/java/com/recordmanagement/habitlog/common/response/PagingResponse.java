package com.recordmanagement.habitlog.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * PagingResponse - 페이징 처리된 데이터를 포함하는 응답 포맷
 *
 * 실제 데이터 리스트(items)와 페이지 정보를 함께 포함한다.
 * 프론트에서 페이지네이션 UI 구성 시 활용된다.
 *
 * @param <T> 목록 데이터의 타입
 */
@Getter
@Schema(description = "페이징 응답 포맷")
@AllArgsConstructor
public class PagingResponse<T> {

    @Schema(description = "조회된 데이터 목록")
    private final List<T> items;

    @Schema(description = "페이지 정보")
    private final PageInfo pageInfo;

    /**
     * PageInfo - 페이징 정보 클래스
     *
     * 현재 페이지, 사이즈, 전체 개수, 전체 페이지 수를 포함한다.
     */
    @Getter
    @AllArgsConstructor
    @Schema(description = "페이지 정보")
    public static class PageInfo {

        @Schema(description = "현재 페이지 번호", example = "1")
        private final int page;

        @Schema(description = "한 페이지당 데이터 수", example = "10")
        private final int size;

        @Schema(description = "전체 데이터 개수", example = "123")
        private final long totalElements;

        @Schema(description = "전체 페이지 수", example = "13")
        private final int totalPages;
    }
}