package mx.edu.ittepic.deliamacias.tpdm_u2_practica1_deliamaciasurzua;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PROYECTOS(IDPROYECTO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, DESCRIPCION VARCHAR(200) NOT NULL,UBICACION VARCHAR(200), FECHA DATE, PRESUPUESTO FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
