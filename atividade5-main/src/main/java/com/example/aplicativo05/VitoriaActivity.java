package com.example.aplicativo05;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VitoriaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitoria);

        long tempo = getIntent().getLongExtra("tempo", 0);
        int tentativas = getIntent().getIntExtra("tentativas", 0);

        TextView txt = findViewById(R.id.txtVitoria);
        txt.setText("Você acertou!\nTempo: " + (tempo / 1000) + " segundos\nTentativas: " + tentativas);
    }
}
