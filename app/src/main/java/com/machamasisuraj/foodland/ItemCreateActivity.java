package com.machamasisuraj.foodland;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.machamasisuraj.foodland.Bll.ImageUploadBll;
import com.machamasisuraj.foodland.Bll.ItemBll;
import com.machamasisuraj.foodland.Model.Item;

public class ItemCreateActivity extends AppCompatActivity {
private ImageView imageView;
private Button button,button2;
private EditText editText2,editText5,editText6,editText,editText3;
private String imagepath;
private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_create);
        imageView = findViewById(R.id.imageView2);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);

        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
        editText5=findViewById(R.id.editText5);
        editText6=findViewById(R.id.editText6);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BrowseImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });


    }

    private void UploadImage(){
        ImageUploadBll imageUploadBll= new ImageUploadBll(imagepath,this);
       String getmageName= imageUploadBll.uploadFile();
       if(!getmageName.equals("Error")){
           SaveData(getmageName);
       }
    }


    private void SaveData(String setimageName){
        Item item = new Item(editText.getText().toString()
                , Integer.parseInt(editText3.getText().toString())
        ,editText6.getText().toString()
        ,editText2.getText().toString()
        ,setimageName
        ,editText5.getText().toString());

        ItemBll itemBll= new ItemBll();
        itemBll.insertItem(item);

    }

    private void BrowseImage() throws Exception {
      // checkPermissionForReadExtertalStorage();
        requestPermissionForReadExtertalStorage();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imageView.setImageURI(uri);
        imagepath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
    public boolean checkPermissionForReadExtertalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        else {
            try {
                requestPermissionForReadExtertalStorage();
                return  true;

            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }
        }

    }
    public void requestPermissionForReadExtertalStorage() throws Exception {
        try {
            ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
