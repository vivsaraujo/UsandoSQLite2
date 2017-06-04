package com.example.vivian.usandosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "banco";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL("CREATE TABLE notas1 (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome_disciplina TEXT NOT NULL, nota DECIMAL NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int oldVersion, int newVersion) {
        banco.execSQL("DROP TABLE IF EXISTS notas1");
        onCreate(banco);
    }

    public void incluirRegistro(Notas notas){

        SQLiteDatabase banco = this.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nome_disciplina", notas.getNomeDisciplina());
        registro.put("nota", notas.getNota());

        banco.insert("notas1", null, registro);
        banco.close();
    }

    public void alterarRegistro(Notas notas){
        SQLiteDatabase banco = this.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nome_disciplina", notas.getNomeDisciplina());
        registro.put("nota", notas.getNota());

        banco.update("notas1", registro, "_id = " + notas.get_id(), null);
        banco.close();
    }

    public void excluirRegistro(int id){
        SQLiteDatabase banco = this.getWritableDatabase();

        banco.delete("notas1", "_id = ?", new String[] {String.valueOf(id)});
        banco.close();
    }

    public Notas pesquisarRegistro(int id){
        SQLiteDatabase banco = this.getWritableDatabase();

        Cursor registros = banco.query("notas1", null, "_id = " + id , null, null, null, null);

        if(registros.moveToNext()){
            String nomeDisciplina = registros.getString(registros.getColumnIndex("nome_disciplina"));
            double nota = registros.getDouble(registros.getColumnIndex("nota"));

            Notas notas = new Notas();
            notas.set_id(id);
            notas.setNomeDisciplina(nomeDisciplina);
            notas.setNota(nota);

            return notas;
        }else{
            return null;
        }
    }

    public Cursor listarRegistros() {
        SQLiteDatabase banco = this.getWritableDatabase();

        Cursor registros = banco.query("notas1", null, null, null, null, null, null);
        return registros;
    }
}
