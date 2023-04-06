package com.project.ph_reco.pharmacy.repository;

import com.project.ph_reco.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
