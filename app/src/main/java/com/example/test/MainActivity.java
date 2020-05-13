package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button_generate, button_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_generate = findViewById(R.id.button_generate);
        button_create = findViewById(R.id.button_create);

        ///////// nowa lista
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_manually_list();
            }
        });

        ///////// lista z pref
//        button_generate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                print_to_PDF();
//            }
//        });
    }


    private void add_manually_list() {
        Intent intent2 = new Intent(MainActivity.this, AddList.class);
        startActivity(intent2);
    }
//     tu bedzie odpowiednia klasa ktora powstanie
//    private void generate_list() {
//        Intent intent = new Intent(MainActivity.this, AddList.class);
//        startActivity(intent);
//    }


//    public void print_to_PDF(View view){
//        String query = "Select Text from PDFTable where SerialNumber=" + editTextSerialNumberFetch.getText().toString();
//        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
//
//        try {
//            cursor.moveToFirst();
//            textViewDisplay.setText(cursor.getString(0));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            textViewDisplay.setText("");
//            return;
//        }
//
//        PdfDocument pdfDocument = new PdfDocument();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600,1).create();
//        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//        page.getCanvas().drawText(cursor.getString(0),10, 25, new Paint());
//        pdfDocument.finishPage(page);
//        String filePath = Environment.getExternalStorageDirectory().getPath()+"/Download/"+editTextSerialNumberFetch.getText().toString()+".pdf";
//        File file = new File(filePath);
//        try {
//            pdfDocument.writeTo(new FileOutputStream(file));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        pdfDocument.close();
//    }
}

