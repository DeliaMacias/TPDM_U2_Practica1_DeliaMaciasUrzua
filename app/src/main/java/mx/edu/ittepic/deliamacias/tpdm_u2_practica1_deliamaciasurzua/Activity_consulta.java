package mx.edu.ittepic.deliamacias.tpdm_u2_practica1_deliamaciasurzua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_consulta extends AppCompatActivity {
    EditText valor;
    TextView informacion;
    Button consultar,regresar;
    Proyectos inf[];
    String valores ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        valor = findViewById(R.id.valor);
        informacion = findViewById(R.id.info);
        regresar = findViewById(R.id.regresar);
        consultar = findViewById(R.id.consultar);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
                regresar.setVisibility(View.VISIBLE);
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void consultar() {
        Proyectos proyectos = new Proyectos(this);
        inf = proyectos.consultar(valor.getText().toString());


        informacion.setText("");

        if (inf == null){
            Toast.makeText(this, "No se encontró coincidencia",Toast.LENGTH_SHORT).show();
        }else{
            String d,u,f,p;
            d = "DESCRIPCIÓN: "+inf[0].getDescripcion();
            u = "UBICACIÓN: "+inf[0].getUbicacion();
            f = "FECHA: "+ inf[0].getFecha();
            p = "PRESUPUESTO: $"+ inf[0].getPresupuesto();
            valores = d + "\n"+u+"\n"+f+"\n"+p+"\n";
        }
        informacion.setText(valores);
        inf = null;

    }
}
