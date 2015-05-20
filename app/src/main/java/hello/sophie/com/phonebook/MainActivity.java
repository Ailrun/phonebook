package hello.sophie.com.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, SearchView.OnQueryTextListener {
    private final int REQUEST_SEARCH = 1111;
    private final int RESULT_SEARCH = 1110;
    private final int REQUEST_DETAIL = 2222;
    private final int RESULT_DETAIL = 2220;

    private ListView listview;
    private Button buttonInsert;
    private SearchView searchView;

    private boolean isAfterSearch;

    //origianl
    private ArrayAdapter<Person> adapter;
    public static ArrayList<Person> list;

    //tmp searched
    private ArrayList<Person> list_searched;
    private ArrayAdapter<Person> adapter_searched;

    @Override
    public boolean onQueryTextSubmit(String query) {
        list_searched = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            if (list.get(i).getName().contains(query)){
                //Toast.makeText(this, list.get(i), Toast.LENGTH_SHORT).show();
                list_searched.add(list.get(i));
            }
        }
        adapter_searched = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_searched);
        listview.setAdapter(adapter_searched);
        isAfterSearch = true;
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.main_listview);
        buttonInsert = (Button) findViewById(R.id.main_button);
        searchView = (SearchView) findViewById(R.id.main_searchview);
        buttonInsert.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);

        isAfterSearch = false;

        list = new ArrayList<>();
        list.add(new Person("Mary Adams", "01012345678"));
        list.add(new Person("Jane Smith","01022223333"));
        list.add(new Person("Kim","01011112222"));
        list.add(new Person("Josh Lee","01022224444"));

        //this: 현재 액티비티
        adapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new ListViewItemClickListener());
    }

    private class ListViewItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(MainActivity.this, id +", "+position, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("pos",position);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_DETAIL);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.main_button :
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivityForResult(intent, REQUEST_SEARCH);
                break;
            //todo 1: listView의 원소를 짧게 누르면, 상세페이지로 넘기기.
            //http://blog.naver.com/2hyoin/220352336443
            //todo2: listView의 원소를 길게 누르면, 전화로 연결.
            //todo 3: 왼쪽으로 슬라이드 or 길게누르면, (팝업 후,) 삭제.
        }
    }

    @Override
    public void onBackPressed() {
        if (isAfterSearch == true) {
            listview.setAdapter(adapter);
            isAfterSearch = false;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SEARCH && resultCode == RESULT_SEARCH ) {
            String nameStr = data.getStringExtra("name");
            String phoneStr = data.getStringExtra("phone");
            list.add(new Person(nameStr, phoneStr));
            adapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_DETAIL && resultCode == 2220){
            adapter.notifyDataSetChanged();//데이터 변경됨을 알림.
            listview.invalidate();// 다시 그리기
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
