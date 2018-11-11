package com.test.yuan.zktest.application;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.test.yuan.zktest.R;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * date:2018/11/10
 * author:y(123)
 * function:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)

                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)

                .displayer(new RoundedBitmapDisplayer(10,10))
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .writeDebugLogs()
                .memoryCacheSize(13*50*50)
                .diskCacheSize(10*50*50)
                .defaultDisplayImageOptions(options)

                .build();

        ImageLoader.getInstance().init(build);

        ZXingLibrary.initDisplayOpinion(this);
    }
}
