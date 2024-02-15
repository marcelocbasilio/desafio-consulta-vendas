package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT sale FROM Sale sale WHERE sale.date BETWEEN :min AND :max AND UPPER(sale.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Sale> searchReport(LocalDate min, LocalDate max, String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(tb_sales.amount) AS total "
            + "FROM tb_sales "
            + "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
            + "WHERE tb_sales.date >= :min AND tb_sales.date <= :max "
            + "GROUP BY tb_seller.name ")
    List<SaleSummaryProjection> searchSummary(LocalDate min, LocalDate max);
}
