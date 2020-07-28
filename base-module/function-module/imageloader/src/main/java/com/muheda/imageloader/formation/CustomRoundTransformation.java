package com.muheda.imageloader.formation;//package com.example.zhangming.webviewtest.ImageLoader;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Shader;
//
//import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
//import com.bumptech.glide.load.resource.bitmap.FitCenter;
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//
///**
// * 自定义Transformation
// * Created by zhangming on 2018/6/7.
// */
//
//public abstract class CustomRoundTransformation extends RoundedCorners {
//
//    public CustomRoundTransformation() {
//        super();
//    }
//
//    @Override
//    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
//        toTransform = super.transform(pool, toTransform, outWidth, outHeight);
//        int width = toTransform.getWidth();
//        int height = toTransform.getHeight();
//        Bitmap result = pool.get(width, height, Bitmap.Config.ARGB_8888);
//        if (result == null) {
//            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        }
//        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setShader(new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//        drawMaskLayer(canvas,width,height,paint);
//        if (toTransform != null && result != toTransform ) {
//            toTransform.recycle();
//        }
//        return result;
//    }
//
//    protected abstract void drawMaskLayer(Canvas canvas, int width, int height, Paint paint);
//
//}
