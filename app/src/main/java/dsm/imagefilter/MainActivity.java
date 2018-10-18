package dsm.imagefilter;

import android.databinding.DataBindingUtil;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import dsm.imagefilter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnClick {
    private ActivityMainBinding binding;
    final int REQ_CODE_SELECT_IMAGE=10;
    //private Button gallery_btn;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //gallery_btn=(Button)findViewById(R.id.gallery_btn);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case REQ_CODE_SELECT_IMAGE:
                    uri=data.getData();
                    Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
                    intent.setData(uri);
                    startActivity(intent);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onChagngeGalleryClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
    }
}
