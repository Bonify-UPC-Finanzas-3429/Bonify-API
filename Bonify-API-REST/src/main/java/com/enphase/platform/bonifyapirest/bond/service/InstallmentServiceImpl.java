package com.enphase.platform.bonifyapirest.bond.service;

import com.enphase.platform.bonifyapirest.bond.dto.InstallmentDTO;
import com.enphase.platform.bonifyapirest.bond.model.entity.Installment;
import com.enphase.platform.bonifyapirest.bond.repository.InstallmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstallmentServiceImpl implements InstallmentService {

    private final InstallmentRepository repository;

    @Override
    public List<InstallmentDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public List<InstallmentDTO> getByBondId(Long bondId) {
        return repository.findByBondId(bondId).stream().map(this::toDTO).toList();
    }

    @Override
    public void createInstallments(List<InstallmentDTO> dtos) {
        List<Installment> entities = dtos.stream().map(this::toEntity).toList();
        repository.saveAll(entities);
    }

    @Override
    public void deleteByBondId(Long bondId) {
        repository.deleteByBondId(bondId);
    }

    private InstallmentDTO toDTO(Installment i) {
        return new InstallmentDTO(
                i.getId(),
                i.getPeriodNumber(),
                i.getBondId(),
                i.getInterest(),
                i.getAmortization(),
                i.getTotalPayment(),
                i.getRemainingBalance(),
                i.getPaymentDate()
        );
    }

    private Installment toEntity(InstallmentDTO dto) {
        return Installment.builder()
                .id(dto.id())
                .periodNumber(dto.periodNumber())
                .bondId(dto.bondId())
                .interest(dto.interest())
                .amortization(dto.amortization())
                .totalPayment(dto.totalPayment())
                .remainingBalance(dto.remainingBalance())
                .paymentDate(dto.paymentDate())
                .build();
    }
}
