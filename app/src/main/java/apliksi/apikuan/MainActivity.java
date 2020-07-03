package apliksi.apikuan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    TextView emptyText;
    RecyclerView list;

    static String URL_DAFTAR = "http://localhost/praktikum/daftar_berita.php";

    public static String TAG_JSON_PREGISTER = "pregister";
    public static String TAG_JSON_SUKSES = "json_sukses";

    public static String TAG_JSON_ID = "json_id";
    public static String TAG_JSON_USER = "json_user";
    public static String TAG_JSON_EMAIL = "json_email";
    public static String TAG_JSON_PASS = "json_pass";


    ArrayList<HashMap<String, String>> daftar = new ArrayList<>();
    AdapterBerita adapterBerita;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        final String id_user = i.getStringExtra("id_user");

        /**fab = (FloatingActionButton) findViewById(R.id.fab);
         emptyText = (TextView) findViewById(R.id.empty_text);
         list = (RecyclerView) findViewById(R.id.list); **/

        list.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        list.setItemAnimator(new DefaultItemAnimator());

        setAdapterBerita(this, daftar);
        requiceData();

        /**fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, TambahActivity.class);
        i.putExtra("id_user", id_user);
        startActivity(i);
        }
        });**/

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setAdapterBerita(Activity activity, ArrayList<HashMap<String, String>> daftar) {
        adapterBerita = new AdapterBerita(activity, daftar);
        list.setAdapter(adapterBerita);
    }

    private void requiceData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL_DAFTAR,
                null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {
                            int sukses = jsonObject.getInt(TAG_JSON_SUKSES);

                            if (sukses == 1) {
                                emptyText.setText("");

                                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON_PREGISTER);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);

                                    HashMap<String, String> map = new HashMap<>();
                                    map.put(TAG_JSON_ID, json.getString("json_id"));
                                    map.put(TAG_JSON_USER, json.getString("json_user"));
                                    map.put(TAG_JSON_EMAIL, json.getString("json_email"));
                                    map.put(TAG_JSON_PASS, json.getString("json_judul"));


                                    daftar.add(map);
                                    adapterBerita.notifyDataSetChanged();


                                }

                            } else {
                                emptyText.setText("Data tidak ada");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        ConfigVolley.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    //mengambil fungsi menu
    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
    }**/

    //mengambil fungsi refres
    /**@Override
    public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == R.id.rpres) {
    Intent i = new Intent(MainActivity.this, MainActivity.class);

    startActivity(i);

    }

    return true;
    }**/

}

