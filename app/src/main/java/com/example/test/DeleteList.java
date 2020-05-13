package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteList extends AppCompatActivity {
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
        selectedName = receivedIntent.getStringExtra("list");
        editable_item.setText(selectedName);

//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String item = editable_item.getText().toString();
//                if (!item.equals("")) {
//                    mDatabase.updateName(item, selectedID, selectedName);
//                } else {
////                    toastMessage("You must enter a name");
//                    Toast.makeText(DeleteIngriedient.this, "No ID associated with that name", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.deleteList(selectedID, selectedName);
                editable_item.setText("");
                Toast.makeText(DeleteList.this, "Deleted list: " + selectedName, Toast.LENGTH_SHORT).show();
            }
        });


//    public void deleteIngriedient() {
//        try {
//            mDatabase.deleteName(selectedID, selectedName);
//            editable_item.setText("");
//            Toast.makeText(DeleteIngriedient.this, "No ID associated with that name", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(DeleteIngriedient.this, "exeption class DeleteIngriedient" + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }


    }
}
