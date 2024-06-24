package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request.MessageRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response.MessageResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Message Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class MessageController {

    private final IMessageService messageService;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages/{reservationId}")
    public ResponseEntity<MessageResponseDto> createMessage(@PathVariable Long reservationId ,@RequestBody MessageRequestDto messageRequestDto){

        var res = messageService.createMessage(reservationId, messageRequestDto);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtain a list of messages by reservation Id")
    @GetMapping("/messages/{reservationId}")
    public ResponseEntity<List<MessageResponseDto>> getMessagesByReservationId(@PathVariable(name = "reservationId") Long reservationId) {
        var res = messageService.getMessagesByReservationId(reservationId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
