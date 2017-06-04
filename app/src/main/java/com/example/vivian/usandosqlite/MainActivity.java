package com.example.vivian.usandosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etCodigo;
    private EditText etNomeDisciplina;
    private EditText etNota;

    private DatabaseHandler banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etCodigo = (EditText) findViewById(R.id.et_codigo);
        etNomeDisciplina = (EditText) findViewById(R.id.et_nome_disciplina);
        etNota = (EditText) findViewById(R.id.et_nota);

        banco = new DatabaseHandler(this);


    }

    public void btIncluirOnClick(View v){

        try {
            if (etNomeDisciplina.getText().toString().equals("") && etNota.getText().toString().equals("")) {
                etNomeDisciplina.setError("Campo Obrigatório");
                etNomeDisciplina.requestFocus();
                etNota.setError("Campo Obrigatório");
                etNota.requestFocus();
            } else {

            }
            Notas notas = new Notas();
            notas.setNomeDisciplina(etNomeDisciplina.getText().toString());
            notas.setNota(Double.parseDouble(etNota.getText().toString()));

            banco.incluirRegistro(notas);
            etCodigo.setText("");
            etNomeDisciplina.setText("");
            etNota.setText("");
            Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
        return;
    }

    public void btAlterarOnClick(View v){
        try {
            if(etNomeDisciplina.getText().toString().equals("") && etNota.getText().toString().equals("")){
                etNomeDisciplina.setError("Campo Obrigatório");
                etNomeDisciplina.requestFocus();
                etNota.setError("Campo Obrigatório");
                etNota.requestFocus();
            }else{

                int id = Integer.parseInt(etCodigo.getText().toString());
                Notas notas = new Notas();
                notas.set_id(id);
                notas.setNomeDisciplina(etNomeDisciplina.getText().toString());
                notas.setNota(Double.parseDouble(etNota.getText().toString()));

                banco.alterarRegistro(notas);
                Toast.makeText(getApplicationContext(), "Registro alterado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return;
    }

    public void btExcluirOnClick(View v) {
        try {
            if (etNomeDisciplina.getText().toString().equals("") && etNota.getText().toString().equals("")) {
                etNomeDisciplina.setError("Campo Obrigatório");
                etNomeDisciplina.requestFocus();
                etNota.setError("Campo Obrigatório");
                etNota.requestFocus();

            } else {
                int id = Integer.parseInt(etCodigo.getText().toString());
                banco.excluirRegistro(id);
                etCodigo.setText("");
                etNomeDisciplina.setText("");
                etNota.setText("");
                Toast.makeText(getApplicationContext(), "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;

    }
    public void btPesquisarOnClick(View v){

        final EditText etCodPesquisa = new EditText(getApplicationContext());
        etCodPesquisa.setTextColor(Color.BLACK);
        etNomeDisciplina.setError(null);
        etNota.setError(null);

        AlertDialog.Builder telaPesquisa = new AlertDialog.Builder(this);
        telaPesquisa.setTitle("Pesquisa");
        telaPesquisa.setMessage("Informe o código para pesquisa");
        telaPesquisa.setView(etCodPesquisa);
        telaPesquisa.setNegativeButton("Cancelar", null);
        telaPesquisa.setPositiveButton("Pesquisar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                realizarPesquisa(Integer.parseInt(etCodPesquisa.getText().toString()));
            }
        });

        telaPesquisa.show();
    }

    protected void realizarPesquisa(int id) {

        Notas notas = banco.pesquisarRegistro(id);

        if(notas != null){
            etCodigo.setText(String.valueOf(notas.get_id()));
            etNomeDisciplina.setText(notas.getNomeDisciplina());
            etNota.setText(String.valueOf(notas.getNota()));

            Toast.makeText(getApplicationContext(), "Registro encontrado!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Registro não encontrado!", Toast.LENGTH_SHORT).show();
        }

    }

    public void btListarOnClick(View v){
        etNomeDisciplina.setError(null);
        etNota.setError(null);
        startActivity(new Intent(getApplicationContext(), ListaActivity.class));
    }
}
