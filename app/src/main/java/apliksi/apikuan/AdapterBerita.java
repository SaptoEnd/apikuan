package apliksi.apikuan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ItemViewHolder> {

    Activity activity;
    ArrayList<HashMap<String, String>>daftar;
    HashMap<String,String>data;

    public AdapterBerita(Activity activity, ArrayList<HashMap<String, String>> daftar){
        this.activity = activity;
        this.daftar = daftar;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        data = new HashMap<>();
        data = daftar.get(position);

        final String nama   = data.get(MainActivity.TAG_JSON_USER);
        final String email  = data.get(MainActivity.TAG_JSON_EMAIL);
        //final String judul  = data.get(MainActivity.TAG_JSON_JUDUL);
        //final String desk   = data.get(MainActivity.TAG_JSON_DESK);
        final String id   = data.get(MainActivity.TAG_JSON_ID);

      //  holder.judul.setText(data.get(MainActivity.TAG_JSON_JUDUL));
        holder.nama.setText(data.get(MainActivity.TAG_JSON_USER));
        holder.email.setText(data.get(MainActivity.TAG_JSON_EMAIL));

        holder.judul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, Catatan_imunisasi.class);
                i.putExtra("1", nama);
                i.putExtra("2", email);
               // i.putExtra("3", judul);
                //i.putExtra("4", desk);
                i.putExtra("5", id);
                activity.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return daftar.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView judul,nama,email;
        public ItemViewHolder(View itemView) {
            super(itemView);

           // judul = (TextView) itemView.findViewById(R.id.);
            nama = (TextView) itemView.findViewById(R.id.namamu);
            email = (TextView) itemView.findViewById(R.id.emailmu);
        }
    }
}

