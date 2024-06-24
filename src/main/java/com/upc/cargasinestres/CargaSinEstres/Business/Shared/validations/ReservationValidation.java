package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The reservationValidation class provides methods for validating reservationRequestDto objects.
 * It checks for the presence and validity of essential fields in a booking history request.
 */
public class ReservationValidation {

    /**
     * Validates the provided ReservationRequestDto object.
     *
     * @param reservationRequestDto The ReservationRequestDto object to be validated.
     * @throws ValidationException if any validation rule is not met.
     */
    public static void ValidateReservation(ReservationRequestDto reservationRequestDto){

        if(reservationRequestDto.getOriginAddress() == null || reservationRequestDto.getOriginAddress().isEmpty()){
            throw new ValidationException("La dirección de recogida debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getDestinationAddress() == null || reservationRequestDto.getDestinationAddress().isEmpty()){
            throw new ValidationException("La dirección de destino debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getStartDate() == null){
            throw new ValidationException("La fecha de recogida debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getStartTime() == null){
            throw new ValidationException("El tiempo de recogida debe ser obligatorio"); //error 400
        }

        if(reservationRequestDto.getServices() == null || reservationRequestDto.getServices().isEmpty()){
            throw new ValidationException("La reserva debe presentar almenos 1 servicio, es obligatorio"); //error 400
        } else {
            validateServices(reservationRequestDto.getServices());
        }

        LocalDate ahora = LocalDate.now();
        if(reservationRequestDto.getStartDate().isBefore(ahora)){
            throw new ValidationException("La fecha de inicio de la reserva no puede ser en el pasado."); //error 400
        }

    }

    /*public static void ValidateReservationStatus(ReservationRequestDto reservationRequestDto) {
        if (!(reservationRequestDto.getStatus().equals("finished")) && !(reservationRequestDto.getStatus().equals("cancelled"))) {
            throw new ValidationException("El estado a enviar debe ser Finalizado");
        }
    }*/

    /*
    // Validación
        if (reservation.getStatus().equals("finished") || reservation.getStatus().equals("cancelled")) {
        throw new ValidationException("Esta reserva ya esta finalizada");
    }
    */

    private static void validateServices(String services){
        // Ensure that services are separated by comma and space
        if (!services.matches("^(\\s*\\w+\\s*(,\\s*\\w+\\s*)*)$")) {
            throw new ValidationException("Los servicios deben estar separados por coma y espacio (', ') o puedes ingresar un solo servicio");
        }

        // Convert the string to lowercase for case-insensitive comparison
        String[] servicesArray = services.toLowerCase().split(", ");

        Set<String> uniqueServices = new HashSet<>(Arrays.asList(servicesArray));

        // List of valid services
        List<String> validServices = Arrays.asList("carga", "montaje", "desmontaje", "embalaje", "transporte");

        // Ensure that there are no duplicate services
        if (uniqueServices.size() < servicesArray.length) {
            throw new ValidationException("No se permiten servicios duplicados en la misma reserva");
        }

        for (String service : uniqueServices) {
            if (!validServices.contains(service)) {
                throw new ValidationException("El servicio '" + service + "' no es válido. Los servicios permitidos son: carga, montaje, desmontaje, embalaje, transporte");
            }
        }
    }
}