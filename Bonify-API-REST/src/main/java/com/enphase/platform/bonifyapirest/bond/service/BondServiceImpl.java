package com.enphase.platform.bonifyapirest.bond.service;

import com.enphase.platform.bonifyapirest.bond.dto.*;
import com.enphase.platform.bonifyapirest.bond.model.entity.*;
import com.enphase.platform.bonifyapirest.bond.model.valueobject.*;
import com.enphase.platform.bonifyapirest.bond.repository.*;
import com.enphase.platform.bonifyapirest.bond.util.BondCalculator;
import com.enphase.platform.bonifyapirest.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BondServiceImpl implements BondService {

    private final BondRepository bondRepository;
    private final InstallmentRepository installmentRepository;

    @Override
    public BondResponseDTO createBond(BondRequestDTO request) {
        int paymentsPerYear = BondCalculator.FREQUENCY_MAP.get(request.paymentFrequency());
        int totalInstallments = request.termInYears() * paymentsPerYear;
        boolean isEffective = request.rateType() == RateType.EFECTIVA;

        double ratePerPeriod = BondCalculator.calculateRatePerPeriod(
                request.annualInterestRate(),
                paymentsPerYear,
                isEffective
        );

        double installmentValue = BondCalculator.calculateInstallment(
                request.nominalValue(),
                ratePerPeriod,
                totalInstallments
        );

        double tea = BondCalculator.calculateTEA(ratePerPeriod, paymentsPerYear);
        double tcea = tea;

        Bond bond = bondRepository.save(Bond.builder()
                .bondId("BOND-" + System.currentTimeMillis())
                .name(request.name())
                .nominalValue(request.nominalValue())
                .issueDate(request.issueDate())
                .termInYears(request.termInYears())
                .annualInterestRate(request.annualInterestRate())
                .rateType(request.rateType())
                .paymentFrequency(request.paymentFrequency())
                .gracePeriod(request.gracePeriod())
                .calculatedInstallment(totalInstallments)
                .tcea(tcea)
                .tea(tea)
                .userId(request.userId())
                .build());

        createInstallments(bond, totalInstallments, ratePerPeriod, installmentValue);

        return toDTO(bond);
    }

    private void createInstallments(Bond bond, int total, double ratePerPeriod, double installmentValue) {
        List<Installment> installments = new ArrayList<>();
        double balance = bond.getNominalValue();
        int monthsBetweenPayments = 12 / BondCalculator.FREQUENCY_MAP.get(bond.getPaymentFrequency());
        LocalDate baseDate = bond.getIssueDate();

        int nGracia = bond.getGracePeriod() != GracePeriodType.NINGUNO ? 2 : 0;

        for (int i = 1; i <= total; i++) {
            double interest = balance * ratePerPeriod;
            double amortization = (i <= nGracia && bond.getGracePeriod() != GracePeriodType.NINGUNO) ? 0 : installmentValue - interest;
            double totalPayment = interest + amortization;
            double remainingBalance = balance - amortization;

            if (i == total) amortization = balance;
            balance = remainingBalance;

            LocalDate paymentDate = baseDate.plusMonths((long) i * monthsBetweenPayments);

            installments.add(Installment.builder()
                    .periodNumber(i)
                    .bondId(bond.getId())
                    .interest(round(interest))
                    .amortization(round(amortization))
                    .totalPayment(round(totalPayment))
                    .remainingBalance(round(Math.max(balance, 0)))
                    .paymentDate(paymentDate)
                    .build());
        }

        installmentRepository.saveAll(installments);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public List<BondResponseDTO> getAll() {
        return bondRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public List<BondResponseDTO> getByUserId(Long userId) {
        return bondRepository.findByUserId(userId).stream().map(this::toDTO).toList();
    }

    @Override
    public BondResponseDTO getByBondId(String bondId) {
        Bond bond = bondRepository.findByBondId(bondId)
                .orElseThrow(() -> new ResourceNotFoundException("Bono no encontrado"));
        return toDTO(bond);
    }

    @Override
    public void deleteBond(Long id) {
        bondRepository.deleteById(id);
    }

    private BondResponseDTO toDTO(Bond bond) {
        return new BondResponseDTO(
                bond.getId(),
                bond.getBondId(),
                bond.getName(),
                bond.getNominalValue(),
                bond.getIssueDate(),
                bond.getTermInYears(),
                bond.getAnnualInterestRate(),
                bond.getRateType(),
                bond.getPaymentFrequency(),
                bond.getGracePeriod(),
                bond.getCalculatedInstallment(),
                bond.getTcea(),
                bond.getTea(),
                bond.getUserId()
        );
    }
}
