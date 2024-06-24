package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Chat;

public interface IChatService {

    public abstract Long getChatByReservationId(Long reservationId);

    public abstract Chat createChat(Long companyId);

}
