package com.example.tugas7_sqlite;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Kapal> Kapal;
    public CustomListAdapter(Activity activity, List<Kapal> Kapal) {
        this.activity = activity;
        this.Kapal = Kapal;
    }
    @Override
    public int getCount() {
        return Kapal.size();
    }
    @Override
    public Object getItem(int location) {
        return Kapal.get(location);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_list, null);
        TextView nama = (TextView)
                convertView.findViewById(R.id.text_nama);
        TextView kelas = (TextView)
                convertView.findViewById(R.id.text_jenis);
        ImageView imageView = (ImageView)
                convertView.findViewById(R.id.iconid);
        Kapal k = Kapal.get(position);
        nama.setText("Nama Kapal : "+ k.get_nama());
        kelas.setText("Jenis Kapal : "+ k.get_jenis());
        return convertView;
    }
}

