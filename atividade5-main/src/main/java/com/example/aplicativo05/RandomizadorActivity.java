package com.example.aplicativo05;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class RandomizadorActivity extends AppCompatActivity {

    EditText inputCategoria, inputItem;
    RecyclerView recyclerView;
    Map<String, List<String>> categorias = new LinkedHashMap<>();
    ArrayAdapter<String> adapter;
    List<String> listaDisplay = new ArrayList<>();
    TextView txtResultado;
    MediaPlayer som;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizador);

        inputCategoria = findViewById(R.id.inputCategoria);
        inputItem = findViewById(R.id.inputItem);
        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        Button btnSortear = findViewById(R.id.btnSortear);
        txtResultado = findViewById(R.id.txtResultado);
        ListView listView = findViewById(R.id.listViewCategorias);

        som = MediaPlayer.create(this, R.raw.som_sorteio);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.resultado_anim);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDisplay);
        listView.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v -> {
            String cat = inputCategoria.getText().toString();
            String item = inputItem.getText().toString();
            if (!cat.isEmpty() && !item.isEmpty()) {
                categorias.putIfAbsent(cat, new ArrayList<>());
                categorias.get(cat).add(item);
                atualizarLista();
                inputItem.setText("");
            }
        });

        btnSortear.setOnClickListener(v -> {
            if (!categorias.isEmpty()) {
                List<String> todosItens = new ArrayList<>();
                for (List<String> itens : categorias.values()) todosItens.addAll(itens);
                if (!todosItens.isEmpty()) {
                    String sorteado = todosItens.get(new Random().nextInt(todosItens.size()));
                    txtResultado.setText(sorteado);
                    txtResultado.startAnimation(anim);
                    som.start();
                }
            }
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            String linha = listaDisplay.get(position);
            String[] partes = linha.split(": ");
            if (partes.length == 2) {
                String categoria = partes[0];
                String item = partes[1];
                categorias.get(categoria).remove(item);
                if (categorias.get(categoria).isEmpty()) categorias.remove(categoria);
                atualizarLista();
            }
            return true;
        });
    }

    void atualizarLista() {
        listaDisplay.clear();
        for (Map.Entry<String, List<String>> entry : categorias.entrySet()) {
            for (String item : entry.getValue()) {
                listaDisplay.add(entry.getKey() + ": " + item);
            }
        }
        adapter.notifyDataSetChanged();
    }
}