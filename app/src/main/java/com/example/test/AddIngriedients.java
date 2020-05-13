package com.example.test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddIngriedients extends AppCompatActivity {


    MyDataBaseClass objMyDataBaseClasss;
    EditText ingriedientET, amountET;
    Spinner spinner;
    Button show_from_db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ingriedients);

        objMyDataBaseClasss = new MyDataBaseClass(this);

        show_from_db = findViewById(R.id.show_from_db);
        ingriedientET = findViewById(R.id.edit_text_ingriedient);
        amountET = findViewById(R.id.edit_text_amount);
        spinner = findViewById(R.id.spinner);

        ///spinner do wyboru nazwy listy
        loadSpinnerData();



        show_from_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAllIngriedients();
            }
        });
    }



    public void insertIngriedientsIntoDatabase(View view) {
        try {
            SQLiteDatabase objSqLiteDatabasee = objMyDataBaseClasss.getWritableDatabase();
            if (objSqLiteDatabasee != null) {
                if (!ingriedientET.getText().toString().isEmpty() && !amountET.getText().toString().isEmpty() && !spinner.getSelectedItem().toString().isEmpty()) {
                    ContentValues objContentValues = new ContentValues();
                    objContentValues.put("Name", ingriedientET.getText().toString());
                    objContentValues.put("Amount", Integer.parseInt(amountET.getText().toString()));
                    objContentValues.put("NameOfList", spinner.getSelectedItem().toString());

                    long checkIfQueryRuns = objSqLiteDatabasee.insert("grocerylist", null, objContentValues);
//                    objSqLiteDatabasee.insert("groceryList", )
                    if (checkIfQueryRuns != -1) {
                        Toast.makeText(this, "Values inserted", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Please fill everything", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Created db", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "exeption insert db" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    ///// zmieniam na listView do wyswietlania i usuwania ingriedientu
    private void listAllIngriedients(){
        Intent intent3 = new Intent(AddIngriedients.this, ListIngriedients.class);
        intent3.putExtra("lista_przynaleznosc", spinner.getSelectedItem().toString());
        startActivity(intent3);
    }



    public void loadSpinnerData() {
        try {
            // lista z db
            List<String> lables = objMyDataBaseClasss.getAllLabels();

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);

        } catch (Exception e) {
            Toast.makeText(this, "exeption loading name of lists" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
