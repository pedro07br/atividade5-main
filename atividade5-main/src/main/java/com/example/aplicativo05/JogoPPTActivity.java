package com.example.aplicativo05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class JogoPPTActivity extends AppCompatActivity {

    String[] opcoes = {"Pedra", "Papel", "Tesoura"};
    int jogadorPontos = 0, iaPontos = 0;
    TextView resultado, placar;
    ImageView imgEscolhaIA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_ppt);

        resultado = findViewById(R.id.txtResultado);
        placar = findViewById(R.id.txtPlacar);
        imgEscolhaIA = findViewById(R.id.imgEscolhaIA);

        Button btnPedra = findViewById(R.id.btnPedra);
        Button btnPapel = findViewById(R.id.btnPapel);
        Button btnTesoura = findViewById(R.id.btnTesoura);

        View.OnClickListener listener = v -> {
            String escolhaJogador = ((Button) v).getText().toString();
            String escolhaIA = opcoes[new Random().nextInt(3)];
            verificarResultado(escolhaJogador, escolhaIA);
        };

        btnPedra.setOnClickListener(listener);
        btnPapel.setOnClickListener(listener);
        btnTesoura.setOnClickListener(listener);
    }

    void verificarResultado(String jogador, String ia) {
        if (jogador.equals(ia)) {
            resultado.setText("Empate! Ambos escolheram " + jogador);
        } else if ((jogador.equals("Pedra") && ia.equals("Tesoura")) ||
                (jogador.equals("Papel") && ia.equals("Pedra")) ||
                (jogador.equals("Tesoura") && ia.equals("Papel"))) {
            jogadorPontos++;
            resultado.setText("Você venceu esta rodada! IA escolheu " + ia);
        } else {
            iaPontos++;
            resultado.setText("Você perdeu! IA escolheu " + ia);
        }
        placar.setText("Placar: Você " + jogadorPontos + " x " + iaPontos + " IA");

        if (jogadorPontos == 2 || iaPontos == 2) {
            Intent i = new Intent(this, ResultadoJogoActivity.class);
            i.putExtra("vencedor", jogadorPontos > iaPontos ? "Você venceu!" : "IA venceu!");
            i.putExtra("pontosJogador", jogadorPontos);
            i.putExtra("pontosIA", iaPontos);
            startActivity(i);
        }
    }
}
