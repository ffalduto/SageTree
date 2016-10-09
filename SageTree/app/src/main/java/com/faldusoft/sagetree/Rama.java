package com.faldusoft.sagetree;
import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Context;
import android.widget.Toast;


public class Rama {
    public int idRama;
    public String titulo;
    public String texto;
    public List<Rama> ramas;
    public Button boton;
    public int estado;
    private Activity mainActivity; //esto esta re mal

    public Rama(String tituloInput, String textoInput,Context contexto, Activity mainInput, int id){
        this.titulo = tituloInput;
        this.texto = textoInput;
        this.idRama = id;
        this.mainActivity = mainInput;
        ramas = new ArrayList<Rama>();

        boton = crearBoton(contexto,(LinearLayout)mainActivity.findViewById(R.id.botonera));
    }

    //no deberia recibir nada, pero bueh
    public Button crearBoton(Context contexto, LinearLayout botonera)
    {
        Button myButton = new Button(contexto);
        myButton.setText(this.titulo);
        myButton.setVisibility(View.GONE);
        myButton.setTag(R.string.unaclave,this);

        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Rama rama = (Rama)view.getTag(idRama);
                rama.click();
            }
        });

        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        botonera.addView(myButton, lp);


        return myButton;
    }

    public void click()
    {
        mostrarHijos();
    }

    public void mostrarHijos()
    {
        for (Rama rama : this.ramas )
            rama.boton.setVisibility(View.VISIBLE);
    }

    public void mostrarBoton(){
        this.boton.setVisibility(View.VISIBLE);
        this.estado = View.VISIBLE;
    }
}
