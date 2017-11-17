package com.example.doarecife.doarecife.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doarecife.doarecife.model.ItemDoacao;

import java.util.ArrayList;
import java.util.List;


public class DoarDAO {

    private Context mContext;

    public DoarDAO (Context context) {
        this.mContext = context;
    }
    public long inserir (ItemDoacao itemDoacao){
        DoarDbHelper doarDbHelper = new DoarDbHelper(mContext);
        //sabendo se o banco existe
        SQLiteDatabase db = doarDbHelper.getWritableDatabase();
        ContentValues values = valuesFromDoar(itemDoacao);
        long id = db.insert(DoarContract.TABLE_NAME, null, values);
        itemDoacao.setId(id);
        db.close();
        return id;
    }
    public long atualizar (ItemDoacao itemDoacao){

        DoarDbHelper doarDbHelper = new DoarDbHelper(mContext);
        SQLiteDatabase db = doarDbHelper.getWritableDatabase();

        ContentValues values = valuesFromDoar(itemDoacao);

        int rowsAffected = db.update(DoarContract.TABLE_NAME, values,
                DoarContract._ID +" = ?",
                new String[]{
                        String.valueOf(itemDoacao.getId())
                });
        db.close();

        return rowsAffected;
    }
    public long excluir (ItemDoacao itemDoacao){
        DoarDbHelper doarDbHelper = new DoarDbHelper(mContext);

        SQLiteDatabase db = doarDbHelper.getWritableDatabase();

        int rowsAffected = db.delete(DoarContract.TABLE_NAME,
                                        DoarContract.LOCAL +" = ?",
                                        new String[]{
                                                itemDoacao.getLocal()
                                        });
        db.close();
        return rowsAffected;
    }
    public List<ItemDoacao> listar(){
        DoarDbHelper doarDbHelper = new DoarDbHelper(mContext);
        SQLiteDatabase db = doarDbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ DoarContract.TABLE_NAME, null);
        List<ItemDoacao> itemDoacaos = new ArrayList<>();
        if (cursor.getCount()>0){
            int idxId = cursor.getColumnIndex(DoarContract._ID);
            int idxLocal = cursor.getColumnIndex(DoarContract.LOCAL);
            int idxTipo = cursor.getColumnIndex(DoarContract.TIPO);
            int idxQuantidade = cursor.getColumnIndex(DoarContract.QUANTIDADE);
            int idxFoto = cursor.getColumnIndex(DoarContract.FOTO);

            while (cursor.moveToNext()){
                ItemDoacao itemDoacao = new ItemDoacao();
                itemDoacao.setId(cursor.getLong(idxId));
                itemDoacao.setLocal(cursor.getString(idxLocal));
                itemDoacao.setTipo(cursor.getString(idxTipo));
                itemDoacao.setQuantidade(cursor.getInt(idxQuantidade));
                itemDoacao.setFoto(cursor.getString(idxFoto));

                itemDoacaos.add(itemDoacao);
            }
        }
        cursor.close();
        db.close();
        return itemDoacaos;

    }
    public boolean isFavorito(ItemDoacao itemDoacao){

        DoarDbHelper doarDbHelper = new DoarDbHelper(mContext);
        SQLiteDatabase db = doarDbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery(
                    "SELECT count(*) from " + DoarContract.TABLE_NAME +
                            "WHERE " + DoarContract.LOCAL + " = ?",
                    new String[]{itemDoacao.getLocal()});

            boolean existe = false;
        if (cursor != null){
            cursor.moveToNext();
            existe = cursor.getInt(0) > 0;
            cursor.close();

        }
        return existe;

    }


    private ContentValues valuesFromDoar(ItemDoacao itemDoacao){
        ContentValues values = new ContentValues();
        values.put(DoarContract.LOCAL, itemDoacao.getLocal());
        values.put(DoarContract.TIPO, itemDoacao.getTipo());
        values.put(DoarContract.QUANTIDADE, itemDoacao.getQuantidade());
        values.put(DoarContract.FOTO, itemDoacao.getFoto());

        return values;
    }

}
