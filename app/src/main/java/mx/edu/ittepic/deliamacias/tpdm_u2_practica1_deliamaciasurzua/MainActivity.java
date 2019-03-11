package mx.edu.ittepic.deliamacias.tpdm_u2_practica1_deliamaciasurzua;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Proyectos vector[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.ListaObras);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    mostrarAlert(i);
                }catch(Exception e){

                }
            }
        });
    }

    private void mostrarAlert(final int i) {
        AlertDialog.Builder alertar = new AlertDialog.Builder(this);
        alertar.setTitle("Selecciona")
                .setMessage("Deseas modificar?"+vector[i].getDescripcion()+"?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        invocaEliminarActualizar(i);
                    }
                })
                .setNegativeButton("No",null)
                .show();

    }

    private void invocaEliminarActualizar(int i) {
        Intent eliminaActualiza = new Intent(this,Activity_captura.class);
        eliminaActualiza.putExtra("IDPROYECTO",vector[i].getId());
        eliminaActualiza.putExtra("DESCRIPCION",vector[i].getDescripcion());
        eliminaActualiza.putExtra("UBICACION",vector[i].getUbicacion());
        eliminaActualiza.putExtra("FECHA",vector[i].getFecha());
        eliminaActualiza.putExtra("PRESUPUESTO",vector[i].getPresupuesto());
    }


    @Override
    protected void onStart() {
        super.onStart();
        Proyectos proyectos = new Proyectos(this);
        Proyectos vector[] = proyectos.consultar();
        String[] info = null;

        if(vector == null){
            Toast.makeText(this,"nullllll?",Toast.LENGTH_SHORT).show();
            info = new String[1];
            info[0] = "No hay Proyectos registrados aun";
        }else{
            info = new String[vector.length];
            for (int i=0;i<vector.length;i++){
                Proyectos temp = vector[i];
                info[i] = temp.getDescripcion()+"\n ";
            }

        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,info);
        lista.setAdapter(adaptador);

    }

    //Método para crear el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Metodo por si se selecciona algun item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertar:

                Intent nuevoProyecto = new Intent(this,Activity_captura.class);
                startActivity(nuevoProyecto);
                break;
            case R.id.consultar:
                Toast.makeText(this,"Funciona",Toast.LENGTH_LONG).show();
                Intent consu = new Intent(this,Activity_consulta.class);
                startActivity(consu);
                break;
            case R.id.modificar:

                Intent modi = new Intent(this,Activity_elimo.class);
                startActivity(modi);
                break;
            case R.id.eliminar:
                Intent elim = new Intent(this,Activity_elimina.class);
                startActivity(elim);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
