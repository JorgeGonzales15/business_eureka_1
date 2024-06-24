package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.ReservationValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response.ReservationResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Reservation;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IReservationRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IReservationService;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Implementation of the IreservationService interface.
 * Handles the business logic for reservation operations.
 *
 * @author Grupo1
 * @version 1.0
 */
@Service
@Qualifier("reservationServiceImpl")
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationServiceImpl(IReservationRepository reservationRepository, ModelMapper modelMapper) { //, IChatRepository chatRepository
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new reservation record.
     *
     * @param customerId              The ID of the client associated with the reservation.
     * @param companyId             The ID of the company associated with the reservation.
     * @param reservationRequestDto The data for creating the reservation.
     * @return The created reservation response.
     * @throws ResourceNotFoundException If the client or company is not found.
     */
    //Method : POST
    @Override
    public ReservationResponseDto createReservation(Long customerId, Long companyId, ReservationRequestDto reservationRequestDto) {
        /*// Buscar la cuenta
        var client = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + customerId));

        var company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la empresa con ID: " + companyId));*/

        // Validación
        ReservationValidation.ValidateReservation(reservationRequestDto);

        // Mapeo
        var newReservation = modelMapper.map(reservationRequestDto, Reservation.class);
        newReservation.setCustomerId(customerId);
        newReservation.setCompanyId(companyId);
        newReservation.setServices(newReservation.getServices().toLowerCase());
        newReservation.setStartTime(LocalTime.parse(reservationRequestDto.getStartTime()));

        newReservation.setStatus("solicited");

        var savedreservation = reservationRepository.save(newReservation);
        var response = modelMapper.map(savedreservation, ReservationResponseDto.class);
        return response;
    }

    /**
     * Retrieves a list of reservation records associated with a client.
     *
     * @param clientId The ID of the client.
     * @return A list of reservation response DTOs.
     */
    @Override
    public List<ReservationResponseDto> getReservationByCustomerId(Long clientId) {
        var existingReservation = reservationRepository.findByCustomerId(clientId);
        if (existingReservation.isEmpty())
            throw new ResourceNotFoundException("No se encuentran reservas para el cliente : " + clientId);

        var toShowReservations = existingReservation.stream()
                .map(Reservation -> modelMapper.map(Reservation, ReservationResponseDto.class))
                .toList();

        /*
        for (ReservationResponseDto reservation : toShowReservations) {
            var Chat = chatRepository.findByReservationId(reservation.getId());
            reservation.setChat_id(Chat.getId());
        }
         */
        return toShowReservations;
    }

    /**
     * Retrieves a list of reservation records associated with a company.
     *
     * @param companyId The ID of the company.
     * @return A list of reservation response DTOs.
     */
    @Override
    public List<ReservationResponseDto> getReservationByCompanyId(Long companyId) {
        var existingReservation = reservationRepository.findByCompanyId(companyId);
        if (existingReservation.isEmpty())
            throw new ResourceNotFoundException("No se encuentran reservas para el cliente : " + companyId);

        var toShowReservations = existingReservation.stream()
                .map(Reservation -> modelMapper.map(Reservation, ReservationResponseDto.class))
                .toList();

        return toShowReservations;

    }

    /**
     * Retrieves a list of reservation records associated with a company and a specific status.
     *
     * @param companyId The ID of the company.
     * @param status The status of the reservation.
     * @return A list of reservation response DTOs.
     */
    @Override
    public List<ReservationResponseDto> getReservationByCompanyIdAndStatus(Long companyId, String status) {
        var existingReservation = reservationRepository.findByCompanyIdAndStatus(companyId, status);
        if (existingReservation.isEmpty())
            throw new ResourceNotFoundException("No se encuentran reservas para la empresa : " + companyId);

        var toShowReservations = existingReservation.stream()
                .map(Reservation -> modelMapper.map(Reservation, ReservationResponseDto.class))
                .toList();
        return toShowReservations;
    }

    /**
     * Updates the payment field of a specific reservation.
     *
     * @param reservationId         The ID of the reservation to be updated.
     * @param price The data for updating the payment of the reservation.
     * @return The response of the updated reservation.
     * @throws ResourceNotFoundException If the reservation with the given ID is not found.
     * @throws ValidationException       If the payment amount is not greater than 0.
     */
    @Override
    public ReservationResponseDto updateReservationPriceStartDateStartTime(Long reservationId, float price, LocalDate startDate, String startTime) {
        // Buscar la reserva
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el historial de reserva con ID: " + reservationId));

        // Validación
        if (price <= 0) throw new ValidationException("El precio debe ser mayor a 0");

        reservation.setPrice(price);
        reservation.setStartDate(startDate);
        reservation.setStartTime(LocalTime.parse(startTime));

        // Guardar la reserva actualizada
        var updatedreservation = reservationRepository.save(reservation);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, ReservationResponseDto.class);
    }

    /**
     * Updates the status field of a specific reservation.
     *
     * @param reservationId         The ID of the reservation to be updated.
     * @param status The data for updating the status of the reservation.
     * @return The response of the updated reservation.
     * @throws ResourceNotFoundException If the reservation with the given ID is not found.
     * @throws ValidationException       If the new status is not "Finalizado".
     */
    @Override
    public ReservationResponseDto updateReservationStatus(Long reservationId, String status) {
        // Buscar la reserva
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la reserva con ID: " + reservationId));

        // Validación
        if (!(status.equals("solicited")) && !(status.equals("finalized"))
                && !(status.equals("cancelled")) && !(status.equals("scheduled"))
                && !(status.equals("to be scheduled")) && !(status.equals("re-scheduled")) && !(status.equals("in progress")) ) {
            throw new ValidationException("El estado debe ser 'solicited', 'finalized', 'scheduled', 'cancelled', 'to be scheduled', 're-scheduled' o 'in progress'");
        }
        reservation.setStatus(status);

        // Guardar la reserva actualizada
        var updatedreservation = reservationRepository.save(reservation);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, ReservationResponseDto.class);
    }

    @Override
    public ReservationResponseDto updateReservationChatId(Long reservationId, Long chatId) {
        // Buscar la reserva
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el historial de reserva con ID: " + reservationId));

        // Actualizar el campo "chatId"
        reservation.setChatId(chatId);

        // Guardar la reserva actualizada
        var updated = reservationRepository.save(reservation);

        return modelMapper.map(updated, ReservationResponseDto.class);
    }

    @Override
    public Reservation getById(Long resId) {
        return reservationRepository.findById(resId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el historial de reserva con ID: " + resId));
    }

    @Override
    public ReservationResponseDto updateReservationEndDateAndEndTime(Long reservationId, LocalDate endDate, String endTime) {
        // Buscar la reserva
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la reserva con ID: " + reservationId));

        reservation.setEndDate(endDate);
        reservation.setEndTime(LocalTime.parse(endTime));

        // Guardar la reserva actualizada
        var updatedreservation = reservationRepository.save(reservation);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, ReservationResponseDto.class);
    }
}
