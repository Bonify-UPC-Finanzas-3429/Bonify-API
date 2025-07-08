package com.enphase.platform.bonifyapirest.bond.service;

import com.enphase.platform.bonifyapirest.bond.dto.InstallmentDTO;

import java.util.List;

public interface InstallmentService {
    List<InstallmentDTO> getAll();
    List<InstallmentDTO> getByBondId(Long bondId);
    void createInstallments(List<InstallmentDTO> installments);
    void deleteByBondId(Long bondId);
}
