package com.devsuperior.dsmeta.repositories;



import com.devsuperior.dsmeta.entities.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT obj FROM Seller obj" +
            " JOIN FETCH obj.sales" +
            " WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))",
            countQuery = "SELECT COUNT(obj)" +
                    " FROM Seller obj" +
                    " JOIN obj.sales")
    Page<Seller> searchByName(String name, Pageable pageable);
}
