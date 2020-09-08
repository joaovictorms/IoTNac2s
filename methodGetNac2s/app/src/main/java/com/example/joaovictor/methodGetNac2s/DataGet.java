package com.example.joaovictor.methodGetNac2s;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DataGet extends AsyncTask<String,Void,String> {

    private TextView txtTitle;
    private TextView txtCompleted;
    private TextView txtMensagem;

    public DataGet(TextView txtTitle, TextView txtCompleted, TextView txtMensagem) {
        this.txtTitle = txtTitle;
        this.txtCompleted = txtCompleted;
        this.txtMensagem = txtMensagem;

        txtMensagem.setText("Pesquisando...");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {

        String url = strings[0];
        String result = NetworkToolkit.doGet(url);


        return result;
    }

    @Override
    protected void onPostExecute(String s) {

        try{
            JSONObject jsonResponse = new JSONObject(s);

            //JSONObject dataResponse = jsonResponse.getJSONObject("");

            String title = jsonResponse.getString("title");
            String completed = jsonResponse.getString("completed");

            txtTitle.setText(title);
            txtCompleted.setText(completed);

            txtMensagem.setText("Encontrado!");

        }
        catch(JSONException e){
            txtMensagem.setText("Nenhum registro encontrado!");
            this.txtTitle.setText("erroJSON");
            this.txtCompleted.setText("");
        }
    }
}

