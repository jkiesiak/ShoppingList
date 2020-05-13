package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteIngriedient extends AppCompatActivity {
    private static final String TAG = "EditDataActivity";
    MyDataBaseClass mDatabase;
    private Button btnSave, btnDelete;
    private EditText editable_item;
    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_layout);
//        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        mDatabase = new MyDataBaseClass(this);
        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", -1); //NOTE: -1 is just the default value
        selectedName = receivedIntent.getStringExtra("name");
        editable_item.setText(selectedName);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mDatabase.deleteName(selectedID, selectedName);
                    editable_item.setText("");
                    Toast.makeText(DeleteIngriedient.this, "Deleted ingriedient: " + selectedName, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DeleteIngriedient.this, "exeption class DeleteIngriedient" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
