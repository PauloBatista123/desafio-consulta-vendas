package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummary(SUM(s.amount) as total, sl.name as sellerName) from #{#entityName} s " +
            "JOIN s.seller sl " +
            "WHERE s.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY sellerName")
    List<SaleSummary> findSummary(LocalDate minDate,
                                  LocalDate maxDate
                                  );


    @Query(value = "SELECT s from #{#entityName} s " +
            "JOIN FETCH s.seller sl " +
            "WHERE s.date BETWEEN :dateIni AND :dateMax " +
            "AND lower(sl.name) LIKE %:name%",
    countQuery = "SELECT COUNT(s) from #{#entityName} s " +
            "JOIN s.seller sl " +
            "WHERE s.date BETWEEN :dateIni AND :dateMax " +
            "AND sl.name LIKE %:name%")
    Page<SaleMinDTO> findReport(LocalDate dateIni, LocalDate dateMax, String name, Pageable pageable);
}
