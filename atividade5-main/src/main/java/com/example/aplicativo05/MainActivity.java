package com.example.aplicativo05;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nome = findViewById(R.id.txtNome);
        TextView matricula = findViewById(R.id.txtMatricula);
        TextView curso = findViewById(R.id.txtCurso);
        ImageView imagem = findViewById(R.id.imgPerfil);
        Button btnEntrar = findViewById(R.id.btnEntrar);

        nome.setText("Nome: Roberta Souza e Gabriela Braga");
        matricula.setText("Matrícula: 20232012000124\nMatrícula: 20232012000132");
        curso.setText("Curso: Análise e Desenvolvimento de Sistemas");

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.botao_animado);
        btnEntrar.startAnimation(anim);

        btnEntrar.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
        });
    }
}


