package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.request.MembershipRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.response.MembershipResponseDto;

/**
 * Service interface for managing memberships.
 * This interface declares methods for creating and retrieving subscription information.
 * @author Grupo1*/
public interface IMembershipService {

    /**
     * Registers a new membership for the specified company.
     * @param companyId The unique identifier of the company for which the membership is registered.
     * @return A MembershipResponseDto containing information about the registered membership.
     */
    public abstract MembershipResponseDto createMembership(Long companyId, MembershipRequestDto membershipRequestDto);

    /**
     * Retrieves membership information for the specified company.
     * @param companyId The unique identifier of the company for which to retrieve the membership.
     * @return A MembershipResponseDto containing information about the membership, or null if not found.
     */
    public abstract MembershipResponseDto getMembershipByCompanyId(Long companyId);

    /*delete membership once it's over*/
}
