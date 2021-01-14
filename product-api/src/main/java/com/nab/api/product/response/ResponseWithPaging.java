package com.nab.api.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWithPaging<T> {
    private String result;
    private List<T> data;
    private Footer footer;

    public static <T> ResponseWithPaging<T> of(List<T> data) {
        ResponseWithPaging<T> response = new ResponseWithPaging<>();
        response.setResult("Success");
        response.setData(data);
        return response;
    }

    public ResponseWithPaging<T> withFooter(Footer footer) {
        this.footer = footer;
        return this;
    }

    public ResponseWithPaging<T> withFooter(Page<?> page) {
        this.footer = new Footer();
        this.footer.setPageNumber(page.getNumber() + 1);
        this.footer.setPageSize(page.getSize());
        this.footer.setTotalPages(page.getTotalPages());
        this.footer.setTotalElement((int) page.getTotalElements());
        return this;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Footer {
        private Integer pageNumber;
        private Integer pageSize;
        private Integer totalPages;
        private Integer totalElement;
    }
}
