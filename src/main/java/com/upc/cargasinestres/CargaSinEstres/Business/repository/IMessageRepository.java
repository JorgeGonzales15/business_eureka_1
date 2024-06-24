package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Message entities.
 * @author Grupo1
 * @version 1.0
 * */
public interface IMessageRepository extends JpaRepository<Message, Long> {



}
