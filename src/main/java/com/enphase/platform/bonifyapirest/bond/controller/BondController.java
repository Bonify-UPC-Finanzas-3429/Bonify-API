package com.enphase.platform.bonifyapirest.bond.controller;

import com.enphase.platform.bonifyapirest.bond.dto.BondRequestDTO;
import com.enphase.platform.bonifyapirest.bond.dto.BondResponseDTO;
import com.enphase.platform.bonifyapirest.bond.service.BondService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bonds")
@RequiredArgsConstructor
public class BondController {

    private final BondService bondService;

    @PostMapping
    public ResponseEntity<BondResponseDTO> create(@RequestBody BondRequestDTO dto) {
        return ResponseEntity.ok(bondService.createBond(dto));
    }

    @GetMapping
    public ResponseEntity<List<BondResponseDTO>> getAll() {
        return ResponseEntity.ok(bondService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BondResponseDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bondService.getByUserId(userId));
    }

    @GetMapping("/{bondId}")
    public ResponseEntity<BondResponseDTO> getByBondId(@PathVariable String bondId) {
        return ResponseEntity.ok(bondService.getByBondId(bondId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bondService.deleteBond(id);
        return ResponseEntity.noContent().build();
    }
}
