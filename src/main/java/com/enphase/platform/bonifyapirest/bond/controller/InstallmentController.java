package com.enphase.platform.bonifyapirest.bond.controller;

import com.enphase.platform.bonifyapirest.bond.dto.InstallmentDTO;
import com.enphase.platform.bonifyapirest.bond.service.InstallmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/installments")
@RequiredArgsConstructor
public class InstallmentController {

    private final InstallmentService installmentService;

    @GetMapping
    public ResponseEntity<List<InstallmentDTO>> getAll() {
        return ResponseEntity.ok(installmentService.getAll());
    }

    @GetMapping("/bond/{bondId}")
    public ResponseEntity<List<InstallmentDTO>> getByBond(@PathVariable Long bondId) {
        return ResponseEntity.ok(installmentService.getByBondId(bondId));
    }

    @PostMapping
    public ResponseEntity<Void> createAll(@RequestBody List<InstallmentDTO> installments) {
        installmentService.createInstallments(installments);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/bond/{bondId}")
    public ResponseEntity<Void> deleteByBond(@PathVariable Long bondId) {
        installmentService.deleteByBondId(bondId);
        return ResponseEntity.noContent().build();
    }
}
