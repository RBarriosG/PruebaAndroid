package co.com.ceiba.pruebaandroid.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import co.com.ceiba.pruebaandroid.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView txt_codigo;

    TextView txt_descipcion;

    TextView txt_precio;

    ImageButton btn_eliminar;

    ImageButton btn_modificar;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_codigo = itemView.findViewById(R.id.txt_codigo);
        txt_descipcion = itemView.findViewById(R.id.txt_descripcion);
        txt_precio = itemView.findViewById(R.id.txt_precio);

        btn_eliminar = itemView.findViewById(R.id.btn_eliminar);
        btn_modificar = itemView.findViewById(R.id.btn_modificar);
    }
}
