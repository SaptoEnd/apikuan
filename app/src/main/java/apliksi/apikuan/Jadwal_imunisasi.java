package apliksi.apikuan;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Jadwal_imunisasi extends AppCompatActivity {

    final static String URL_JADWAL ="http://10.160.1.252/apikuan/login.php";

    DateFormat formatDateTime = DateFormat.getDateInstance();
    DateFormat Time = DateFormat.getTimeInstance();

    Calendar dateTime = Calendar.getInstance();

    public EditText vaksin;
    public EditText catatan;
    private TextView text;
    private Button btn_date;
    private Button btn_time;
     Button ket;
     Button lihat;
    private Button simpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_imunisasi);




        btn_date = (Button) findViewById(R.id.btn_datePicker);
        btn_time = (Button) findViewById(R.id.btn_timePicker);
        ket = (Button) findViewById(R.id.keterangan);
        lihat = (Button) findViewById(R.id.lihat_catatan);
        simpan = (Button) findViewById(R.id.text_simpan);
        vaksin = (EditText) findViewById(R.id.l_vaksin);
        catatan = (EditText) findViewById(R.id.text_jadwal);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aks = new Intent(Jadwal_imunisasi.this,Navigasi_derawer.class);

            }
        });

        ket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aks1 = new Intent(Jadwal_imunisasi.this,Keterangan.class);
                startActivity(aks1);
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

        updateTextLabel();
    }

    private void updateDate() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };

    private void updateTextLabel() {
        btn_date.setText(formatDateTime.format(dateTime.getTime()));


    }

    private void updateLabel() {
        btn_time.setText(Time.format(dateTime.getTime()));
    }

    public void requestData(){

        //mulai
        final String Ldate = btn_date.getText().toString();
        final String Ltime = btn_time.getText().toString();
        final String Lihat = lihat.getText().toString();
        final String Lcat = catatan.getText().toString();
        final String Lvak = vaksin.getText().toString();
        //selesai

        //kondisi masuk
        if (btn_date.getText().toString().equals("")){
            Toast.makeText(Jadwal_imunisasi.this,"input masuh kosong", Toast.LENGTH_SHORT).show();
        }else if (btn_date.getText().toString().equals("")){
            Toast.makeText(Jadwal_imunisasi.this,"input masuh kosong", Toast.LENGTH_SHORT).show();
        }else if (catatan.getText().toString().equals("")){
            Toast.makeText(Jadwal_imunisasi.this,"input masuh kosong", Toast.LENGTH_SHORT).show();
           Toast.makeText(Jadwal_imunisasi.this,"input masuh kosong", Toast.LENGTH_SHORT).show();
        }else {
            if (vaksin.getText().toString().equals("")){
                Toast.makeText(Jadwal_imunisasi.this,"input masih kosong", Toast.LENGTH_SHORT).show();
            }else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        URL_JADWAL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                if (s.substring(0,6).equals("sucses")){
                                    Intent pindah = new Intent(Jadwal_imunisasi.this,Navigasi_derawer.class);
                                    pindah.putExtra("id_user", s.substring(6));
                                    startActivity(pindah);
                                    finish();

                                }

                                Toast.makeText(Jadwal_imunisasi.this, s, Toast.LENGTH_SHORT).show();

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

                        map.put("php_email", Ldate);
                        map.put("php_pass", Ltime);
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

