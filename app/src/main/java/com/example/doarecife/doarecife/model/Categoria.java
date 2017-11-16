package com.example.doarecife.doarecife.model;

import java.util.List;

/**
 * Created by jose mario on 13/11/2017.
 */

public class Categoria {

    String nome;
    List<Item> itemList;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
