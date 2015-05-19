package hello.sophie.com.phonebook;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private ListView listview;
    private Button buttonInsert;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.main_listview);
        buttonInsert = (Button) findViewById(R.id.main_button);
        buttonInsert.setOnClickListener(this);

        //listview adapter arrayList
        //http://blog.naver.com/aro004/80203876954

        /* list = new ArrayList<String>();
        list.add("우정");
        list.add("광");
        list.add("kyu"); */

        //this: 현재 액티비티
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.main_button :
                /*String input = editText.getText().toString();
                list.add(input);
                adapter.notifyDataSetChanged();*/
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivityForResult(intent, 1061);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1061 && resultCode == 412) {
            String str = data.getStringExtra("data");
            list.add(str);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
