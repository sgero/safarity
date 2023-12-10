package com.example.safarity.repository;

import com.example.safarity.model.Participante;
import com.example.safarity.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllByParticipanteAndActivoTrue(Participante participante);

}
