package hello.sophie.com.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by SophiesMac on 15. 5. 20..
 */
public class DetailActivity extends Activity implements View.OnClickListener{
    private TextView name;
    private TextView phone;
    private String phoneStr;
    private Button buttonCall;
    private Button buttonDelete;
    private List<Person> list;
    private List<Person> list_seasrched;
    private int pos=0;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = (TextView) findViewById(R.id.detail_name);
        phone = (TextView) findViewById(R.id.detail_phone);
        buttonCall = (Button) findViewById(R.id.call_button);
        buttonCall.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.delete_button);
        buttonDelete.setOnClickListener(this);
        this.list = MainActivity.list;
        this.list_seasrched = MainActivity.list_searched;

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        pos = bundle.getInt("pos");

        Toast.makeText(this, "pos: "+pos,Toast.LENGTH_LONG).show();
        //todo 검색중이면, list가 아니라 list_searched에서 가져오기.
        boolean isSearching = bundle.getBoolean("isSearching");
        Person person;
        if(isSearching){
            person = this.list_seasrched.get(pos);
        }else{
            person= this.list.get(pos);
        }

        phoneStr = person.getPhone();
        name.setText(person.getName());
        phone.setText(person.getPhone());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.call_button :
                Toast.makeText(this,"call",Toast.LENGTH_SHORT).show();
                //todo 해당번호 전화앱 켜기
                call(phoneStr);
                //Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                //startActivityForResult(intent, REQUEST_SEARCH);
                break;
            case R.id.delete_button:
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
                this.list.remove(pos);
                setResult(2220, intent);
                finish();
                break;
        }
    }

    public void call(String phoneStr) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneStr));
        startActivity(callIntent);
    }
}
