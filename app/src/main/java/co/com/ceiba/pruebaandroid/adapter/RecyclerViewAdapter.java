package co.com.ceiba.pruebaandroid.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.com.ceiba.pruebaandroid.R;
import co.com.ceiba.pruebaandroid.datos.BaseDeDatos;
import co.com.ceiba.pruebaandroid.datos.estructura.EstructuraArticulo;
import co.com.ceiba.pruebaandroid.dialogos.DialogoEliminarArtriculo;
import co.com.ceiba.pruebaandroid.dominio.modelo.Articulo;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private BaseDeDatos baseDeDatos;

    private ArrayList<Articulo> articulos = new ArrayList<>();

    private Context context;

    private LayoutInflater layoutInflater;

    private AppCompatActivity activity;

    public RecyclerViewAdapter(AppCompatActivity activity, Context context, Cursor cursor) {
        this.activity = activity;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        baseDeDatos = new BaseDeDatos(context);
        cargarDatos(cursor);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = layoutInflater.inflate(R.layout.item_articulo, parent, false);
        return new RecyclerViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        holder.txt_codigo.setText(articulos.get(position).getCodigo());
        holder.txt_descipcion.setText(articulos.get(position).getDescripcion());
        holder.txt_precio.setText("$ "+articulos.get(position).getPrecio());

        holder.btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(position);
            }
        });

        holder.btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private void cargarDatos(Cursor cursor) {

        if (cursor.moveToFirst()) {
            do {
                articulos.add(
                        new Articulo(
                                cursor.getInt(cursor.getColumnIndex(EstructuraArticulo.EstructuraBase.COLUMNA_CODIGO)),
                                cursor.getString(cursor.getColumnIndex(EstructuraArticulo.EstructuraBase.COLUMNA_DESCRIPCION)),
                                cursor.getDouble(cursor.getColumnIndex(EstructuraArticulo.EstructuraBase.COLUMNA_PRECIO))
                        ));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(context, "No hay articulos registrados", Toast.LENGTH_SHORT).show();
        }

    }

    private void modificar(final int posicion) {



    }

    private void eliminar(final int posicion) {
        final DialogoEliminarArtriculo dialogoEliminarArtriculo = new DialogoEliminarArtriculo();

        dialogoEliminarArtriculo.show(activity.getSupportFragmentManager(), "Eliminar Articulo");
        activity.getSupportFragmentManager().executePendingTransactions();

        ((AlertDialog) dialogoEliminarArtriculo.getDialog()).getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SQLiteDatabase sq = baseDeDatos.getWritableDatabase();
                        sq.execSQL("DELETE FROM "
                                + EstructuraArticulo.EstructuraBase.NOMBRE_TABLA + " WHERE "
                                + EstructuraArticulo.EstructuraBase.COLUMNA_CODIGO + " = ?;",
                                new Object[]{articulos.get(posicion).getCodigo()}
                        );

                        Toast.makeText(context, "Articulo eliminado correctamente", Toast.LENGTH_SHORT).show();

                        //((MainActivity) activity).cargarArticulos();
                        dialogoEliminarArtriculo.dismiss();
                    }
                });
    }

}
