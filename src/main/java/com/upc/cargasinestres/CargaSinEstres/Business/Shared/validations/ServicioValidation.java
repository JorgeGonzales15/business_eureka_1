package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.request.ServicioRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;

public class ServicioValidation {
    public static void ValidateServicio(ServicioRequestDto servicioRequestDto) {
        if (servicioRequestDto.getName() == null || servicioRequestDto.getName().isEmpty()) {
            throw new ValidationException("El nombre del servicio es obligatorio"); //error 400
        }

        // Validar que el tipo de servicio sea uno de los permitidos
        String[] tiposPermitidos = {"carga", "embalaje", "desmontaje", "montaje", "transporte"};
        String tipoServicio = servicioRequestDto.getName().toLowerCase();

        if (!isValidTipoServicio(tipoServicio, tiposPermitidos)) {
            throw new ValidationException("Tipo de servicio no válido. Los tipos permitidos son: carga, embalaje, desmontaje, montaje, transporte"); // Error 400
        }
    }

    private static boolean isValidTipoServicio(String tipoServicio, String[] tiposPermitidos) {
        for (String tipo : tiposPermitidos) {
            if (tipoServicio.equals(tipo)) {
                return true;
            }
        }
        return false;
    }
}