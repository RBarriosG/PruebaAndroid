package co.com.ceiba.pruebaandroid.datos.estructura;

import android.provider.BaseColumns;

public final class EstructuraArticulo {

    private EstructuraArticulo() {
    }

    public static abstract class EstructuraBase implements BaseColumns {

        public static final String NOMBRE_TABLA = "articulos";
        public static final String COLUMNA_CODIGO = "codigo";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
        public static final String COLUMNA_PRECIO = "precio";

    }

}
