package com.example.sharedpreferencesnac2s;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private String fileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //chamada dos elementos de tela em objetos
        final EditText contentBox = findViewById(R.id.contextBox);
        final EditText keyWord = findViewById(R.id.keyWord);
        Button btLimpar = findViewById(R.id.btLimpar);
        Button btSalvar = findViewById(R.id.btSalvar);
        Button btRecuperar = findViewById(R.id.btRecuperar);

        //obtendo caminho de gravação do arquivos
        this.fileName = getApplicationContext().getFilesDir().getPath() + "/";

        //listeners de tocar no botão para disparar eventos
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentBox.setText("");
                keyWord.setText("");
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyWord.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Digite a palavra chave!", Toast.LENGTH_SHORT).show();
                }
                else{
                    String completeFN  = fileName + keyWord.getText().toString();
                    String content = contentBox.getText().toString();
                    MainActivity.this.gravaDadosArquivo(completeFN,content);
                }
            }
        });

        btRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyWord.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Digite a Palavra-Chave!", Toast.LENGTH_SHORT).show();
                }
                else{
                    String completeFN  = fileName + keyWord.getText().toString();
                    String content = recuperaDadosArquivo(completeFN);
                    contentBox.setText(content);
                }
            }
        });
    }

    public void gravaDadosArquivo(String fileName,String data){
        try{
            OutputStreamWriter bufferSaida = new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8");
            bufferSaida.write(data);
            bufferSaida.close();
            Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
        }
        catch(FileNotFoundException e){
            Toast.makeText(this, "Falha na abertura!", Toast.LENGTH_SHORT).show();
        }
        catch (UnsupportedEncodingException e){
            Toast.makeText(this, "Falha na codificação!", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(this, "Falha na escrita!", Toast.LENGTH_SHORT).show();
        }
    }

    public String recuperaDadosArquivo(String fileName){

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String linha = bufferedReader.readLine();

            while(linha != null){
                sb.append("\n");
                sb.append(linha);
                linha = bufferedReader.readLine();
            }

            Toast.makeText(this, "Recuperado!", Toast.LENGTH_SHORT).show();
            return sb.toString();

        }
        catch(FileNotFoundException e){
            Toast.makeText(this, "Arquivo não encontrado", Toast.LENGTH_SHORT).show();
        }
        catch (UnsupportedEncodingException e){
            Toast.makeText(this, "Falha na codificação", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(this, "Falha na leitura", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

}