package com.sahilssoft.s_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sahilssoft.s_apps.calculator.S_Calculator;
import com.sahilssoft.s_apps.ocr.S_OCR;
import com.sahilssoft.s_apps.paint.S_Paint;
import com.sahilssoft.s_apps.torch.S_Torch;

public class MainActivity extends AppCompatActivity {

    TextView app_calculator,app_ocr,app_paint,app_torch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app_calculator=findViewById(R.id.app_calculator);
        app_ocr=findViewById(R.id.app_ocr);
        app_paint=findViewById(R.id.app_paint);
        app_torch=findViewById(R.id.app_torch);

        app_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, S_Calculator.class));
            }
        });

        app_ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, S_OCR.class));
            }
        });

        app_paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, S_Paint.class));
            }
        });

        app_torch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, S_Torch.class));
            }
        });

    }
}