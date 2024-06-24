package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {

    private String content;
    private String userType;
}
