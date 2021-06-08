package com.cibertec.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.cibertec.myapplication.adapter.AlumnoAdapter;
import com.cibertec.myapplication.api.ServiceAlumnoApi;
import com.cibertec.myapplication.entity.Alumno;
import com.cibertec.myapplication.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlumnoCrudListaActivity extends AppCompatActivity {

    List<Alumno>lstData= new ArrayList<Alumno>();
    AlumnoAdapter adaptador=null;
    ListView lstView = null;
    ServiceAlumnoApi api=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_crud_lista);

        lstView= findViewById(R.id.idCrudAlumnoList);
        adaptador= new AlumnoAdapter(this, R.layout.activity_alumno_crud_item,lstData);
        lstView.setAdapter(adaptador);

        api= ConnectionRest.getConnection().create(ServiceAlumnoApi.class);
        lista();
    }

    public  void lista(){
        mensaje(  "Log1-->" + "Error en el metodo lista ");

        Call<List<Alumno>> call= api.listaAlumno();
        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                mensaje(  "Log2-->" + "Error en el metodo lista 2");
                if (response.isSuccessful()){
                    mensaje(  "Log3-->" + "Error en el metodo lista 3");

                    List<Alumno>lista= response.body();
                     lstData.clear();
                     lstData.addAll(lista);
                     adaptador.notifyDataSetChanged();
                 } else {
                     mensaje(  "Error-->" + "Error en la respuesta ");
                 }
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                  mensaje(  "Error-->" + "Error en la respuesta ");
            }
        });
    }

    void mensaje(String msg){
        Toast toast1= Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG);
        toast1.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.idMenuCrudAlumno){
            Intent intent = new Intent(this,AlumnoCrudListaActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}