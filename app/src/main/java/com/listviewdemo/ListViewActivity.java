package com.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private ListNormalAdapter listNormalAdapter;
    private ListCustomizedAdapter listCustomizedAdapter;
    private String[] mobileArray = {"Samsung", "Apple", "Microsoft", "Nokia", "Sony", "LG", "HTC", "Motorola", "Lava", "Oppo", "Asus", "Ericsson", "Haier", "Intex", "Micromax"};
    private int[] mobileIconArray = {R.drawable.img_samsung, R.drawable.img_apple, R.drawable.img_microsoft, R.drawable.img_nokia, R.drawable.img_sony, R.drawable.img_lg, R.drawable.img_htc, R.drawable.img_moto, R.drawable.img_lava, R.drawable.img_oppo, R.drawable.img_asus, R.drawable.img_ericsson, R.drawable.img_haier, R.drawable.img_intex, R.drawable.img_micromax};
    private int selectedPosition = -1;
    private boolean isCustomizedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initControls();
    }

    private void initControls() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isCustomizedList) {
                    selectedPosition = i;
                    listCustomizedAdapter.notifyDataSetChanged();
                }
                Toast.makeText(ListViewActivity.this, "You have selected [" + mobileArray[i] + "]", Toast.LENGTH_SHORT).show();
            }
        });
        listNormalAdapter = new ListNormalAdapter(mobileArray);
        listCustomizedAdapter = new ListCustomizedAdapter(mobileArray);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getBoolean("isNormalList")) {
                listView.setAdapter(listNormalAdapter);
                isCustomizedList = false;
            } else {
                isCustomizedList = true;
                listView.setAdapter(listCustomizedAdapter);
            }
        }
    }

    public class ListNormalAdapter extends BaseAdapter {

        private final String[] mobileNames;
        private LayoutInflater inflater;

        public ListNormalAdapter(String[] strings) {
            this.mobileNames = strings;
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mobileNames.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            ViewHolder holder;

            if (convertView == null) {

                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.row_normal_list, null);

                holder.textName = (TextView) convertView.findViewById(R.id.textName);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();

            }

            holder.textName.setText(mobileNames[position]);

            return convertView;
        }

        public class ViewHolder {
            private TextView textName;
        }

    }

    // Customized List

    public class ListCustomizedAdapter extends BaseAdapter {

        private final String[] mobileNames;
        private LayoutInflater inflater;

        public ListCustomizedAdapter(String[] strings) {
            this.mobileNames = strings;
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mobileNames.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            ViewHolder holder;

            if (convertView == null) {

                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.row_customized_list, null);

                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.imgCheck = (ImageView) convertView.findViewById(R.id.imgCheck);
                holder.imgLogo = (ImageView) convertView.findViewById(R.id.imgLogo);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();

            }

            holder.textName.setText(mobileNames[position]);
            holder.imgLogo.setImageResource(mobileIconArray[position]);

            if (selectedPosition == position) {
                holder.imgCheck.setVisibility(View.VISIBLE);
            } else {
                holder.imgCheck.setVisibility(View.GONE);
            }

            return convertView;
        }

        public class ViewHolder {
            private TextView textName;
            private ImageView imgCheck, imgLogo;
        }

    }
}
