package com.example.aplicativo05;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CifraActivity extends AppCompatActivity {

    EditText inputTexto, inputChave;
    TextView txtResultado;
    Button btnCodificar, btnDecodificar;
    MediaPlayer som;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cifra);

        inputTexto = findViewById(R.id.inputTexto);
        inputChave = findViewById(R.id.inputChave);
        txtResultado = findViewById(R.id.txtResultado);
        btnCodificar = findViewById(R.id.btnCodificar);
        btnDecodificar = findViewById(R.id.btnDecodificar);

        anim = AnimationUtils.loadAnimation(this, R.anim.resultado_anim);
        som = MediaPlayer.create(this, R.raw.som_suave);

        btnCodificar.setOnClickListener(v -> processar(true));
        btnDecodificar.setOnClickListener(v -> processar(false));
    }

    private void processar(boolean codificar) {
        String texto = inputTexto.getText().toString();
        String chaveStr = inputChave.getText().toString();

        if (texto.isEmpty() || chaveStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int chave = Integer.parseInt(chaveStr);
            if (!codificar) chave = -chave;
            String resultado = cifraDeCesar(texto, chave);
            txtResultado.setText(resultado);
            txtResultado.startAnimation(anim);
            som.start();
            Log.d("CifraActivity", "Resultado: " + resultado);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Chave inválida", Toast.LENGTH_SHORT).show();
        }
    }

    private String cifraDeCesar(String texto, int chave) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int offset = (c - base + chave + 26) % 26;
                resultado.append((char) (base + offset));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}
