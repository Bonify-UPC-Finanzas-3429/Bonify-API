package com.enphase.platform.bonifyapirest.bond.service;

import com.enphase.platform.bonifyapirest.bond.dto.BondRequestDTO;
import com.enphase.platform.bonifyapirest.bond.dto.BondResponseDTO;

import java.util.List;

public interface BondService {
    BondResponseDTO createBond(BondRequestDTO request);
    List<BondResponseDTO> getAll();
    List<BondResponseDTO> getByUserId(Long userId);
    BondResponseDTO getByBondId(String bondId);
    void deleteBond(Long id);
}
