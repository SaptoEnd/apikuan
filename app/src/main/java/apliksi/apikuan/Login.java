package apliksi.apikuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    final static String URL_LOGIN ="http://10.160.1.252/apikuan/login.php";

    EditText nama;
    EditText pass;
    Button   masuk;
    Button   bAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nama = (EditText) findViewById(R.id.login_nama);
        pass = (EditText) findViewById(R.id.login_pass);
        masuk = (Button) findViewById(R.id.btn_login);
        bAkun = (Button) findViewById(R.id.buatAkun);

        bAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(Login.this,Register.class);
                startActivity(r);
            }
        });

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
    }

    private void requestData() {
        //mulai
        final String Lnama = nama.getText().toString();
        final String Lpass = pass.getText().toString();
        //selesai

        //kondisi masuk
        if (nama.getText().toString().equals("")){
            Toast.makeText(Login.this,"input masuh kosong", Toast.LENGTH_SHORT).show();
        }else {
            if (pass.getText().toString().equals("")){
                Toast.makeText(Login.this,"input masih kosong", Toast.LENGTH_SHORT).show();
            }else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                if (s.substring(0,6).equals("sucses")){
                                    Intent pindah = new Intent(Login.this,Navigasi_derawer.class);
                                    pindah.putExtra("id_user", s.substring(6));
                                    startActivity(pindah);
                                    finish();

                                }

                                Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }){
                    //mulai
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();

                        map.put("php_email", Lnama);
                        map.put("php_pass", Lpass);
                        return map;
                    }
                    //selesai

                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);


            }
        }

    }
}
