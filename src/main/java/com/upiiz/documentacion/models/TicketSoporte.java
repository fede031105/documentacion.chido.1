package com.upiiz.documentacion.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket_soporte")
public class TicketSoporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Identificador único", example = "1")
    private Long id; // BIGINT (PK)[cite: 2]

    @Column(length = 120)
    @Schema(description = "Asunto del ticket", example = "Falla de conexión a internet")
    private String titulo; // VARCHAR(120)[cite: 2]

    @Column(length = 500)
    @Schema(description = "Detalle del problema", example = "El router no asigna IP por DHCP")
    private String descripcion; // VARCHAR(500)[cite: 2]

    @Column(length = 20)
    @Schema(description = "Prioridad (Alta, Media, Baja)", example = "Alta")
    private String prioridad; // VARCHAR(20)[cite: 2]

    @Column(length = 30)
    @Schema(description = "Estado actual (Abierto, Atendiendo, Cerrado)", example = "Abierto")
    private String estado; // VARCHAR(30)[cite: 2]

    @Schema(description = "Fecha de creación", example = "2026-10-14")
    private LocalDate fechaCreacion; // DATE[cite: 2]

    // Constructores, Getters y Setters
    public TicketSoporte() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}