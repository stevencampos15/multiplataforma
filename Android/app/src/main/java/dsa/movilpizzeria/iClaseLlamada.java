package dsa.movilpizzeria;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface iClaseLlamada {
    public void resultadoOk(String tipo, JSONObject obj);
    public void resultadoError(String tipo, VolleyError e);
}
