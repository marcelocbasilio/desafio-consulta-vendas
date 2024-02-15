package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private SellerDTO seller;

    public SaleDTO() {}

    public SaleDTO(Long id, LocalDate date, Double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public SaleDTO(Sale sale) {
        id = sale.getId();
        date = sale.getDate();
        amount = sale.getAmount();
        seller = new SellerDTO(sale.getSeller());
    }

    public Long getId() { return id; }

    public LocalDate getDate() { return date; }

    public Double getAmount() { return amount; }

    public SellerDTO getSeller() { return seller; }

    @Override
    public String toString() {
        return "SaleDTO{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", seller=" + seller +
                '}';
    }
}
