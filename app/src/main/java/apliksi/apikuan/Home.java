package apliksi.apikuan;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class Home extends AppCompatActivity {
    private TextView textView;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        textView = (TextView) findViewById(R.id.textView);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                //membuat aksi ke layout fragmant
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if (tabId == R.id.tab_home) {
                    // The tab with id R.id.tab_calls was selected,
                    // change your content accordingly.
                    HomeFragment homefragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.fragment_container,homefragment);
                    fragmentTransaction.commit();

                } else if (tabId == R.id.tab_buku) {
                    ProfilFragmen homefragment = new ProfilFragmen();
                    fragmentTransaction.replace(R.id.fragment_container,homefragment);
                    fragmentTransaction.commit();
                } else if (tabId == R.id.tab_profil) {
                    // The tab with id R.id.tab_chats was selected,
                    // change your content accordingly.
                    Frag_edukasi profil = new Frag_edukasi();
                    fragmentTransaction.replace(R.id.fragment_container,profil);
                    fragmentTransaction.commit();
                }
            }

        });
    }
}
