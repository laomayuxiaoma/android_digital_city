package com.muheda.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.muheda.imageloader.formation.CircleCenterCropTransformation;
import com.muheda.imageloader.formation.CircleFitCenterTransformation;
import com.muheda.imageloader.formation.RoundedCornersCenterCropTransformation;
import com.muheda.imageloader.formation.RoundedCornersFitCenterTransformation;

import com.muheda.imageloader.util.DisplayUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;


/**
 * 图片加载统一处理
 * 图片加载工具类(当替换图片加载框架时可直接替换此类，不用替换再修改其他的代码了)
 * <p>
 * Created by zhangming on 2018/6/6.
 */

public class ImageLoader {

    public enum Mode {
        MODE_CENTER, MODE_FIT
    }

    //正常加载图片(默认加载图片待处理)
    public static <T> void loadImageView(Context mContext, T url, ImageView mImageView) {
        loadImageViewLoding(mContext, url, mImageView, R.mipmap.img_icon, R.mipmap.img_icon);
    }

    //加载圆形头像(Center展示)
    public static <T> void loadImageHead(Context mContext, T url, ImageView mImageView) {
        loadCircleImageHead(mContext, url, mImageView, R.mipmap.img_icon, R.mipmap.img_icon, Mode.MODE_CENTER, 20);
    }

    //加载圆形图片(Center展示)
    public static <T> void loadCircleImage(Context mContext, T url, ImageView mImageView) {
        loadCircleImageView(mContext, url, mImageView, R.mipmap.img_icon, R.mipmap.img_icon, Mode.MODE_CENTER);
    }

    //加载圆形图片(fit展示)
    public static <T> void loadFitCircleImage(Context mContext, T url, ImageView mImageView) {
        loadCircleImageView(mContext, url, mImageView, R.mipmap.img_icon, R.mipmap.img_icon, Mode.MODE_FIT);
    }

    //加载圆角图片(Center展示)
    public static <T> void loadRoundImage(Context mContext, T url, ImageView mImageView) {
        loadRoundImage(mContext, url, mImageView, 6);
    }

    //加载圆角图片(fit展示)
    public static <T> void loadFitRoundImage(Context mContext, T url, ImageView mImageView) {
        loadFitRoundImage(mContext, url, mImageView, 6);
    }

    //加载圆角图片(Center展示,单位dp)
    public static <T> void loadRoundImage(Context mContext, T url, ImageView mImageView, int dp) {
        loadRoundImageView(mContext, url, mImageView, R.mipmap.img_icon, R.mipmap.img_icon, dp, Mode.MODE_CENTER);
    }

    //加载圆角图片(fit展示,单位dp)
    public static <T> void loadFitRoundImage(Context mContext, T url, ImageView mImageView, int dp) {
        loadRoundImageView(mContext, url, mImageView, R.mipmap.img_icon, R.mipmap.img_icon, dp, Mode.MODE_FIT);
    }

    //加载指定大小
    public static <T> void loadImageViewSize(Context mContext, T url, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(url).apply(RequestOptions.overrideOf(DisplayUtil.dip2px(mContext, width), DisplayUtil.dip2px(mContext, height))).into(mImageView);
    }

    //设置加载中以及加载失败图片
    public static <T> void loadImageViewLoding(Context mContext, T url, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(url) .apply(RequestOptions.placeholderOf(lodingImage))
                .apply(RequestOptions.errorOf(errorImageView)).into(mImageView);
    }

    private static RequestOptions options = new RequestOptions();

    //设置头像
    public static <T> void loadCircleImageHead(Context mContext, T url, ImageView mImageView, int lodingImage, int errorImageView, Mode mode, int dp) {
        Glide.with(mContext).load(url).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .apply(RequestOptions.overrideOf(DisplayUtil.dip2px(mContext, dp), DisplayUtil.dip2px(mContext, dp)))
                .apply(options.transform((mode == Mode.MODE_CENTER) ? new CircleCenterCropTransformation(mContext) :
                        new CircleFitCenterTransformation(mContext)))/*.placeholder(lodingImage).error(errorImageView)*/
                .into(mImageView);
    }

