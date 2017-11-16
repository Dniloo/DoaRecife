package com.example.doarecife.doarecife.model;

/**
 * Created by jose mario on 13/11/2017.
 */

public class ItemDoacao {

    String local;
    String tipo;
    int quantidade;
    String foto;
    long id;


    public ItemDoacao() {
    }

    @Override
    public String toString() {
        return "local='" + getLocal() + "\n" +
                "tipo='" + getTipo() + "\n" +
                "quantidade='" + getQuantidade();
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



}
