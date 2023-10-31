package com.inha.capstonedesign.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = "페이징 처리하고 반환하는 DTO")
@Getter
@Setter
@NoArgsConstructor
public class PageResponseDto<T> {
    @Schema(description = "페이지 안에 들어갈 내용 list")
    private List<T> content;
    @Schema(description = "총 페이지 수", example = "10")
    private int totalPages;
    @Schema(description = "총 데이터 수", example = "27")
    private long totalElements;
    @Schema(description = "현재 페이지 번호", example = "1")
    private int pageNumber;
    @Schema(description = "한 페이지에 들어가는 데이터 수", example = "5")
    private int size;

    public PageResponseDto(Page<T> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getNumber() + 1;
        this.size = page.getSize();
    }
}
