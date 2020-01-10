package com.dillibhandari.foodmandu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dillibhandari.foodmandu.Bll.BannerBll;
import com.dillibhandari.foodmandu.Bll.ImageUploadBll;
import com.dillibhandari.foodmandu.Model.BannerItem;
import com.dillibhandari.foodmandu.permissonsAndUri.LinkPermisson;
import com.dillibhandari.foodmandu.permissonsAndUri.StoragePath;

public class BannerActivity extends AppCompatActivity {

    private ImageView bannerImage;
    private EditText bannerName,desc;
    private Button upload,save;
    private String imagepath="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        bannerImage=findViewById(R.id.imageView2banner);
        bannerName=findViewById(R.id.editTextbanner);
        desc=findViewById(R.id.editTextdesc);
        upload=findViewById(R.id.uploadbutton2);
        save=findViewById(R.id.buttonsave);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BrowseImage();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(BannerActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImageAndSaveData();
            }
        });


    }

    private void BrowseImage() throws Exception {
        // checkPermissionForReadExtertalStorage();
        LinkPermisson linkPermisson= new LinkPermisson(this);
        linkPermisson.requestPermissionForReadExtertalStorage();

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
        bannerImage.setImageURI(uri);
        StoragePath storagePath= new StoragePath(this);
        imagepath = storagePath.getRealPathFromUri(uri);
    }

    private void UploadImageAndSaveData(){
        ImageUploadBll imageUploadBll= new ImageUploadBll(imagepath,this);
        String getmageName= imageUploadBll.uploadFile();
        if(!getmageName.equals("Error")){
            SaveData(getmageName);
        }
    }

    private void SaveData(String setimageName){
        BannerItem bannerItem= new BannerItem(bannerName.getText().toString(),setimageName,desc.getText().toString());
        BannerBll bannerBll= new BannerBll();
        if(bannerBll.InsertBanner(bannerItem)){
            Toast.makeText(this, "Data Inserted Successfuly", Toast.LENGTH_SHORT).show();
        }

    }

}
