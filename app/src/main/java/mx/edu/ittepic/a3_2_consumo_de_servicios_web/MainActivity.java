package mx.edu.ittepic.a3_2_consumo_de_servicios_web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    ConectionWeb con;
    //TextView temp, pres, hm, amanecer;
    TextView id,nom,prac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.txtId);
        nom = findViewById(R.id.txtNombre);
        prac = findViewById(R.id.txtPractica);
        //amanecer = findViewById(R.id.txtAmanecer);
        //puesta = findViewById(R.id.txtPuesta);
        //cord = findViewById(R.id.txtCoordenadas);
        //wather = findViewById(R.id.txtClima);
        fnWeatherConection();
    }

    public void procesarRespuesta(String r) {
        try{
            JSONObject object = new JSONObject(r);
            JSONArray weather = object.getJSONArray("weather");

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < weather.length(); i++) {
                JSONObject objeto = weather.getJSONObject(i);
                String clima = objeto.getString("main");
                String des = objeto.getString("description");
                stringBuilder.append(clima+" : "+des+"         ");
            }
            JSONObject  main= object.getJSONObject("main");
            JSONObject  sys= object.getJSONObject("sys");
            JSONObject  coord= object.getJSONObject("coord");
            id.setText(""+main.getString("_id")+"*F");
            nom.setText(""+main.getString("Nombre"));
            prac.setText(main.getString("Practica"));

            /*amanecer.setText(sys.getString("sunrise"));
            puesta.setText(sys.getString("sunset"));

            cord.setText(coord.getString("lon")+","+coord.getString("lat"));

            wather.setText(""+stringBuilder);*/

        }catch (JSONException e){
            System.out.println(""+e);
        }

    }

    public void fnWeatherConection(){
        try {
            con = new ConectionWeb (MainActivity.this);
            URL direcciopn = new URL("https://earthquake-report.com/feeds/recent-eq?json");
            con.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
//com.google.android.geo.AIzaSyC5fxDpTFx70oI9yVfq5SkGk2eyFmIqAqo
//com.google.android.maps.v2.AIzaSyC5fxDpTFx70oI9yVfq5SkGk2eyFmIqAqo
//https://programacion8web.000webhostapp.com/ConsumosWeb.json
//https://www.google.com/maps/embed/v1/MODE?key=AIzaSyDiuOzL5gGnyczlmZs0sHQCnrVWHnzITB4

