package com.example.aplicativo05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnCifra = findViewById(R.id.btnCifra);
        Button btnJogo = findViewById(R.id.btnJogo);
        Button btnCodigo = findViewById(R.id.btnCodigo);
        Button btnRandom = findViewById(R.id.btnRandom);

        btnCifra.setOnClickListener(v -> startActivity(new Intent(this, CifraActivity.class)));
        btnJogo.setOnClickListener(v -> startActivity(new Intent(this, JogoPPTActivity.class)));
        btnCodigo.setOnClickListener(v -> startActivity(new Intent(this, CodigoSecretoActivity.class)));
        btnRandom.setOnClickListener(v -> startActivity(new Intent(this, RandomizadorActivity.class)));
    }
}