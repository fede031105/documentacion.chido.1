package com.upiiz.documentacion.responses;

public class CustomResponse<T> {
    private int estado;
    private String mensaje;
    private T valores;

    public CustomResponse(int estado, String mensaje, T valores) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.valores = valores;
    }

    // Getters y Setters
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public T getValores() { return valores; }
    public void setValores(T valores) { this.valores = valores; }
}