package swordgit.canadianmortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //retrieve the passed payment list
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        //setup adapter and apply to list view which is a list created in activity_result
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.adaptlist, list);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);

    }
}
