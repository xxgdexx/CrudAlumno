package com.cibertec.simulacro_pc02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cibertec.simulacro_pc02.api.ServiceRevistaApi;
import com.cibertec.simulacro_pc02.entity.Revista;
import com.cibertec.simulacro_pc02.util.ConnectionRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    Spinner spnPais , spnFrecuencia, spnEstado;
    Button btnEnviar;

    ServiceRevistaApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = ConnectionRest.getConnection().create(ServiceRevistaApi.class);

        txtNombre  = findViewById(R.id.txtRevistaNombres);
        spnPais  = findViewById(R.id.spnRevistaPais);
        spnFrecuencia  = findViewById(R.id.spnRevistaFrecuencia);
        spnEstado  = findViewById(R.id.spnRevistaEstado);
        btnEnviar =  findViewById(R.id.idbtnEnvAut);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = txtNombre.getText().toString();
                String fre = spnFrecuencia.getSelectedItem().toString();
                String pai = spnPais.getSelectedItem().toString();
                int est = spnEstado.getSelectedItemPosition();

                if (est == 2){ est = 0;}

                Revista obj = new Revista();
                obj.setNombre(nom);
                obj.setFrecuencia(fre);
                obj.setPais(pai);
                obj.setEstado(est);

                inserta(obj);
            }
        });
    }

    public void inserta(Revista obj){
        Call<Revista> call = api.insertaRevistar(obj);
        call.enqueue(new Callback<Revista>() {
            @Override
            public void onResponse(Call<Revista> call, Response<Revista> response) {
                    if (response.isSuccessful()){
                          Revista objSalida = response.body();
                           if (objSalida == null){
                               mensaje("ERROR -> " +   "NO se insertó");
                           }else{
                               mensaje("ÉXITO -> " +   "Se insertó correctamente");
                           }
                    }else{
                        mensaje("ERROR -> " +   "Error en la respuesta");
                    }
            }
            @Override
            public void onFailure(Call<Revista> call, Throwable t) {
                mensaje("ERROR -> " +   t.getMessage());
            }
        });
    }

    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }

}
