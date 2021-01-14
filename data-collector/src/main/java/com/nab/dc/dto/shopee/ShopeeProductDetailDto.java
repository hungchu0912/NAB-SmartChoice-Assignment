package com.nab.dc.dto.shopee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShopeeProductDetailDto {
    private Item item;
    private String version;
    @JsonProperty("error_msg")
    private String errorMsg;
    private String error;

    @Data
    public static class Item {
        @JsonProperty("itemid")
        private String itemId;
        private String name;
        private Double price;
        @JsonProperty("price_max_before_discount")
        private Double priceMaxBeforeDiscount;
        @JsonProperty("price_max")
        private Double priceMax;
        @JsonProperty("price_min")
        private Double priceMin;
        @JsonProperty("price_before_discount")
        private Double priceBeforeDiscount;
        @JsonProperty("raw_discount")
        private Double rawDiscount;
        @JsonProperty("item_status")
        private String itemStatus;
        @JsonProperty("is_non_cc_installment_payment_eligible")
        private Boolean isNonCcInstallmentPaymentEligible;
        @JsonProperty("shopid")
        private Long shopId;
        private Integer discount_stock;
        @JsonProperty("is_adult")
        private Boolean isAdult;
        private String currency;
        private Integer stock;
        private Integer status;
        @JsonProperty("shop_location")
        private String shopLocation;
        private String imgUrl;
    }
}
