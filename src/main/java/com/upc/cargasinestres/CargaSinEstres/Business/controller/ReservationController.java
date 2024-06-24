package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response.ReservationResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IChatService;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST reservation controller for the management of reservations of the API
 * Provides the methods to manage the reservations
 *
 * @author Grupo1
 * @version 1.0
 */
@Tag(name = "Reservation Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ReservationController {

    private final IReservationService reservationService;
    private final IChatService chatService;


    /**
     * Class constructor
     *
     * @param reservationService The service for handling reservation operations.
     */
    public ReservationController(IReservationService reservationService, IChatService chatService) { //, IChatService chatService
        this.reservationService = reservationService;
        this.chatService = chatService;
    }

    /**
     * Creates a new reservation record.
     *
     * @param customerId            The ID of the client associated with the reservation.
     * @param companyId             The ID of the company associated with the reservation.
     * @param reservationRequestDto The data for creating the reservation.
     * @return A ResponseEntity containing the created reservationResponseDtoV2 and HttpStatus.CREATED.
     */
    @Operation(summary = "Create a Reservation")
    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> createReservation
    (@RequestParam(name = "customerId") Long customerId, @RequestParam(name = "idCompany") Long companyId, @RequestBody ReservationRequestDto reservationRequestDto) {
        var res = reservationService.createReservation(customerId, companyId, reservationRequestDto);

        //chat se crea
        var chat = chatService.createChat(res.getId());
        /*if (chat == null) {
            return ResponseEntity.badRequest().build();
        }*/

        //Id de chat se setea en la reserva
        var reservation = reservationService.updateReservationChatId(res.getId(), chat.getId());
        if (reservation.getChatId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of reservation records by client ID.
     *
     * @param id The ID of the client.
     * @return A ResponseEntity containing the list of reservationResponseDtoV2 and HttpStatus.OK.
     */
    @Operation(summary = "Obtain a list of reservation by client Id")
    @GetMapping("/reservations/customer/{id}")
    public ResponseEntity<List<ReservationResponseDto>> getReservationByCustomerId(@PathVariable(name = "id") Long id) {
        var res = reservationService.getReservationByCustomerId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Retrieves a list of reservation records by company ID.
     *
     * @param id The ID of the company.
     * @return A ResponseEntity containing the list of reservationResponseDtoV2 and HttpStatus.OK.
     */
    @Operation(summary = "Obtain a list of reservation by company Id")
    @GetMapping("/reservations/company/{id}")
    public ResponseEntity<List<ReservationResponseDto>> getReservationByCompanyId(@PathVariable(name = "id") Long id) {
        var res = reservationService.getReservationByCompanyId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of reservation by company Id and status")
    @GetMapping("/reservations/company/{id}/status")
    public ResponseEntity<List<ReservationResponseDto>> getReservationByCompanyIdAndStatus(@PathVariable(name = "id") Long id, @RequestParam(name = "status") String status) {
        var res = reservationService.getReservationByCompanyIdAndStatus(id, status);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Updates the payment field of a specific reservation.
     *
     * @param reservationId The ID of the reservation to be updated.
     * @param price         The data for updating the reservation.
     * @return The response of the updated reservation.
     */
    @Operation(summary = "Update the price, startDate, startTime of a reservation")
    @PatchMapping("/reservations/{id}/price-startDate-startTime")
    public ResponseEntity<ReservationResponseDto> updateReservationPayment(@PathVariable(name = "id") Long reservationId, float price, LocalDate startDate, String startTime) { //status
        var res = reservationService.updateReservationPriceStartDateStartTime(reservationId, price, startDate, startTime);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Updates the status field of a specific reservation.
     *
     * @param reservationId The ID of the reservation to be updated.
     * @param status        The data status for updating the reservation.
     * @return The response of the updated reservation.
     */
    @Operation(summary = "Update the status of a reservation")
    @PatchMapping("/reservations/{id}/status")
    public ResponseEntity<ReservationResponseDto> updateReservationStatus(@PathVariable(name = "id") Long reservationId, @RequestParam String status) {
        var res = reservationService.updateReservationStatus(reservationId, status);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Update the endDate of a reservation")
    @PatchMapping("/reservations/{id}/endDate-endTime")
    public ResponseEntity<ReservationResponseDto> updateReservationEndDateAndEndTime(@PathVariable(name = "id") Long reservationId, @RequestParam LocalDate endDate, @RequestParam String endTime) {
        var res = reservationService.updateReservationEndDateAndEndTime(reservationId, endDate, endTime);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

}

