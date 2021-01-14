package com.nab.dc.dto.lazada;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LazadaProductDetailDto {
    private String code;
    private DataDto data;
    @JsonProperty("request_id")
    private String request_id;

    @Data
    public class DataDto {
        @JsonProperty("item_id")
        private String itemId;
        private String name;
        @JsonProperty("special_price")
        private Double specialPrice;
        private Double price;
        @JsonProperty("discount_rate")
        private Double discountRate;
        @JsonProperty("Status")
        private String status;
        private Integer quantity;
        @JsonProperty("product_weight")
        private String productWeight;
        @JsonProperty("Images")
        private List<String> images;
        @JsonProperty("SellerSku")
        private String sellerSku;
        @JsonProperty("ShopSku")
        private String shopSku;
        @JsonProperty("Url")
        private String url;
        @JsonProperty("special_to_time")
        private String specialToTime;
        @JsonProperty("special_from_time")
        private String specialFromTime;
        @JsonProperty("package_height")
        private String packageHeight;
        @JsonProperty("Available")
        private Integer available;
        @JsonProperty("SkuId")
        private String skuId;
        @JsonProperty("special_to_date")
        private String specialToDate;
    }    
}
