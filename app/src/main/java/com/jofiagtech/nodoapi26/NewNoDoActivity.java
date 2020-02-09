package com.jofiagtech.nodoapi26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jofiagtech.nodoapi26.util.NoDoRepository;

public class NewNoDoActivity extends AppCompatActivity {

    private EditText noDoEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_no_do);

        noDoEditText = findViewById(R.id.nodo_edit);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                if (TextUtils.isEmpty(noDoEditText.getText())){
                    setResult(RESULT_CANCELED, intent);
                }
                else {
                    String text = noDoEditText.getText().toString();
                    intent.putExtra("nodo", text);
                    setResult(RESULT_OK, intent);
                }

                finish();
            }
        });

    }
}
