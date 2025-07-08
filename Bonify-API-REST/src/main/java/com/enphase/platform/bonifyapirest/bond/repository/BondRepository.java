package com.enphase.platform.bonifyapirest.bond.repository;

import com.enphase.platform.bonifyapirest.bond.model.entity.Bond;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BondRepository extends JpaRepository<Bond, Long> {
    List<Bond> findByUserId(Long userId);
    Optional<Bond> findByBondId(String bondId);
}
