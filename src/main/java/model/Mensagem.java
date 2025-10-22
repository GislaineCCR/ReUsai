package model;

import java.time.LocalDateTime;

public class Mensagem {
    private int id;
    private int idConversa;
    private int idRemetente;
    private String conteudo;
    private LocalDateTime dataEnvio;
    private String statusLeitura;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConversa() {
        return idConversa;
    }

    public void setIdConversa(int idConversa) {
        this.idConversa = idConversa;
    }

    public int getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(int idRemetente) {
        this.idRemetente = idRemetente;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getStatusLeitura() {
        return statusLeitura;
    }

    public void setStatusLeitura(String statusLeitura) {
        this.statusLeitura = statusLeitura;
    }
}
