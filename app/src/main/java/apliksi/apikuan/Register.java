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

public class Register extends AppCompatActivity {

    final static String URL_REGISTER = "http://10.160.1.252/apikuan/register.php" ;

    EditText nDepan;
    EditText nBelakang;
    Button   nxSatu;
    Button   keLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        nDepan = (EditText) findViewById(R.id.namaD);
        nBelakang = (EditText) findViewById(R.id.namaB);

        keLogin = (Button) findViewById(R.id.nextLogin);
        nxSatu = (Button) findViewById(R.id.nextSatu);

        keLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(Register.this,Login.class);
                startActivity(r);
            }
        });

        nxSatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData();

            }
        });

    }

    //method RequestData
    private void RequestData() {
        final String txtnama = nDepan.getText().toString();
        final String txtnamab = nBelakang.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_REGISTER,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String s) {
                        if (s.equals("sukses")) {
                            Intent pindah = new Intent(Register.this, Registerdua.class);
                            startActivity(pindah);
                            finish();
                        }
                        Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Register.this, volleyError.toString(), Toast.LENGTH_SHORT ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("php_nama",txtnama);
                param.put("php_namab",txtnamab);

                return param;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
