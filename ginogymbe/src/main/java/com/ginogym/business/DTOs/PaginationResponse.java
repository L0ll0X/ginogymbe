package com.ginogym.business.DTOs;

import java.util.List;
import org.springframework.data.domain.Page;
import lombok.Data;

@Data
public class PaginationResponse<T> {
    
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private boolean last;
    private int size;
    private int number; // Numero di pagina corrente

    // Costruttore per convertire direttamente da Spring Page
    public PaginationResponse(Page<T> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.last = page.isLast();
        this.size = page.getSize();
        this.number = page.getNumber();
    }
}
