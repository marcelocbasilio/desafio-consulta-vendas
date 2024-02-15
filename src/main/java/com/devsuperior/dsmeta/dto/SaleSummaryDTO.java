package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public class SaleSummaryDTO {
    private String name;
    private Double amount;

    public SaleSummaryDTO(SaleSummaryProjection projection) {
        name = projection.getName();
        amount = projection.getTotal();
    }

    public String getName() { return name; }

    public Double getAmount() { return amount; }

    @Override
    public String toString() {
        return "SaleSummaryDTO{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
