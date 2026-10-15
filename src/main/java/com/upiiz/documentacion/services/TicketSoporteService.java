package com.upiiz.documentacion.services;

import com.upiiz.documentacion.models.TicketSoporte;
import com.upiiz.documentacion.repositories.TicketSoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketSoporteService {

    @Autowired
    private TicketSoporteRepository repository;

    public List<TicketSoporte> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<TicketSoporte> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public TicketSoporte guardar(TicketSoporte ticket) {
        return repository.save(ticket);
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}