package com.project.ph_reco.pharmacy.service;

import com.project.ph_reco.pharmacy.entity.Pharmacy;
import com.project.ph_reco.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRepositoryService {
    private final PharmacyRepository pharmacyRepository;

    @Transactional
    public void updateAddress(Long id, String address) {
        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            log.error("not found id: {}", id);
            return;
        }

        entity.changePharmacyAddress(address);
    }

    //for test
    public void updateAddressWithoutTransaction(Long id, String address) {
        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            log.error("not found id: {}", id);
            return;
        }

        entity.changePharmacyAddress(address);
    }
}
