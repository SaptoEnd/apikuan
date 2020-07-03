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

public class Registerdua extends AppCompatActivity {

    final static String URL_REGISTER = "http://10.160.1.252/apikuan/registerdua.php";

    EditText mail;
    EditText Pass;
    EditText PassD;
    Button   Daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registerdua);


        mail = (EditText) findViewById(R.id.reg_email);
        Pass = (EditText) findViewById(R.id.reg_pass);
        PassD = (EditText) findViewById(R.id.reg_passdua);
        Daftar = (Button) findViewById(R.id.reg_daftar);

        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData();

            }
        });

    }

    //method RequestData
    private void RequestData() {

        final String txtemail = mail.getText().toString();
        final String txtpass = Pass.getText().toString();
        final String txtpassdua = PassD.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_REGISTER,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String s) {
                        if (s.equals("sukses")) {
                            Intent pindah = new Intent(Registerdua.this, Login.class);
                            startActivity(pindah);
                            finish();
                        }
                        Toast.makeText(Registerdua.this, s, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Registerdua.this, volleyError.toString(), Toast.LENGTH_SHORT ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();

                param.put("php_email",txtemail);
                param.put("php_pass",txtpass);
                param.put("php_passdua",txtpassdua);

                return param;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
