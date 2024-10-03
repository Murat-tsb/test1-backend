package com.example.test1.repository;

import com.example.test1.dto.ReturnDTO;
import com.example.test1.model.Returns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReturnsRepository extends JpaRepository<Returns, Long> {
    @Query("SELECT new com.example.test1.dto.ReturnDTO(r.companyName, r.commission, r.returnDate, r.paymentAmount, r.totalPayment) " +
            "FROM Returns r " +
            "WHERE (:companyName IS NULL OR r.companyName = :companyName) " +
            "AND (cast(:startDate as DATE) IS NULL OR r.returnDate >= :startDate) " +
            "AND r.returnDate <= :endDate")
    List<ReturnDTO> getByDateAndCompany(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate,
                                        @Param("companyName") String companyName);
}

