package com.example.aplicativo05;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoJogoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_jogo);

        String vencedor = getIntent().getStringExtra("vencedor");
        int pontosJogador = getIntent().getIntExtra("pontosJogador", 0);
        int pontosIA = getIntent().getIntExtra("pontosIA", 0);

        TextView txtResultado = findViewById(R.id.txtResultadoFinal);
        TextView txtPlacar = findViewById(R.id.txtPlacarFinal);
        ImageView imgIcone = findViewById(R.id.imgIconeResultado);

        txtResultado.setText(vencedor);
        txtPlacar.setText("Placar final: Você " + pontosJogador + " x " + pontosIA + " IA");

        if (vencedor.contains("Você")) {
            imgIcone.setImageResource(R.drawable.ic_placeholder2);
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.resultado_anim);
            imgIcone.startAnimation(anim);
        } else if (vencedor.contains("IA")) {
            imgIcone.setImageResource(R.drawable.ic_placeholder3);
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.resultado_derrota);
            imgIcone.startAnimation(anim);
        } else {
            imgIcone.setImageResource(R.drawable.ic_placeholder4);
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.resultado_empate);
            imgIcone.startAnimation(anim);
        }
    }
}
