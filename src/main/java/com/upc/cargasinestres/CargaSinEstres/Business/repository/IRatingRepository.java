package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Rating entities.
 * @author Grupo1
 * @version 1.0
 * */
@Repository
public interface IRatingRepository extends JpaRepository<Rating, Long> {
    /**
     * Retrieves a list of rating records based on the company ID.
     *
     * @param companyId The ID of the company.
     * @return A List of Rating records associated with the specified company.
     */
    List<Rating> findByCompanyId(Long companyId);

}
