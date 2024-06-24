package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Repository interface for managing Subscription entities.
 * @author Grupo1
 * @version 1.0
 * */
@Repository
public interface IMembershipRepository extends JpaRepository<Membership, Long> {

    /**
     * Retrieve a membership by the specified end date, this is used to check if a membership is still active.
     * @param endDate
     * @return
     */
    Membership findByEndDate(LocalDate endDate); //flujo

    /**
     * Retrieve a membership by the specified company ID.
     * @param companyId The unique identifier of the company associated with the subscription.
     * @return An Optional containing the Subscription entity, or empty if not found.
     */
    Membership findByCompanyId(Long companyId);

}
