package com.nab.dc.dto.tiki;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TikiProductDetailDto {
    private String id;
    private String name;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("is_deal")
    private Boolean is_deal;
    private Double price;
    @JsonProperty("inventory_status")
    private String inventoryStatus;
    @JsonProperty("is_hot_deal")
    private Boolean isHotDeal;
    @JsonProperty("deal_specs")
    private DealSpecs dealSpecs;
    private Integer qty;
    @JsonProperty("qty_ordered")
    private Integer qtyOrdered;
    @JsonProperty("special_to_date")
    private String specialToDate;
    @JsonProperty("is_free_shipping")
    private boolean isFreeShipping;
    @JsonProperty("is_support_installation_package")
    private boolean isSupportInstallationPackage;
    private String imgUrl;

    @Data
    public static class DealSpecs {
        @JsonProperty("is_hot_deal")
        private Boolean isHotDeal;
        private Integer qty;
        @JsonProperty("max_sale_qty")
        private Integer maxSaleQty;
        @JsonProperty("qty_ordered")
        private Integer qtyOrdered;
        @JsonProperty("special_to_date")
        private Long specialToDate;
        private Double price;
        @JsonProperty("list_price")
        private Double listPrice;
        @JsonProperty("discount_rate")
        private Double discountRate;
        @JsonProperty("progress_text")
        private String progressText;
    }
}
