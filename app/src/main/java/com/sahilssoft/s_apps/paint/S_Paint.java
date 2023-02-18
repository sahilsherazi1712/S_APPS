package com.sahilssoft.s_apps.paint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.kyanogen.signatureview.SignatureView;
import com.sahilssoft.s_apps.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;

public class S_Paint extends AppCompatActivity {

    int defaultColor;
    SignatureView signatureView;
    ImageButton imgErazer,imgColor,imgSave;
    SeekBar seekBar;
    TextView txtPenSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaint);

        signatureView=findViewById(R.id.signature_view);
        imgErazer=findViewById(R.id.btnErazer);
        imgColor=findViewById(R.id.btnColor);
        imgSave=findViewById(R.id.btnSave);
        seekBar=findViewById(R.id.penSize);
        txtPenSize=findViewById(R.id.txtPenSize);

        askPermission();

        //SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault());

//        File path=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SPaint");
//        String date=new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(new Date());
//        filename=path+"/"+date+".png";
//
//        if (!path.exists()){
//            path.mkdirs();
//        }

        defaultColor= ContextCompat.getColor(S_Paint.this,R.color.black);
        imgColor.setOnClickListener(view -> {
            //openColorPicker();
            AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {

                }

                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    defaultColor=color;
                    signatureView.setPenColor(color);
                }
            });
            ambilWarnaDialog.show();
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtPenSize.setText(i+"dp");
                signatureView.setPenSize(i);
                seekBar.setMax(50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        imgErazer.setOnClickListener(view -> {
            signatureView.clearCanvas();
        });
        
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!signatureView.isBitmapEmpty()){
                    try {
                        //saveImage();
                        File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SPaint");
                        String date = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(new Date());
                        String filename = path+"/"+date+".png";

                        if (!path.exists()){
                            path.mkdirs();
                        }
                        File file=new File(filename);

                        Bitmap bitmap=signatureView.getSignatureBitmap();
                        ByteArrayOutputStream bos =new ByteArrayOutputStream();

                        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
                        byte[] bitMapData=bos.toByteArray();

                        FileOutputStream fos =new FileOutputStream(file);
                        fos.write(bitMapData);
                        fos.flush();
                        fos.close();

                        Toast.makeText(S_Paint.this, "Image is Save", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(S_Paint.this, "Couldn't saved!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void askPermission() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        Toast.makeText(S_Paint.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
}