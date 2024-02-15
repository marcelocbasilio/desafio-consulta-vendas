package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleReportDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SaleReportDTO() {}

    public SaleReportDTO(Sale sale) {
        id = sale.getId();
        date = sale.getDate();
        amount = sale.getAmount();
        sellerName = sale.getSeller().getName();
    }

    public Long getId() { return id; }

    public LocalDate getDate() { return date; }

    public Double getAmount() { return amount; }

    public String getSellerName() { return sellerName; }

    @Override
    public String toString() {
        return "SaleReportDTO{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", sellerName='" + sellerName + '\'' +
                '}';
    }
}
