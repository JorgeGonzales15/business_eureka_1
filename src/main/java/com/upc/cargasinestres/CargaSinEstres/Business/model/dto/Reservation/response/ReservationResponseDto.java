package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**

 This class represents the response of the booking history
 @author Grupo1
 @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {
    private Long id;
    private LocalDate startDate;
    private String startTime;
    private String originAddress;
    private String destinationAddress;
    private LocalDate endDate;
    private String endTime;
    private float price;
    private String status;
    private String services;
    private Long chatId;
    private String customerId;
    private String companyId;
}