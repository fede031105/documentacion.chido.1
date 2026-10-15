package com.upiiz.documentacion.controllers;

import com.upiiz.documentacion.models.TicketSoporte;
import com.upiiz.documentacion.responses.CustomResponse;
import com.upiiz.documentacion.services.TicketSoporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tickets")
@Tag(name = "Tickets", description = "Endpoints para la gestión de Tickets de Soporte")
public class TicketSoporteController {

    @Autowired
    private TicketSoporteService service;

    @Operation(summary = "Obtener todos los tickets", description = "Retorna una lista de todos los tickets registrados.")
    @ApiResponse(responseCode = "200", description = "Recurso encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
    @GetMapping
    public ResponseEntity<CustomResponse<List<TicketSoporte>>> obtenerTodos() {
        try {
            List<TicketSoporte> tickets = service.obtenerTodos();
            return ResponseEntity.ok(new CustomResponse<>(200, "Tickets obtenidos correctamente", tickets));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(500, "Error interno del servidor", null));
        }
    }

    @Operation(summary = "Crear un nuevo ticket", description = "Registra un nuevo ticket de soporte en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Recurso creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomResponse.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos", content = @Content)
    @ApiResponse(responseCode = "409", description = "Conflicto (duplicado)", content = @Content)
    @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    @PostMapping
    public ResponseEntity<CustomResponse<TicketSoporte>> crearTicket(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del ticket a crear", required = true, content = @Content(schema = @Schema(implementation = TicketSoporte.class)))
            @RequestBody TicketSoporte ticket) {
        try {
            if (ticket.getTitulo() == null || ticket.getDescripcion() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CustomResponse<>(400, "Faltan datos obligatorios", null));
            }
            TicketSoporte nuevoTicket = service.guardar(ticket);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomResponse<>(201, "Ticket creado exitosamente", nuevoTicket));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(500, "Error al crear el ticket", null));
        }
    }

    @Operation(summary = "Actualizar un ticket", description = "Actualiza los datos de un ticket existente por su ID.")
    @ApiResponse(responseCode = "200", description = "Recurso actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomResponse.class)))
    @ApiResponse(responseCode = "400", description = "Casos de error (Datos inválidos)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Casos de error (No encontrado)", content = @Content)
    @ApiResponse(responseCode = "409", description = "Casos de error (Conflicto)", content = @Content)
    @ApiResponse(responseCode = "500", description = "Casos de error (Error del servidor)", content = @Content)
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<TicketSoporte>> actualizarTicket(@PathVariable Long id, @RequestBody TicketSoporte ticket) {
        Optional<TicketSoporte> existente = service.obtenerPorId(id);
        if (existente.isPresent()) {
            ticket.setId(id);
            TicketSoporte actualizado = service.guardar(ticket);
            return ResponseEntity.ok(new CustomResponse<>(200, "Ticket actualizado exitosamente", actualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse<>(404, "Ticket no encontrado", null));
        }
    }

    @Operation(summary = "Eliminar un ticket", description = "Elimina de forma lógica o física un ticket por su ID.")
    @ApiResponse(responseCode = "200", description = "Eliminación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomResponse.class)))
    @ApiResponse(responseCode = "204", description = "Eliminación exitosa sin contenido", content = @Content)
    @ApiResponse(responseCode = "400", description = "Casos de error (Petición inválida)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Casos de error (No encontrado)", content = @Content)
    @ApiResponse(responseCode = "500", description = "Casos de error (Error del servidor)", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> eliminarTicket(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.ok(new CustomResponse<>(200, "Ticket eliminado correctamente", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse<>(404, "Ticket no encontrado para eliminar", null));
        }
    }
}