package com.luhuan.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.luhuan.tool.GlideApp;
import com.luhuan.tool.GlideModule;
import com.luhuan.tool.GlideModuleKt;
import com.luhuan.tool.PixelKt;
import com.luhuan.tool.RxBus;

import kotlin.Pair;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d("","");
        Pair<String,Object> pair1=new Pair<>("1",1);
        Pair<String,Object> pair2=new Pair<>("2",2);
        Pair[] pairs= new Pair[]{pair1, pair2};
        RxBus.INSTANCE.post(1,"你好");
        PixelKt.getDp(10);
        PixelKt.getPx(20);
    }
    
}
