package apliksi.apikuan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class Catatan_imunisasi extends AppCompatActivity {

    FloatingActionButton fab;
    TextView emptyText;
    RecyclerView list;

    static String URL_DAFTAR = "http://10.160.1.252/praktikum/daftar_berita.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catatan_imunisasi_layout);




    }



}

