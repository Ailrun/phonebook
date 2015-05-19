package hello.sophie.com.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by SophiesMac on 15. 5. 19..
 */
public class InsertActivity extends Activity implements View.OnClickListener {
    private EditText name;
    private EditText phone;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        name = (EditText) findViewById(R.id.insert_name);
        phone = (EditText) findViewById(R.id.insert_phonenumber);
        buttonSubmit = (Button) findViewById(R.id.insert_button);
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.insert_button :
                String nameStr = name.getText().toString();
                String phoneStr = phone.getText().toString();

                Intent intent = getIntent();
                intent.putExtra("data", nameStr + "/" + phoneStr);
                setResult(412, intent);
                finish();
                break;
        }
    }
}
