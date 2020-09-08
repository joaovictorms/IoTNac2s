package com.example.joaovictor.methodGetNac2s;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joaovictor.methodGetNac2s.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void methodGet(View view) {

        String response;
        String url = "https://jsonplaceholder.typicode.com/todos/";

        EditText txtGet = findViewById(R.id.editTextPesquisa);
        url += txtGet.getText().toString();

        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtCompleted = findViewById(R.id.txtCompleted);
        TextView txtMensagem = findViewById(R.id.viewMensagem);

        //NetworkToolkit.getJSONFromAPI(url);

        new DataGet(txtTitle, txtCompleted, txtMensagem).execute(url);

    }

}
