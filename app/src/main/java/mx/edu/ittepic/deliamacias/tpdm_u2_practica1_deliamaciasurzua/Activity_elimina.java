package mx.edu.ittepic.deliamacias.tpdm_u2_practica1_deliamaciasurzua;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_elimina extends AppCompatActivity {
    EditText valor;
    Button eliminar,regresar;
    Proyectos inf[];
    String descri,ubic,fecha,pres;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimina);
        valor = findViewById(R.id.valor);

        eliminar= findViewById(R.id.btn_eliminar);
        regresar = findViewById(R.id.btn_regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });
    }

    private void consultar() {

        Proyectos proyectos = new Proyectos(this);
        inf = proyectos.consultar(valor.getText().toString());

        if (inf == null){
            Toast.makeText(this, "No se encontró coincidencia",Toast.LENGTH_SHORT).show();
        }else{

           descri = inf[0].getDescripcion();
           ubic = inf[0].getUbicacion();
           fecha = inf[0].getFecha();
           pres = ""+inf[0].getPresupuesto();
           id= inf[0].getId();
        }
        AlertDialog.Builder comprueba = new AlertDialog.Builder(this);
        comprueba.setTitle("Estas Seguro?")
                .setMessage("Deseas Eliminar "+ descri)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eliminar();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();

        inf = null;
    }

    private void eliminar() {
        Proyectos proyectos = new Proyectos(this);
        String mensaje = "";

        boolean respuesta = proyectos.eliminar(new Proyectos(id,descri,ubic,fecha,Float.parseFloat(pres)));

        if(respuesta){
            mensaje = "Se eliminó correctamente el proyecto " + descri;
            valor.setText("");
        } else{
            mensaje = "Error! Algo salió mal: "+proyectos.error;
        }

        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();

    }
}
