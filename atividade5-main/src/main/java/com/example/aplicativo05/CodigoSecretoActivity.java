package com.example.aplicativo05;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CodigoSecretoActivity extends AppCompatActivity {

    String codigo;
    long tempoInicial;
    List<String> tentativas = new ArrayList<>();
    RecyclerView recyclerView;
    TentativasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_secreto);

        codigo = gerarCodigo();
        tempoInicial = SystemClock.elapsedRealtime();

        EditText input = findViewById(R.id.editTentativa);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        recyclerView = findViewById(R.id.recyclerTentativas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TentativasAdapter(tentativas);
        recyclerView.setAdapter(adapter);

        btnEnviar.setOnClickListener(v -> {
            String tentativa = input.getText().toString();
            if (tentativa.length() == 4) {
                String resultado = verificarTentativa(tentativa);
                tentativas.add(tentativa + " → " + resultado);
                adapter.notifyItemInserted(tentativas.size() - 1);
                if (tentativa.equals(codigo)) {
                    long tempoTotal = SystemClock.elapsedRealtime() - tempoInicial;
                    Intent i = new Intent(this, VitoriaActivity.class);
                    i.putExtra("tempo", tempoTotal);
                    i.putExtra("tentativas", tentativas.size());
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    String gerarCodigo() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) sb.append(r.nextInt(10));
        return sb.toString();
    }

    String verificarTentativa(String tentativa) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (tentativa.charAt(i) == codigo.charAt(i)) sb.append("✔️ ");
            else if (codigo.contains(tentativa.charAt(i) + "")) sb.append("🔄 ");
            else sb.append("❌ ");
        }
        return sb.toString();
    }
}
