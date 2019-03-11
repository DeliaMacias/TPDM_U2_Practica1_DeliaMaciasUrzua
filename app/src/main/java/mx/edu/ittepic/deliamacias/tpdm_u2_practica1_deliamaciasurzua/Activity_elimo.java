package mx.edu.ittepic.deliamacias.tpdm_u2_practica1_deliamaciasurzua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_elimo extends AppCompatActivity {
    EditText valor,descripcion,ubicacion,fecha,presupuesto;
    Button buscar,modificar,cancelar;
    Proyectos inf[];
    String d,u,f,p;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimo);

        valor= findViewById(R.id.valor);
        descripcion= findViewById(R.id.descripcion);
        ubicacion= findViewById(R.id.ubicacion);
        fecha= findViewById(R.id.date);
        presupuesto= findViewById(R.id.presupuesto);
        buscar= findViewById(R.id.btn_buscar);
        modificar= findViewById(R.id.btn_modificar);
        cancelar= findViewById(R.id.btn_cancelar);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                consultar();
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });
    }

    private void actualizar() {
        Proyectos proyectos = new Proyectos(this);


        boolean respuesta = proyectos.actualizar(new Proyectos(id,descripcion.getText().toString(),ubicacion.getText().toString(),fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())));
        if(respuesta){
            Toast.makeText(this,"Se actualizó correctamente el Proyecto "+descripcion.getText().toString(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error! algo salió mal: "+proyectos.error,Toast.LENGTH_LONG).show();
        }

    }

    private void consultar() {

        Proyectos proyectos = new Proyectos(this);
        inf = proyectos.consultar(valor.getText().toString());

        if (inf == null){
            Toast.makeText(this, "No se encontró coincidencia",Toast.LENGTH_SHORT).show();
        }else{
            id = inf[0].getId();
            d = inf[0].getDescripcion();
            u = inf[0].getUbicacion();
            f = inf[0].getFecha();
            p =""+inf[0].getPresupuesto();



        }
        descripcion.setText(""+d);
        ubicacion.setText(""+u);
        fecha.setText(""+f);
        presupuesto.setText(""+p);
        inf = null;
    }
}
