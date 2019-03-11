package mx.edu.ittepic.deliamacias.tpdm_u2_practica1_deliamaciasurzua;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_captura extends AppCompatActivity {
    EditText descripcion,ubicacion,fecha,presupuesto;
    Button guardar,cancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);
        descripcion=findViewById(R.id.descripcion);
        ubicacion=findViewById(R.id.ubicacion);
        fecha=findViewById(R.id.date);
        presupuesto=findViewById(R.id.presupuesto);
        guardar=findViewById(R.id.btn_guardar);
        cancelar=findViewById(R.id.btn_cancelar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    guardar();
                }

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private boolean validar(){
        if(descripcion.getText().toString().equals("")){
            mnj("Escribe una Descripcion");
            return false;

        }
        if(ubicacion.getText().toString().equals("")){
            mnj("Escribe una Ubicación");
            return false;
        }
        return true;
    }

    private void guardar() {

            Proyectos proyectos = new Proyectos(this);
            boolean respuest = proyectos.insertar(new Proyectos(-1,descripcion.getText().toString(),ubicacion.getText().toString(),fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())));
            String mensaje;
            if(respuest){

                mensaje = "Se pudo insertar el nuevo proyecto " + descripcion.getText().toString();
                ubicacion.setText("");
                descripcion.setText("");
                fecha.setText("");
                presupuesto.setText("");
            }else{
                mensaje="Error! no se puede crear un nuevo proyecto, respuesta de SQLite"+proyectos.error;
            }
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            Toast.makeText(this,"Guardando...",Toast.LENGTH_LONG).show();
            alerta.setTitle("ATENCION").setMessage(mensaje).setPositiveButton("ok",null).show();

    }

    private void mnj(String mensaje){
        Toast.makeText(Activity_captura.this,mensaje
        ,Toast.LENGTH_SHORT).show();
    }



}
