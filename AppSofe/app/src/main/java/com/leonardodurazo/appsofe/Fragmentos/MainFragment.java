package com.leonardodurazo.appsofe.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.leonardodurazo.appsofe.Actividades.InsertActivity;
import com.leonardodurazo.appsofe.Model.todos;
import com.leonardodurazo.appsofe.R;
import com.melnykov.fab.FloatingActionButton;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Leonardo Durazo on 19/11/2015.
 */
public class MainFragment extends Fragment {
    public static final  String TAG= MainFragment.class.getSimpleName();
    private TodosAdapter adapter;
    private RecyclerView lista;
    private RecyclerView.LayoutManager lManager;

    com.melnykov.fab.FloatingActionButton fab;

    private Gson gson = new Gson();  //libreria que convertira los datos de texto traidos del JSON y convertirlos en objetos
    public MainFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_main, container, false);

        lista=(RecyclerView) v.findViewById(R.id.reciclador);
        lista.setHasFixedSize(true);
        lManager= new LinearLayoutManager(getActivity());
        lista.setLayoutManager(lManager);
        cargarAdaptador();

        fab=(FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(
                                getActivity(), InsertActivity.class
                        ), 3
                );
            }
        });

        return v;

    }


    //Con este metodo cargaremos cada uno de los objetos con la clase ProductosAdapter
    public void cargarAdaptador() {
        //Construimos el objeto cliente en formato JSON

        HttpClient httpClient = new DefaultHttpClient();

        HttpGet del =
                new HttpGet("http://api.mylist.io/v1/todos");

        del.setHeader("content-type", "application/json");

        try
        {
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());

            JSONArray respJSON = new JSONArray(respStr);

            String[] clientes = new String[respJSON.length()];

            for(int i=0; i<respJSON.length(); i++)
            {
                JSONObject obj = respJSON.getJSONObject(i);

                int idCli = obj.getInt("Id");
                String nombCli = obj.getString("Nombre");
                int telefCli = obj.getInt("Telefono");

                clientes[i] = "" + idCli + "-" + nombCli + "-" + telefCli;
            }

            //Rellenamos la lista con los resultados
            /*ArrayAdapter<String> adaptador =
                    new ArrayAdapter<String>(ServicioWebRest.this,
                            android.R.layout.simple_list_item_1, clientes);


            lstClientes.setAdapter(adaptador);*/
        }
        catch(Exception ex)
        {
            Log.e("ServicioRest","Error!", ex);
        }


    }

}
