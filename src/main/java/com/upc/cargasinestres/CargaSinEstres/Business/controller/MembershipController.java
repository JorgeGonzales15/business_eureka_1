package com.upc.cargasinestres.CargaSinEstres.Business.controller;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.request.MembershipRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.response.MembershipResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IMembershipService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Membership controller
 * @author Grupo1
 * @version 1.0
 */
@Tag(name="Membership Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class MembershipController {

    private final IMembershipService membershipService;

    /**
     * Class constructor
     *
     * @param membershipService The membership service implementation.
     */
    public MembershipController(IMembershipService membershipService){
        this.membershipService = membershipService;
    }

    /**
     * Handles the creation of a new membership.
     * @param idCompany The
     * @param membershipRequestDto The request data for creating the membership.
     * @return ResponseEntity with the created membership response or an error status.
     */
    @PostMapping("/memberships/{idCompany}")
    public ResponseEntity<MembershipResponseDto> createMembership(@PathVariable Long idCompany, @RequestBody MembershipRequestDto membershipRequestDto){
        var res = membershipService.createMembership(idCompany, membershipRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Handles the retrieval of membership(s) by company ID.
     *
     * @param idCompany The ID of the company for which memberships are retrieved.
     * @return ResponseEntity with the membership response(s) or an error status.
     */
    @GetMapping("/memberships/{idCompany}")
    public ResponseEntity<MembershipResponseDto> getMembership(@PathVariable(name="idCompany") Long idCompany){
        var res = membershipService.getMembershipByCompanyId(idCompany);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}