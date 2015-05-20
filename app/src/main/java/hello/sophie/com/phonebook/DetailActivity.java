package hello.sophie.com.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SophiesMac on 15. 5. 20..
 */
public class DetailActivity extends Activity implements View.OnClickListener{
    private TextView name;
    private TextView phone;
    private Button buttonCall;
    private Button buttonDelete;
    private List<Person> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = (TextView) findViewById(R.id.detail_name);
        phone = (TextView) findViewById(R.id.detail_phone);
        buttonCall = (Button) findViewById(R.id.call_button);
        buttonCall.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.delete_button);
        //buttonDelete.setOnClickListener(this);
        //this.list = MainActivity.list;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int pos = bundle.getInt("pos");
        Toast.makeText(this, "pos: "+pos,Toast.LENGTH_LONG).show();
        Person person= MainActivity.list.get(pos);
        name.setText(person.getName());
        phone.setText(person.getPhone());

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.call_button :
                //todo 해당번호 전화앱 켜기
                //Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                //startActivityForResult(intent, REQUEST_SEARCH);
                break;
            case R.id.delete_button:
                break;
        }
    }
}
