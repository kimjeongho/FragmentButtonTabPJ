package com.example.kimjh.fragmentbuttontab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {
    TextView messageView;
    EditText inputView;

    public static final String EXTRA_OTHER = "message";
    public static final String RESULT_DATA = "data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        messageView = (TextView)findViewById(R.id.text_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_OTHER);
        messageView.setText(message);

        inputView = (EditText)findViewById(R.id.edit_result);
        Button btn = (Button)findViewById(R.id.btn_finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = inputView.getText().toString();
                Intent data = new Intent();
                data.putExtra(RESULT_DATA,result);
                setResult(Activity.RESULT_OK,data);
                finish();
            }
        });
    }
}
