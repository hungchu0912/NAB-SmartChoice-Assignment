package com.nab.api.product.controller;

import com.nab.api.product.response.ProductDetailResponseDto;
import com.nab.api.product.response.ResponseWithPaging;
import com.nab.api.product.response.SearchResponseDto;
import com.nab.api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseWithPaging<SearchResponseDto> search(@RequestParam String name,
                                                        @RequestParam(required = false) Integer index,
                                                        @RequestParam(required = false) Integer size) {
        return productService.search(name, index, size);
    }

    @GetMapping("/{productId}")
    public ProductDetailResponseDto detail(@PathVariable Integer productId) {
        return productService.detail(productId);
    }
}
