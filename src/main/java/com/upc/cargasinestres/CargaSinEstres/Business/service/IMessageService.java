package com.upc.cargasinestres.CargaSinEstres.Business.service;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request.MessageRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response.MessageResponseDto;

import java.util.List;

public interface IMessageService {

    /**
     * Create a new Message with the information provided as a parameter
     * @param reservationId The booking id
     * @return The created message information
     */
    public abstract MessageResponseDto createMessage(Long reservationId, MessageRequestDto messageRequestDto);

    List<MessageResponseDto> getMessagesByReservationId(Long reservationId);
}
