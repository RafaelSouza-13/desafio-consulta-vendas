package com.devsuperior.dsmeta.repositories;



import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.amount, obj.date, s.name) " +
            " FROM Sale obj" +
            " INNER JOIN Seller s" +
            " ON obj.seller = s.id" +
            " WHERE obj.date BETWEEN :minDate AND :maxDate " +
            " AND UPPER(s.name) LIKE UPPER(CONCAT('%', :name, '%'))",
            countQuery = "SELECT COUNT(obj)" +
                    " FROM Sale obj" +
                    " JOIN obj.seller s" +
                    " WHERE UPPER(s.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleMinDTO> searchByName(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(seller.name, SUM(sale.amount))" +
            " FROM Sale sale" +
            " INNER JOIN Seller seller" +
            " ON sale.seller = seller.id" +
            " WHERE sale.date BETWEEN :minDate AND :maxDate" +
            " GROUP BY seller.name",
            countQuery = "SELECT COUNT(sale)" +
                    " FROM Sale sale" +
                    " JOIN sale.seller seller" +
                    " WHERE sale.date BETWEEN :minDate AND :maxDate")
    Page<SaleReportDTO> searchSumarry(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