    //设置圆形图片
    public static <T> void loadCircleImageView(Context mContext, T url, ImageView mImageView, int lodingImage, int errorImageView, Mode mode) {
        Glide.with(mContext).load(url)
                .apply(RequestOptions.placeholderOf(lodingImage))
                .apply(RequestOptions.errorOf(errorImageView))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .apply(options.transform((mode == Mode.MODE_CENTER) ? new CircleCenterCropTransformation(mContext) :
                        new CircleFitCenterTransformation(mContext)))/*.placeholder(lodingImage).error(errorImageView)*/
                .into(mImageView);
    }

    //设置圆角图片
    public static <T> void loadRoundImageView(Context mContext, T url, ImageView mImageView, int lodingImage, int errorImageView, int dp, Mode mode) {
        Glide.with(mContext).load(url)
                .apply(RequestOptions.placeholderOf(lodingImage))
                .apply(RequestOptions.errorOf(errorImageView))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .apply(options.transform((mode == Mode.MODE_CENTER) ? new RoundedCornersCenterCropTransformation(mContext, dp) :
                        new RoundedCornersFitCenterTransformation(mContext, dp)))
                .into(mImageView);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static <T> void loadImageViewLodingSize(Context mContext, T url, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(url)
                .apply(RequestOptions.overrideOf(DisplayUtil.dip2px(mContext, width), DisplayUtil.dip2px(mContext, height)))
                .apply(RequestOptions.placeholderOf(lodingImage))
                .apply(RequestOptions.errorOf(errorImageView))
                .into(mImageView);
    }

    /**
     * 策略解说：
     * all:缓存源资源和转换后的资源
     * none:不作任何磁盘缓存
     * source:缓存源资源
     * result：缓存转换后的资源
     */

    //设置缓存策略
    public static <T> void loadImageViewDiskCache(Context mContext, T url, ImageView mImageView) {
        Glide.with(mContext).load(url)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mImageView);
    }

    /**
     * 会先加载缩略图
     */

    //设置缩略图支持
    public static <T> void loadImageViewThumbnail(Context mContext, T url, ImageView mImageView) {
        Glide.with(mContext).load(url).thumbnail(0.1f).into(mImageView);
    }

    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //设置动态转换
    public static <T> void loadImageViewCrop(Context mContext, T url, ImageView mImageView) {
        Glide.with(mContext).load(url)
                .apply(RequestOptions.centerCropTransform())
                .into(mImageView);
    }

    //设置动态GIF加载方式
    public static <T> void loadImageViewDynamicGif(Context mContext, T url, ImageView mImageView) {
        Glide.with(mContext).asGif().load(url).into(mImageView);
    }

    //设置静态GIF加载方式
    public static <T> void loadImageViewStaticGif(Context mContext, T url, ImageView mImageView) {
        Glide.with(mContext).asBitmap().load(url).into(mImageView);
    }

    /**
     * 恢复请求，一般在停止滚动的时候
     *
     * @param context
     */
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 暂停请求 正在滚动的时候
     *
     * @param context
     */
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    //清理内存缓存，可以在UI主线程中进行
    public static void clearMemory(Context mContext) {
        Glide.get(mContext).clearMemory();
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    public void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
            }
        }).start();
    }
    public static DisplayImageOptions option = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.touxiang)
            .showImageForEmptyUri(R.mipmap.touxiang)
            .showImageOnFail(R.mipmap.touxiang)
            .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
            .displayer(new SimpleBitmapDisplayer()).build();
