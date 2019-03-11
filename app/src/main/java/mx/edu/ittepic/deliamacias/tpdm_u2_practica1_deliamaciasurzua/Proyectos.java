package mx.edu.ittepic.deliamacias.tpdm_u2_practica1_deliamaciasurzua;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;


public class Proyectos {
    private BaseDatos base;
    private int id;
    private String descripcion;
    private String ubicacion;
    private String fecha;
    private Float presupuesto;
    protected  String error;

    public Proyectos(Activity activity) {
        base = new BaseDatos(activity,"civil",null,1);
    }
    public Proyectos(int id, String descripcion, String ubicacion, String fecha, float presupuesto){
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.presupuesto = presupuesto;
    }


    public boolean insertar(Proyectos proyectos){
        try{
            SQLiteDatabase t_insertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION",proyectos.getDescripcion());
            datos.put("UBICACION",proyectos.getUbicacion());
            datos.put("FECHA",proyectos.getFecha());
            datos.put("PRESUPUESTO",proyectos.getPresupuesto());
            long resultado = t_insertar.insert("PROYECTOS","IDPROYECTO",datos);

            Log.d("notice","Si entró");
            t_insertar.close();

            if (resultado== -1){
                return false;
            }

        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return  true;
    }
    public Proyectos[] consultar(){
        Proyectos[] resultado = null;
        try{
            SQLiteDatabase t_consultar = base.getReadableDatabase();
            Cursor c = t_consultar.rawQuery("SELECT * FROM PROYECTOS",null);
            if(c.moveToFirst()){
                resultado = new Proyectos[c.getCount()];
                int i=0;
                do{
                    int id = c.getInt(0);
                    String descripcion = c.getString(1);
                    String ubicacion = c.getString(2);
                    String fecha = c.getString(3);
                    float presupuesto = c.getFloat(4);
                    resultado[i] = new Proyectos(id,descripcion,ubicacion,fecha,presupuesto);
                    i++;

                }while (c.moveToNext());
            }else{
                error = "Error! No hay datos que mostrar.";
            }
            t_consultar.close();
        }catch (SQLiteException e){
            error = e.getMessage();
            return  null;
        }
        return resultado;
    }
    public Proyectos[] consultar(String clave){
        Proyectos[] resultado = null;

        try{
            SQLiteDatabase t_consultar = base.getReadableDatabase();
            String[] vector = {clave,clave};
            Cursor c = t_consultar.rawQuery("SELECT * FROM PROYECTOS WHERE DESCRIPCION = ? OR IDPROYECTO = ?", vector);


            if(c.moveToFirst()){
                resultado = new Proyectos[c.getCount()];
                int i = 0;
                do{
                    int id = c.getInt(0);
                    String descripcion = c.getString(1);
                    String ubicacion = c.getString(2);
                    String fecha = c.getString(3);
                    float presupuesto = c.getFloat(4);
                    resultado[i] = new Proyectos(id,descripcion,ubicacion,fecha,presupuesto);
                    i++;

                }while (c.moveToNext());
            }else{
                error = "Error! No hay datos que mostrar.";
            }
            t_consultar.close();
        }catch(SQLiteException e){
            error = e.getMessage();
            return null;
        }
        return resultado;

    }
    public boolean actualizar(Proyectos proyectos){

        try{
            String[] vector = {proyectos.getId()+""};
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyectos.getDescripcion());
            datos.put("UBICACION", proyectos.getUbicacion());
            datos.put("FECHA",proyectos.getFecha());
            datos.put("PRESUPUESTO",proyectos.getPresupuesto());

            SQLiteDatabase t_actualizar = base.getWritableDatabase();

            long resultado = t_actualizar.update("PROYECTOS", datos,"IDPROYECTO = ?",vector);

            t_actualizar.close();

            if(resultado == -1){
                return false;
            }

        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }

        return true;
    }

    public boolean eliminar (Proyectos proyectos){
        int resultado;
        try{
            String[] vector = {proyectos.getId()+""};
            SQLiteDatabase t_eliminar = base.getWritableDatabase();

            resultado = t_eliminar.delete("PROYECTOS", "IDPROYECTO =?", vector );

            t_eliminar.close();

            if(resultado==-1){
                return false;
            }
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }

        return resultado>0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Float presupuesto) {
        this.presupuesto = presupuesto;
    }
}
