package swordgit.canadianmortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        Log.v("list1", list.get(0));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.adaptlist, list);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);

    }
}