//    public static boolean isLoadedGif(final Context context, final String url) {
//        if (TextUtils.isEmpty(url))
//            return false;
//
//        String[] strs = url.split("\\.");
//        if (strs.length <= 0)
//            return false;
//        String path = context.getCacheDir().getAbsolutePath() + File.separator + MD5Util.MD5Encode(url, null) + "." + strs[strs.length - 1];
//        File file = new File(path);
////        Log.e("TTTTTTTTGGG", file.getFreeSpace() + "||" + file.getTotalSpace() + "||" + file.length() + "||");
//        if (file.exists() && !file.isDirectory() && SPUtil.getBoolean(IS_FILE_DOWN, false) && FileUtil.getFileOrDirSize(file) > 800) {
//            return true;
//        }
//        return false;
//    }

//    private static boolean isFrist = true;
//    public final static String IS_FILE_DOWN = "isFileDown";
//
//    public static void loadGif(final Context context, final String url, final ImageView mImageView, final int defaultRes, boolean isShow, final RequestListener listener) {
//        isFrist = true;
//        if (TextUtils.isEmpty(url)) {
//            return;
//        }
//        String[] strs = url.split("\\.");
//        if (strs.length <= 0)
//            return;
//        final String path = context.getCacheDir().getAbsolutePath() + File.separator + MD5Util.MD5Encode(url, null) + "." + strs[strs.length - 1];
//        final File file = new File(path);
//        if (file.exists() && !file.isDirectory() && SPUtil.getBoolean(IS_FILE_DOWN, false) && FileUtil.getFileOrDirSize(file) > 800) {
//            if (isShow) {
//                RequestListener<File, GifDrawable> requestListener = new RequestListener<File, GifDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, File model, Target<GifDrawable> target, boolean isFirstResource) {
//                        if (!url.endsWith(".gif") && isFrist) {
//                            isFrist = false;
//                            Glide.with(context).load(new File(path)).listener(new RequestListener<File, GlideDrawable>() {
//                                @Override
//                                public boolean onException(Exception e, File model, Target<GlideDrawable> target, boolean isFirstResource) {
//                                    FileUtils.deleteFile(file.getAbsolutePath());
//                                    listener.onException(e, model, target, isFirstResource);
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(GlideDrawable resource, File model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                                    listener.onResourceReady(resource, model, target, isFromMemoryCache, isFirstResource);
//                                    return false;
//                                }
//                            })
//                                    .placeholder(defaultRes).error(defaultRes).into(mImageView);
//                        } else {
//                            FileUtils.deleteFile(file.getAbsolutePath());
//                        }
//                        listener.onException(e, model, target, isFirstResource);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GifDrawable resource, File model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        listener.onResourceReady(resource, model, target, isFromMemoryCache, isFirstResource);
//                        return false;
//                    }
//                };
//                Glide.with(context).load(new File(path)).asGif().listener(requestListener)
//                        .placeholder(defaultRes).error(defaultRes).into(mImageView);
//            }
//            return;
//        }
//        SPUtil.setBoolean(IS_FILE_DOWN, false);
//        HttpUtil.downloadFile(url, context.getCacheDir().getAbsolutePath(), null, new DownloadProgressCallBack() {
//                    @Override
//                    public void update(long bytesRead, long contentLength, boolean done) {
////                        fileLength = bytesRead;
//                        Log.e("TTTTTTTTTTKK", bytesRead + "|||" + contentLength + "||" + done);
//                    }
//
//                    @Override
//                    public void onComplete(String path) {
//                        Log.e("TTTTTTTTTTKK11", "onComplete");
//                        FileUtil.copy(path, file.getAbsolutePath());
//                        FileUtils.deleteFile(path);
//                        SPUtil.setBoolean(IS_FILE_DOWN, true);
//                    }
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        FileUtils.deleteFile(path);
//                        SPUtil.setBoolean(IS_FILE_DOWN, false);
//                    }
//                }
//                /*new ProgressDialogCallBack<String>(null, true, true) {
//                    @Override
//                    public void onSuccess(String s) {
//                        FileUtil.copy(s, file.getAbsolutePath());
//                        FileUtils.deleteFile(s);
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        super.onError(e);
//                    }
//                }*/);
//    }
}