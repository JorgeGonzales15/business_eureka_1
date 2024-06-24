package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request.MessageRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response.MessageResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Chat;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Message;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IMessageRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IMessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("messageServiceImpl")
public class MessageServiceImpl implements IMessageService {

    private final IMessageRepository messageRepository;
    private final IChatRepository chatRepository;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(IMessageRepository messageRepository, IChatRepository chatRepository , ModelMapper modelMapper) { //, IChatRepository chatRepository
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageResponseDto createMessage(Long reservationId, MessageRequestDto messageRequestDto) {

        Chat chat = chatRepository.findByReservationId(reservationId); //find the chat by reservationId

        if (chat == null)
            throw new RuntimeException("No existe un chat para la reservation de id " + reservationId);

        var newMessage = modelMapper.map(messageRequestDto, Message.class);

        newMessage.setChat(chat);

        newMessage.setMessageDate(java.time.LocalDateTime.now());

        var createdMessage = messageRepository.save(newMessage);

        return modelMapper.map(createdMessage, MessageResponseDto.class);

    }

    @Override
    public List<MessageResponseDto> getMessagesByReservationId(Long reservationId) {
        // Busca el chat asociado a la reserva
        Chat chat = chatRepository.findByReservationId(reservationId);
        if (chat == null) {
            throw new RuntimeException("No se encontró un chat para la reserva con ID " + reservationId);
        }

        // Obtiene la lista de mensajes asociados al chat
        List<MessageResponseDto> messages = chat.getMessages().stream()
                .map(message -> modelMapper.map(message, MessageResponseDto.class))
                .collect(Collectors.toList());

        return messages;
    }
}
