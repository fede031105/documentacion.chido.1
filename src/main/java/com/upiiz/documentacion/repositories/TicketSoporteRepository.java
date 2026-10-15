package com.upiiz.documentacion.repositories;

import com.upiiz.documentacion.models.TicketSoporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketSoporteRepository extends JpaRepository<TicketSoporte, Long> {
}