package com.example.vivian.usandosqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaActivity extends AppCompatActivity {

    private ListView lvRegistros;
    private DatabaseHandler banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvRegistros = (ListView) findViewById(R.id.lv_Listar);
        banco = new DatabaseHandler(this);
        montarListaRegistro();
    }

    @SuppressWarnings("deprecation")
    private void montarListaRegistro() {

        Cursor resistros = banco.listarRegistros();
        String nomeCamposTabela[] = new String[] {"nome_disciplina", "nota"};
        int nomeCamposTela[] = new int[] {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                android.R.layout.two_line_list_item,
                resistros,
                nomeCamposTabela,
                nomeCamposTela);

        lvRegistros.setAdapter(adapter);
    }

}
