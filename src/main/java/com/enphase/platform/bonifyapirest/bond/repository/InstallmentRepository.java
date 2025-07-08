package com.enphase.platform.bonifyapirest.bond.repository;

import com.enphase.platform.bonifyapirest.bond.model.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    List<Installment> findByBondId(Long bondId);
    void deleteByBondId(Long bondId);
}
