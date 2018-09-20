package dsm.imagefilter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import pl.polidea.view.ZoomView;

/**
 * Created by ghdth on 2018-09-03.
 */

public class ImageEditActivity extends AppCompatActivity {
    private ImageView image;
    private android.support.v7.widget.Toolbar top_tab;
    private RelativeLayout bottom_tab,item_tab;
    private RecyclerView recyclerView,item_rv;
    private RecyclerView.Adapter mAdapter,item_mAdapter;
    private ArrayList<Item_bottom_tab> list,item_list;
    private LinearLayoutManager linearLayoutManager,item_lm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);


        //ZooView
        View v=((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.zoom_item,null,false);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        ZoomView zoomView =new ZoomView(this);
        zoomView.addView(v);
        zoomView.setLayoutParams(layoutParams);
        zoomView.setMiniMapEnabled(true);
        zoomView.setMaxZoom(4f);
        zoomView.setMiniMapCaptionSize(20);
        //zoomView.setMiniMapCaption("mini map test");

        RelativeLayout container=(RelativeLayout)findViewById(R.id.container);
        container.addView(zoomView);

        top_tab=(android.support.v7.widget.Toolbar)findViewById(R.id.top_tab);
        bottom_tab=(RelativeLayout) findViewById(R.id.bottom_tab);
        item_tab=(RelativeLayout)findViewById(R.id.item_tab);



        Intent intent=getIntent();
        Uri uri=intent.getData();
        Log.d("ImageEditActivity:",uri.toString());
        image=(ImageView)v.findViewById(R.id.Image_edit);

       image.setImageURI(uri);
/////////////////////////////////////////////////////////////////////////////////////////
        //RecyclerView
        recyclerView=(RecyclerView)findViewById(R.id.bottom_tab_rv);
       // item_rv=(RecyclerView)findViewById(R.id.item_tab_rv);


        linearLayoutManager=new LinearLayoutManager(this);
        //item_lm=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
      //  item_lm.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
       // item_rv.setLayoutManager(item_lm);

        list=new ArrayList<>();
       // item_list=new ArrayList<>();
        mAdapter=new Adapter_bottom_tab(getApplicationContext(),list);
       // item_mAdapter=new Adapter_bottom_tab(getApplicationContext(),item_list);
        recyclerView.setAdapter(mAdapter);
        //item_rv.setAdapter(item_mAdapter);

        list.add(new Item_bottom_tab(R.drawable.ex,"테스트1"));
        list.add(new Item_bottom_tab(R.drawable.ex,"테스트2"));
        list.add(new Item_bottom_tab(R.drawable.ex,"테스트3"));
        list.add(new Item_bottom_tab(R.drawable.ex,"테스트4"));
        list.add(new Item_bottom_tab(R.drawable.ex,"테스트5"));

        /*item_list.add(new Item_bottom_tab(R.drawable.ex,"호롤롤롤1"));
        item_list.add(new Item_bottom_tab(R.drawable.ex,"호롤롤롤2"));
        item_list.add(new Item_bottom_tab(R.drawable.ex,"호롤롤롤3"));
        item_list.add(new Item_bottom_tab(R.drawable.ex,"호롤롤롤4"));
        item_list.add(new Item_bottom_tab(R.drawable.ex,"호롤롤롤5"));
        item_list.add(new Item_bottom_tab(R.drawable.ex,"호롤롤롤6"));*/

         //image.setImageBitmap(doInvert(change_bitmap(uri)));


        //Glide.with(this).load(uri.toString()).into(image);
    }
   @Override

    public boolean onTouchEvent(MotionEvent event) {


        int action = event.getAction();

        switch(action) {
            case MotionEvent.ACTION_UP :    //화면을 터치했다 땠을때
                if(item_tab.getVisibility()==View.VISIBLE){
                    item_tab.setVisibility(View.GONE);
                    Animation item_tab_ani= AnimationUtils.loadAnimation(this,R.anim.remove);
                    item_tab.startAnimation(item_tab_ani);
                }
                Toast.makeText(getApplicationContext(),"터치",Toast.LENGTH_LONG).show();
                if(top_tab.getVisibility()==View.VISIBLE&&bottom_tab.getVisibility()==View.VISIBLE){
                    top_tab.setVisibility(View.GONE);
                    bottom_tab.setVisibility(View.GONE);

                    Animation top_tab_ani= AnimationUtils.loadAnimation(this,R.anim.remove);
                    Animation bottom_tab_ani= AnimationUtils.loadAnimation(this,R.anim.remove);

                    top_tab.startAnimation(top_tab_ani);
                    bottom_tab.startAnimation(bottom_tab_ani);
                }
                else if(top_tab.getVisibility()==View.GONE&&bottom_tab.getVisibility()==View.GONE){
                    top_tab.setVisibility(View.VISIBLE);
                    bottom_tab.setVisibility(View.VISIBLE);
                    Animation top_tab_ani= AnimationUtils.loadAnimation(this,R.anim.alpha);
                    Animation bottom_tab_ani= AnimationUtils.loadAnimation(this,R.anim.alpha);

                    top_tab.startAnimation(top_tab_ani);
                    bottom_tab.startAnimation(bottom_tab_ani);
                }

                break;
        }

        return super.onTouchEvent(event);

    }



    public  Bitmap change_bitmap(Uri uri){
        Bitmap bm = null;
        if(uri!=null&&!uri.toString().isEmpty()){
            try {
                bm= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bm;
    }

    public static Bitmap doInvert(Bitmap src) {
        // create new bitmap with the same settings as source bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // color info
        int A, R, G, B;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();

        // scan through every pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // get one pixel
                pixelColor = src.getPixel(x, y);
                // saving alpha channel
                A = Color.alpha(pixelColor);
                // inverting byte for each R/G/B channel
                R = (int)((Color.red(pixelColor)*120)/100);
                G = (int)((Color.green(pixelColor)*120)/100);
                B = (int)((Color.blue(pixelColor) *120)/100);
                // set newly-inverted pixel to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final bitmap
        return bmOut;
    }


}
