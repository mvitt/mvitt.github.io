package com.android.lewis.vitt.matthew.excelapptest;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    HSSFWorkbook workbook;
    HSSFSheet sheet;
    HSSFRow row;
    HSSFCell cell;

    File excelFile;
    FileOutputStream fos = null;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.excelBtn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createExcel();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    private void createExcel(){
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Sheet");
        row = sheet.createRow(0);

        cell = row.createCell(0);
        cell.setCellValue(new HSSFRichTextString("Name"));

        cell = row.createCell(1);
        cell.setCellValue(new HSSFRichTextString("Date"));

        cell = row.createCell(2);
        cell.setCellValue(new HSSFRichTextString("Time"));

        cell = row.createCell(3);
        cell.setCellValue(new HSSFRichTextString("Score 1"));

        cell = row.createCell(4);
        cell.setCellValue(new HSSFRichTextString("Score 2"));

        cell = row.createCell(5);
        cell.setCellValue(new HSSFRichTextString("Score 3"));

        cell = row.createCell(6);
        cell.setCellValue(new HSSFRichTextString("Score 4"));

        cell = row.createCell(7);
        cell.setCellValue(new HSSFRichTextString("Score 5"));

        cell = row.createCell(8);
        cell.setCellValue(new HSSFRichTextString("Score 6"));

        cell = row.createCell(9);
        cell.setCellValue(new HSSFRichTextString("Score 7"));



        row = sheet.createRow(1);

        cell = row.createCell(0);
        cell.setCellValue(new HSSFRichTextString("Bob Hill"));

        cell = row.createCell(1);
        cell.setCellValue(new HSSFRichTextString("3/1/2017"));

        cell = row.createCell(2);
        cell.setCellValue(new HSSFRichTextString("00:21:35"));

        cell = row.createCell(3);
        cell.setCellValue(new HSSFRichTextString("5"));

        cell = row.createCell(4);
        cell.setCellValue(new HSSFRichTextString("3"));

        cell = row.createCell(5);
        cell.setCellValue(new HSSFRichTextString("1"));

        cell = row.createCell(6);
        cell.setCellValue(new HSSFRichTextString("1"));

        cell = row.createCell(7);
        cell.setCellValue(new HSSFRichTextString("4"));

        cell = row.createCell(8);
        cell.setCellValue(new HSSFRichTextString("5"));

        cell = row.createCell(9);
        cell.setCellValue(new HSSFRichTextString("5"));





        sheet.setDefaultColumnWidth(20);


        String fileName = "test.xls";

        excelFile = new File(this.getFilesDir(), fileName);
        try {
            fos = new FileOutputStream(excelFile);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){

                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Uri excelUri = FileProvider.getUriForFile(this, "com.android.lewis.vitt.matthew.excelapptest.excelfileprovider", excelFile);
        Intent emailIntent = createExcelEmailIntent(excelUri);
        Intent chooser = Intent.createChooser(emailIntent, "Choose an Email Application...");
        startActivity(chooser);

    }




    //create email intent that will be sent
    private Intent createExcelEmailIntent(Uri uri) {
        final Intent i = new Intent(Intent.ACTION_SEND);
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[] { "youremail@gmail.com" });
        i.putExtra(Intent.EXTRA_SUBJECT, "Excel Test");
        i.putExtra(Intent.EXTRA_TEXT, "test");
        i.putExtra(Intent.EXTRA_STREAM, uri);
        return i;
    }
}
