package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The IBookingHistoryRepository interface extends JpaRepository for CRUD operations on BookingHistory entities.
 * It provides methods for querying booking history records based on client ID, company ID, and other criteria.
 * @author Grupo1
 * @version 1.0
 * */
public interface IReservationRepository extends JpaRepository<Reservation, Long>{

    /**
     * Retrieves a list of booking history records based on the client ID.
     *
     * @param customerId The ID of the client.
     * @return A List of BookingHistory records associated with the specified client.
     */
    List<Reservation> findByCustomerId(Long customerId);


    /**
     * Retrieves a list of booking history records based on the company ID.
     *
     * @param companyId The ID of the company.
     * @return A List of BookingHistory records associated with the specified company.
     */
    List<Reservation> findByCompanyId(Long companyId);

    /**
     * Retrieves a list of booking history records based on the company ID.
     *
     * @param companyId The ID of the company.
     * @param status The status of the reservation.
     * @return A List of BookingHistory records associated with the specified company.
     */
    List<Reservation> findByCompanyIdAndStatus(Long companyId, String status);

    /**
     * Retrieves a list of reservations based on the customer ID and the status of the reservation.
     *
     * @param customerId The ID of the client.
     * @param status The status of the reservation.
     * @return A List of BookingHistory records associated with the specified client.
     */
    List<Reservation> findByCustomerIdAndStatus(Long customerId, String status);

}
