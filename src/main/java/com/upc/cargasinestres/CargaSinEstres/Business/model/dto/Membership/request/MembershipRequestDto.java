package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * The MembershipRequestDto class represents the data transfer object of the Membership class.
 * It contains fields related to the details of a membership entity.
 * This class is used for membership information when creating a membership.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private float price;
}
