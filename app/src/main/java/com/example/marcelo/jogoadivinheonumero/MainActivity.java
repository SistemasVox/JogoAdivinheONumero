package com.example.marcelo.jogoadivinheonumero;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnKeyListener {

    private int numeroSorteado;
    private TextView mensagem, mensagemQTDX;
    private EditText entrada;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preparacaoInicial();
    }

    private void preparacaoInicial() {
        numeroSorteado = (int) (Math.random() * 101);
        mensagem = (TextView) findViewById(R.id.txtInfo);
        entrada = (EditText) findViewById(R.id.editText);
        entrada.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            return true;
        }
        return false;
    }

    private void verificar() {
        int numero = Integer.parseInt(entrada.getText().toString());
        if (numero == numeroSorteado) {
            mensagem.setText(R.string.resposta1);
            caixaDeTexto();
        } else if (numero > numeroSorteado) {
            mensagem.setText(R.string.texto2);
            cont++;

        } else {
            mensagem.setText(R.string.texto3);
            cont++;
        }
    }

    private void caixaDeTexto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Parabéns");

        builder.setMessage("Parabéns, o número era: " + numeroSorteado + ".")
                .setCancelable(false)
                .setPositiveButton("OK (reiniciar)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar (exit)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                        System.exit(0);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void botao(View x) {
        verificar();
        entrada.setText("");
        mensagemQTDX = (TextView) findViewById(R.id.txtQTDX);
        mensagemQTDX.setText(String.valueOf("Tentativas: " + cont + "."));
    }

}