package com.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNListView, btnCListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }

    private void initControls() {
        btnNListView = (Button) findViewById(R.id.btnNListView);
        btnNListView.setOnClickListener(this);
        btnCListView = (Button) findViewById(R.id.btnCListView);
        btnCListView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {

            case R.id.btnNListView:

                intent = new Intent(this, ListViewActivity.class);
                intent.putExtra("isNormalList", true);
                startActivity(intent);

                break;

            case R.id.btnCListView:

                intent = new Intent(this, ListViewActivity.class);
                intent.putExtra("isNormalList", false);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
