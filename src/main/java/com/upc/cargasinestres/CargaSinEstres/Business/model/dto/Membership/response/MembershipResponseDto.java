package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
/**
 * The MembershipResponseDto class represents the data transfer object of the Membership class.
 * It contains fields related to the details of a membership entity.
 * This class is used for subscription information when retrieving a membership.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponseDto {
    private Long id;
    private float price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long companyId;
}
