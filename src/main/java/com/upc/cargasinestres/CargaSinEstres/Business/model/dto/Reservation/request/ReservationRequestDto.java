package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * The BookingHistoryRequestDto class represents the data transfer object for creating a booking history record.
 * It contains fields related to the details of a booking history.
 * This class is used for receiving client requests to create booking history entries.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {
    private String originAddress;
    private String destinationAddress;
    private LocalDate startDate;
    private String startTime;
    private String services;
}
