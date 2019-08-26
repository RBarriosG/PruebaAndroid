package co.com.ceiba.pruebaandroid.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import co.com.ceiba.pruebaandroid.datos.estructura.EstructuraArticulo;

public class BaseDeDatos extends SQLiteOpenHelper {

    private static final int VERSION_BASE_DE_DATOS = 1;
    private static final String NOMBRE_BASE_DE_DATOS = "Inventario";

    private static final String SENTENCIA_ARTICULOS = "CREATE TABLE IF NOT EXISTS "
            + EstructuraArticulo.EstructuraBase.NOMBRE_TABLA + " ("
            + EstructuraArticulo.EstructuraBase.COLUMNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EstructuraArticulo.EstructuraBase.COLUMNA_DESCRIPCION + " TEXT NOT NULL, "
            + EstructuraArticulo.EstructuraBase.COLUMNA_PRECIO + " NUMBER); ";

    public BaseDeDatos(Context context){
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SENTENCIA_ARTICULOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SENTENCIA_ARTICULOS);
    }
}
