package model;

import java.time.LocalDateTime;

public class Conversa {
    private int id;
    private int idItem;
    private int idUsuarioInteressado;
    private LocalDateTime dataInicio;
    private String status;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdUsuarioInteressado() {
        return idUsuarioInteressado;
    }

    public void setIdUsuarioInteressado(int idUsuarioInteressado) {
        this.idUsuarioInteressado = idUsuarioInteressado;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
