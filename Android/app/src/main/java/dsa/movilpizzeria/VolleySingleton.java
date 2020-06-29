package dsa.movilpizzeria;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton volleySgl;
    private RequestQueue requestQueue;
    private static Context contexto;

    private VolleySingleton(Context ctx){
        this.volleySgl.contexto = ctx;
        this.requestQueue = this.getRequestQueue();
    }
    public static synchronized VolleySingleton getInstancia(Context _ctx){
        if(volleySgl==null){
            volleySgl = new VolleySingleton(_ctx);
        }
        return volleySgl;
    }
    public RequestQueue getRequestQueue(){
        if(this.requestQueue==null){
            this.requestQueue = Volley.newRequestQueue(contexto.getApplicationContext());
        }
        return this.requestQueue;
    }
    public <T> void addToQueue(Request<T> rq){
        this.getRequestQueue().add(rq);
    }
}
