package com.negocio.semana07_recyclerviewconglide_202302.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.negocio.semana07_recyclerviewconglide_202302.R;
import com.negocio.semana07_recyclerviewconglide_202302.entity.Producto;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto> {

    private Context context;
    private List<Producto> lista;

    public ProductoAdapter(@NonNull Context context, int resource, @NonNull List<Producto> lista) {
        super(context, resource, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_item_producto, parent, false);

        Producto objProducto = lista.get(position);

        TextView txtID = row.findViewById(R.id.txtId);
        txtID.setText(String.valueOf(objProducto.getId()));

        TextView txtTitle = row.findViewById(R.id.txtTitle);
        txtTitle.setText(objProducto.getTitle());

        ImageView imgFoto = row.findViewById(R.id.imageProducto);
        Glide.with(context).load(objProducto.getImage()).into(imgFoto);

        return row;
    }
}
