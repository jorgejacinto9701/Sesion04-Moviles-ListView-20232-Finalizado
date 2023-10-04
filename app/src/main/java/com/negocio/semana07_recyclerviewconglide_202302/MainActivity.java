package com.negocio.semana07_recyclerviewconglide_202302;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.negocio.semana07_recyclerviewconglide_202302.adapter.ProductoAdapter;
import com.negocio.semana07_recyclerviewconglide_202302.entity.Producto;
import com.negocio.semana07_recyclerviewconglide_202302.service.ServiceProducto;
import com.negocio.semana07_recyclerviewconglide_202302.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Button btnListar;

    ListView lstProductos;
    ArrayList<Producto> data = new ArrayList<>();
    ProductoAdapter adapatador;

    ServiceProducto serviceProducto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListar = findViewById(R.id.btnLista);

        lstProductos = findViewById(R.id.lstProductos);
        adapatador = new ProductoAdapter(this, R.layout.activity_item_producto, data);
        lstProductos.setAdapter(adapatador);

        serviceProducto = ConnectionRest.getConnection().create(ServiceProducto.class);

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaProductos();
            }
        });

    }
    public void listaProductos(){
        Call<List<Producto>> call = serviceProducto.listaProducto();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()){
                    List<Producto> lstSalida = response.body();
                    //mensajeAlert(""+lstSalida.size());
                    data.clear();;
                    data.addAll(lstSalida);
                    adapatador.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });


    }


    public void mensajeAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public void mensajeToastLong(String msg){
        Toast toast1 =  Toast.makeText(this,msg, Toast.LENGTH_LONG);
        toast1.show();
    }
    public void mensajeToastShort(String msg){
        Toast toast1 =  Toast.makeText(this,msg, Toast.LENGTH_SHORT);
        toast1.show();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

}