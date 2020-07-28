package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Base64;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;

import com.muheda.mhdsystemkit.sytemUtil.SystemKit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wf
 */
public final class ImageUtil {

    private ImageUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 图片处理：裁剪.
     */
    public static final int CUTIMG = 0;

    /**
     * 图片处理：缩放.
     */
    public static final int SCALEIMG = 1;

    /**
     * 图片最大宽度.
     */
    public static final int MAX_WIDTH = 4096 / 2;

    /**
     * 图片最大高度.
     */
    public static final int MAX_HEIGHT = 4096 / 2;

    /**
     * 描述：缩放图片.压缩
     *
     * @param file          File对象
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getScaleBitmap(File file, int desiredWidth, int desiredHeight) {
        Bitmap resizeBmp = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为true,decodeFile先不创建内存 只获取一些解码边界信息即图片大小信息
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), opts);

        // 获取图片的原始宽度高度
        int srcWidth = opts.outWidth;
        int srcHeight = opts.outHeight;
        int[] size = resizeToMaxSize(srcWidth, srcHeight, desiredWidth, desiredHeight);
        desiredWidth = size[0];
        desiredHeight = size[1];

        // 缩放的比例
        float scale = getMinScale(srcWidth, srcHeight, desiredWidth, desiredHeight);
        int destWidth = srcWidth;
        int destHeight = srcHeight;
        if (scale != 0) {
            destWidth = (int) (srcWidth * scale);
            destHeight = (int) (srcHeight * scale);
        }

        // 默认为ARGB_8888.
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        // 以下两个字段需一起使用：
        // 产生的位图将得到像素空间，如果系统gc，那么将被清空。当像素再次被访问，如果Bitmap已经decode，那么将被自动重新解码
        opts.inPurgeable = true;
        // 位图可以共享一个参考输入数据(inputstream、阵列等)
        opts.inInputShareable = true;

        // inSampleSize=2 表示图片宽高都为原来的二分之一，即图片为原来的四分之一
        // 缩放的比例，SDK中建议其值是2的指数值，通过inSampleSize来进行缩放，其值表明缩放的倍数
        if (scale < 0.25) {
            // 缩小到4分之一
            opts.inSampleSize = 2;
        } else if (scale < 0.125) {
            // 缩小到8分之一
            opts.inSampleSize = 4;
        } else {
            opts.inSampleSize = 1;
        }

        // 设置大小
        opts.outWidth = destWidth;
        opts.outHeight = destHeight;

        // 创建内存
        opts.inJustDecodeBounds = false;
        // 使图片不抖动
        opts.inDither = false;

        resizeBmp = BitmapFactory.decodeFile(file.getPath(), opts);
        // 缩小或者放大
        resizeBmp = getScaleBitmap(resizeBmp, scale);

        return resizeBmp;
    }

    public static Bitmap decodesampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        //reqWidth reqHeight 为ImageView控件宽高
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize=calculateSamplesize(options,reqWidth,reqHeight);
        System.out.println("--------size--"+options.inSampleSize);
        options.inJustDecodeBounds=false;

        return  BitmapFactory.decodeResource(res,resId,options);
    }

    public static int calculateSamplesize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        int height=options.outHeight;
        int width= options.outWidth;
        int inSampleSize=1;
        if (height>reqHeight||width>reqWidth){
            int halfHeight=height/2;
            int halfWidth=width/2;
            while ((halfHeight/inSampleSize)>=reqHeight&&(halfWidth/inSampleSize)>=reqWidth){
                inSampleSize*=2;
            }
        }
        return inSampleSize;
    }

    private static int[] resizeToMaxSize(int srcWidth, int srcHeight,
                                         int desiredWidth, int desiredHeight) {
        int[] size = new int[2];
        if (desiredWidth <= 0) {
            desiredWidth = srcWidth;
        }
        if (desiredHeight <= 0) {
            desiredHeight = srcHeight;
        }
        if (desiredWidth > MAX_WIDTH) {
            // 重新计算大小
            desiredWidth = MAX_WIDTH;
            float scaleWidth = (float) desiredWidth / srcWidth;
            desiredHeight = (int) (desiredHeight * scaleWidth);
        }

        if (desiredHeight > MAX_HEIGHT) {
            // 重新计算大小
            desiredHeight = MAX_HEIGHT;
            float scaleHeight = (float) desiredHeight / srcHeight;
            desiredWidth = (int) (desiredWidth * scaleHeight);
        }
        size[0] = desiredWidth;
        size[1] = desiredHeight;
        return size;
    }

    private static float getMinScale(int srcWidth, int srcHeight, int desiredWidth,
                                     int desiredHeight) {
        // 缩放的比例
        float scale = 0;
        // 计算缩放比例，宽高的最小比例
        float scaleWidth = (float) desiredWidth / srcWidth;
        float scaleHeight = (float) desiredHeight / srcHeight;
        if (scaleWidth > scaleHeight) {
            scale = scaleWidth;
        } else {
            scale = scaleHeight;
        }

        return scale;
    }

    /**
     * 描述：根据等比例缩放图片.
     *
     * @param bitmap the bitmap
     * @param scale  比例
     * @return Bitmap 新图片
     */
    public static Bitmap getScaleBitmap(Bitmap bitmap, float scale) {

        if (!checkBitmap(bitmap)) {
            return null;
        }

        if (scale == 1) {
            return bitmap;
        }

        Bitmap resizeBmp = null;
        try {
            // 获取Bitmap资源的宽和高
            int bmpW = bitmap.getWidth();
            int bmpH = bitmap.getHeight();

            // 注意这个Matirx是android.graphics底下的那个
            Matrix matrix = new Matrix();
            // 设置缩放系数，分别为原来的0.8和0.8
            matrix.postScale(scale, scale);
            resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bmpW, bmpH, matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resizeBmp != bitmap) {
                bitmap.recycle();
            }
        }
        return resizeBmp;
    }

    private static boolean checkBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            LogUtil.e(ImageUtil.class, "原图Bitmap为空了");
            return false;
        }

        if (bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            LogUtil.e(ImageUtil.class, "原图Bitmap大小为0");
            return false;
        }
        return true;
    }

    private static boolean checkSize(int desiredWidth, int desiredHeight) {
        if (desiredWidth <= 0 || desiredHeight <= 0) {
            LogUtil.e(ImageUtil.class, "请求Bitmap的宽高参数必须大于0");
            return false;
        }
        return true;
    }

    private static Bitmap getMatrixBitmap(Bitmap bitmap, float scale) {
        Matrix matrix = new Matrix();
        //matrix.postScale(0.15f, 0.15f);
        matrix.postScale(scale, scale);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap getScaleBitmap(Bitmap bmp, int bmpSize) {
        return Bitmap.createScaledBitmap(bmp, bmpSize, bmpSize, true);
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        int radius = bitmap.getWidth() / 2;
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        output.eraseColor(Color.argb(0, 0, 0, 0));
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(radius, radius, radius, paint);

        return output;
    }

    /**
     * 描述：获取Drawable中的图片资源.
     *
     * @param resId drawable id
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromDrawable(int resId) {
        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeResource(SystemKit.getContext().getResources(), resId);
        } catch (Exception e) {
//            LogUtil.d(FileUtil.class, "getBitmapFromDrawable ：" + e.getMessage());
        }
        return bit;
    }

    /**
     * Bitmap to drawable.
     *
     * @param bitmap The bitmap.
     * @return drawable
     */
    public static Drawable bitmap2Drawable(final Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(SystemKit.getContext().getResources(), bitmap);
    }


    /**
     * View to bitmap.
     *
     * @param view The view.
     * @return bitmap
     */
    public static Bitmap view2Bitmap(final View view) {
        if (view == null) return null;
        Bitmap ret = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(ret);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return ret;
    }

    /**
     * 描述：文件获取原图.
     *
     * @param file File对象
     * @return Bitmap 图片
     */
    public static Bitmap getBitmap(File file) {
        Bitmap resizeBmp = null;
        try {
            resizeBmp = BitmapFactory.decodeFile(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resizeBmp;
    }

    /**
     * Bitmap to Base64
     *
     * @param bit
     * @return
     */
    public static String Bitmap2StrByBase64(Bitmap bit) {
        if (bit == null) {
            return "";
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();

        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * Bitmap rotation 旋转
     */
    public static Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {
        if(bm == null) return null;

        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);

        try {
            return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
        } catch (OutOfMemoryError ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * 读取drawable下的图片到文件夹里
     *
     * @param path     保存路径
     * @param srcName  图片的名字
     * @param drawable 资源文件
     */
    public static void saveImageFromDrawable(String path, String srcName, int drawable) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(path);
            File fileImage = new File(path + File.separator + srcName);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!fileImage.exists()) {
                try {
                    Bitmap pic = BitmapFactory.decodeResource(SystemKit.getContext().getResources(), drawable);
                    FileOutputStream fos = new FileOutputStream(fileImage);
                    pic.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    public static InputStream Bitmap2IS(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }


    /**
     * Return the round bitmap.
     * 转为圆形图片
     * @param src The source of bitmap.
     * @return the round bitmap
     */
    public static Bitmap toRound(final Bitmap src) {
        return toRound(src, 0, 0, false);
    }

    /**
     * Return the round bitmap.
     *
     * @param src     The source of bitmap.
     * @param recycle True to recycle the source of bitmap, false otherwise.
     * @return the round bitmap
     */
    public static Bitmap toRound(final Bitmap src, final boolean recycle) {
        return toRound(src, 0, 0, recycle);
    }

    /**
     * Return the round bitmap.
     *
     * @param src         The source of bitmap.
     * @param borderSize  The size of border.
     * @param borderColor The color of border.
     * @return the round bitmap
     */
    public static Bitmap toRound(final Bitmap src,
                                 @IntRange(from = 0) int borderSize,
                                 @ColorInt int borderColor) {
        return toRound(src, borderSize, borderColor, false);
    }

    private static boolean isEmptyBitmap(final Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }
    /**
     * Return the round bitmap.
     *
     * @param src         The source of bitmap.
     * @param recycle     True to recycle the source of bitmap, false otherwise.
     * @param borderSize  The size of border.
     * @param borderColor The color of border.
     * @return the round bitmap
     */
    public static Bitmap toRound(final Bitmap src,
                                 @IntRange(from = 0) int borderSize,
                                 @ColorInt int borderColor,
                                 final boolean recycle) {
        if (isEmptyBitmap(src)) return null;
        int width = src.getWidth();
        int height = src.getHeight();
        int size = Math.min(width, height);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap ret = Bitmap.createBitmap(width, height, src.getConfig());
        float center = size / 2f;
        RectF rectF = new RectF(0, 0, width, height);
        rectF.inset((width - size) / 2f, (height - size) / 2f);
        Matrix matrix = new Matrix();
        matrix.setTranslate(rectF.left, rectF.top);
        matrix.preScale((float) size / width, (float) size / height);
        BitmapShader shader = new BitmapShader(src, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);
        Canvas canvas = new Canvas(ret);
        canvas.drawRoundRect(rectF, center, center, paint);
        if (borderSize > 0) {
            paint.setShader(null);
            paint.setColor(borderColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(borderSize);
            float radius = center - borderSize / 2f;
            canvas.drawCircle(width / 2f, height / 2f, radius, paint);
        }
        if (recycle && !src.isRecycled()) src.recycle();
        return ret;
    }

    /**
     * Return the round corner bitmap.
     * 转为圆角图片
     * @param src    The source of bitmap.
     * @param radius The radius of corner.
     * @return the round corner bitmap
     */
    public static Bitmap toRoundCorner(final Bitmap src, final float radius) {
        return toRoundCorner(src, radius, 0, 0, false);
    }

    /**
     * Return the round corner bitmap.
     *
     * @param src     The source of bitmap.
     * @param radius  The radius of corner.
     * @param recycle True to recycle the source of bitmap, false otherwise.
     * @return the round corner bitmap
     */
    public static Bitmap toRoundCorner(final Bitmap src,
                                       final float radius,
                                       final boolean recycle) {
        return toRoundCorner(src, radius, 0, 0, recycle);
    }

    /**
     * Return the round corner bitmap.
     *
     * @param src         The source of bitmap.
     * @param radius      The radius of corner.
     * @param borderSize  The size of border.
     * @param borderColor The color of border.
     * @return the round corner bitmap
     */
    public static Bitmap toRoundCorner(final Bitmap src,
                                       final float radius,
                                       @IntRange(from = 0) int borderSize,
                                       @ColorInt int borderColor) {
        return toRoundCorner(src, radius, borderSize, borderColor, false);
    }

    /**
     * Return the round corner bitmap.
     *
     * @param src         The source of bitmap.
     * @param radius      The radius of corner.
     * @param borderSize  The size of border.
     * @param borderColor The color of border.
     * @param recycle     True to recycle the source of bitmap, false otherwise.
     * @return the round corner bitmap
     */
    public static Bitmap toRoundCorner(final Bitmap src,
                                       final float radius,
                                       @IntRange(from = 0) int borderSize,
                                       @ColorInt int borderColor,
                                       final boolean recycle) {
        if (isEmptyBitmap(src)) return null;
        int width = src.getWidth();
        int height = src.getHeight();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap ret = Bitmap.createBitmap(width, height, src.getConfig());
        BitmapShader shader = new BitmapShader(src, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        Canvas canvas = new Canvas(ret);
        RectF rectF = new RectF(0, 0, width, height);
        float halfBorderSize = borderSize / 2f;
        rectF.inset(halfBorderSize, halfBorderSize);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        if (borderSize > 0) {
            paint.setShader(null);
            paint.setColor(borderColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(borderSize);
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawRoundRect(rectF, radius, radius, paint);
        }
        if (recycle && !src.isRecycled()) src.recycle();
        return ret;
    }


}
