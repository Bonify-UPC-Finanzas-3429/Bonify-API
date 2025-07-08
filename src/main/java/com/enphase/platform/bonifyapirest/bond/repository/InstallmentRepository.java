package com.enphase.platform.bonifyapirest.bond.repository;

import com.enphase.platform.bonifyapirest.bond.model.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    List<Installment> findByBondId(Long bondId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Installment i WHERE i.bondId = :bondId")
    void deleteByBondId(Long bondId);
}
