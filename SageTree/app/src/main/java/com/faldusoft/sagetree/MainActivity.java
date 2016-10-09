package com.faldusoft.sagetree;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;


import android.widget.LinearLayout;
import android.app.ActionBar.LayoutParams;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearBotonera();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void crearBotonera() {
        String jsonContent = this.ObtenerJson();
        List<Rama> tronco = new ArrayList<>();
        JSONObject troncoJson;
        int id = 0;

        try {
            troncoJson = new JSONObject(jsonContent);
            JSONArray ramasJson = troncoJson.getJSONArray("ramas");

            tronco = ObtenerRamas(ramasJson,id);

            for (Rama rama: tronco)
                rama.mostrarBoton();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private String ObtenerJson() {
        return getResources().getString(R.string.jsonPrueba);
    }

    private List<Rama> ObtenerRamas(JSONArray ramasJson, int id) {
        List<Rama> respuesta = new ArrayList<Rama>();

        try {

            for (int i = 0; i < ramasJson.length(); i++) {
                JSONObject ramaActual = ramasJson.getJSONObject(i);

                String titulo = ramaActual.getString("titulo");
                String texto = ramaActual.getString("texto");

                Rama ramaNueva = new Rama(titulo,texto,this,this,id);
                id++;
                ramaNueva.ramas = ObtenerRamas(ramaActual.getJSONArray("ramas"),id);

                respuesta.add(ramaNueva);
            }

        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }

        return respuesta;
    }
}
