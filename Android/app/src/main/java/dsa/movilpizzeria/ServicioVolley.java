package dsa.movilpizzeria;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dsa.movilpizzeria.entidad.Detallespedido;
import dsa.movilpizzeria.entidad.Pedidos;
import dsa.movilpizzeria.entidad.Usuarios;

public class ServicioVolley {
    private iClaseLlamada claseLlamada;
    private Context contexto;

    public ServicioVolley(iClaseLlamada claseLlamada, Context contexto) {
        this.claseLlamada = claseLlamada;
        this.contexto = contexto;
    }

    public void llamadaPost(final String tipo, String url){
        try {
            //RequestQueue cola = Volley.newRequestQueue(this.contexto);
            VolleySingleton sg = VolleySingleton.getInstancia(this.contexto);
            JsonObjectRequest obj = new JsonObjectRequest(
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (claseLlamada != null) {
                                try {
                                    response.put("status","okPost");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                claseLlamada.resultadoOk(tipo, response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(claseLlamada!=null){
                        claseLlamada.resultadoError(tipo, error);
                    }
                }
            }
            );
            //cola.add(obj);
            sg.addToQueue(obj);
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void guardarUsuario(String url, Usuarios usuario){
        final Usuarios us = usuario;
        RequestQueue queue = VolleySingleton.getInstancia(this.contexto).getRequestQueue();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("nombreCliente", us.getNombreCliente());
        params.put("apellidoCliente", us.getApellidoCliente());
        params.put("documento", us.getDocumento());
        params.put("username", us.getUsername());
        params.put("pwd", us.getPwd());
        Map<String,String> tipoUsuario = new HashMap<String, String>();
        tipoUsuario.put("idTipoUsuario", String.valueOf(us.getTipousuarios().getIdTipoUsuario()));
        tipoUsuario.put("nombreTipoUsuario", us.getTipousuarios().getNombreTipoUsuario());
        params.put("tipousuarios", tipoUsuario);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("JSONPost", response.toString());
                        //pDialog.hide();
                        if (claseLlamada != null) {
                            claseLlamada.resultadoOk("", response);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("JSONPost", "Error: " + error.getMessage());
                //pDialog.hide();
                if(claseLlamada!=null){
                    claseLlamada.resultadoError("", error);
                }
            }
        });


        queue.add(jsonObjReq);
    }

    public void guardarDetallesPedido(String url, Detallespedido detalles){
        final Detallespedido dp = detalles;
        RequestQueue queue = VolleySingleton.getInstancia(this.contexto).getRequestQueue();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("cantidad", dp.getCantidad());
        //Items
        Map<String,String> items = new HashMap<String, String>();
        items.put("idItem", String.valueOf(dp.getItems().getIdItem()));
        items.put("nombreItem", dp.getItems().getNombreItem());
        params.put("items", items);

        //Pedidos
        Map<String,String> pedidos = new HashMap<String, String>();
        pedidos.put("idPedido", String.valueOf(dp.getPedidos().getIdPedido()));
        params.put("pedidos", pedidos);
        System.out.println(new JSONObject(params));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("JSONPost", response.toString());
                        //pDialog.hide();
                        if (claseLlamada != null) {
                            try {
                                response.put("status","okDetalle");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            claseLlamada.resultadoOk("", response);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("JSONPost", "Error: " + error.getMessage());
                //pDialog.hide();
                if(claseLlamada!=null){
                    claseLlamada.resultadoError("", error);
                }
            }
        });
        queue.add(jsonObjReq);
    }

    public void guardarPedido(String url, Pedidos pedido){
        final Pedidos p = pedido;
        RequestQueue queue = VolleySingleton.getInstancia(this.contexto).getRequestQueue();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("fecha", p.getFecha());
        //Usuarios
        Map<String,String> usuarios = new HashMap<String, String>();
        usuarios.put("idUsuario", String.valueOf(p.getUsuarios().getIdUsuario()));
        usuarios.put("nombreCliente", p.getUsuarios().getNombreCliente());
        params.put("usuarios", usuarios);
        System.out.println(new JSONObject(params));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("JSONPost", response.toString());
                        //pDialog.hide();
                        if (claseLlamada != null) {
                            try {
                                response.put("status","okPedido");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            claseLlamada.resultadoOk("", response);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("JSONPost", "Error: " + error.getMessage());
                //pDialog.hide();
                if(claseLlamada!=null){
                    claseLlamada.resultadoError("", error);
                }
            }
        });
        queue.add(jsonObjReq);
    }

}
