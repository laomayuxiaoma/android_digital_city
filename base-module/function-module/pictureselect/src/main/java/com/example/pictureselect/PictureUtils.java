package com.example.pictureselect;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.luck.picture.lib.PictureExternalPreviewActivity;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;


public class PictureUtils {

    // .selectionMedia(selectList)// 手写是否传入已选图片

    private PictureSelectionModel pictureSelectionModel;

    public PictureUtils(PictureSelectionModel pictureSelectionModel) {
        this.pictureSelectionModel = pictureSelectionModel;
    }

    public enum Type {
        CAMER, GALLERY
    }

    /**
     * @param themeStyleId 主题样式
     * @return
     */
    public PictureUtils setThemeStyle(@StyleRes int themeStyleId) {
        pictureSelectionModel.theme(themeStyleId);
        return this;
    }

    /**
     * @param activity 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
     * @param mimeType
     * @return type CAMER开启相机 GA开启相册
     */
    public static PictureUtils creatOpenGallery(Activity activity, int mimeType, Type type) {
        switch (type) {
            case CAMER:

                return new PictureUtils(create(activity).openCamera(mimeType));
            case GALLERY:

                return new PictureUtils(create(activity).openGallery(mimeType));
        }
        return null;
    }

    public static PictureUtils creatOpenGallery(Fragment fragment, int mimeType, Type type) {
        switch (type) {
            case CAMER:

                return new PictureUtils(create(fragment).openCamera(mimeType));
            case GALLERY:

                return new PictureUtils(create(fragment).openGallery(mimeType));
        }
        return null;
    }

    public static PictureSelector create(Activity activity) {
        return PictureSelector.create(activity);
    }

    public static PictureSelector create(Fragment fragment) {
        return PictureSelector.create(fragment);
    }

    /**
     * @param max        最大选择
     * @param min        最小选择
     * @param spacecount 每行显示条目
     * @return
     */
    public PictureUtils setSelectNum(int max, int min, int spacecount) {
        pictureSelectionModel.maxSelectNum(max).minSelectNum(min).imageSpanCount(spacecount);
        return this;
    }

    /**
     * @param modeType 选择模式
     *                 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
     * @return
     */
    public PictureUtils setSelectMode(int modeType) {

        pictureSelectionModel.selectionMode(modeType);
        return this;
    }

    /**
     * @param enablePreview 是否可预览图片 true or false
     * @param previewEggs   预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
     * @return
     */
    public PictureUtils previewImage(boolean enablePreview, boolean previewEggs) {
        pictureSelectionModel.previewImage(enablePreview).previewEggs(previewEggs);
        return this;
    }

    /**
     * @param enPreviewVideo 是否可预览视频 true or false
     * @return
     */
    public PictureUtils previewVideo(boolean enPreviewVideo) {
        pictureSelectionModel.previewVideo(enPreviewVideo);
        return this;
    }

    /**
     * @param enablePreviewAudio 是否可播放音频 true or false
     * @return
     */
    public PictureUtils enablePreviewAudio(boolean enablePreviewAudio) {
        pictureSelectionModel.enablePreviewAudio(enablePreviewAudio);
        return this;
    }

    /**
     * @param isCamera 是否显示拍照按钮 true or false
     * @return
     */
    public PictureUtils isCamera(boolean isCamera) {
        pictureSelectionModel.isCamera(isCamera);
        return this;
    }

    /**
     * @param isGif 是否显示gif图片 true or false
     * @return
     */
    public PictureUtils isGif(boolean isGif) {
        pictureSelectionModel.isGif(isGif);
        return this;
    }

    /**
     * @param isOpenClickSound 是否开启点击声音 true or false
     * @return
     */
    public PictureUtils openClickSound(boolean isOpenClickSound) {
        pictureSelectionModel.openClickSound(isOpenClickSound);
        return this;
    }

    /**
     * 显示多少秒以内的视频or音频
     *
     * @param videoMaxSecond
     * @param videoMinSecond
     * @return
     */
    public PictureUtils videoSecond(int videoMaxSecond, int videoMinSecond) {
        pictureSelectionModel.videoMaxSecond(videoMaxSecond).videoMinSecond(videoMinSecond);
        return this;
    }

    /**
     * @param enableCrop 是否裁剪 true or false
     * @return
     */
    public PictureUtils enableCrop(boolean enableCrop) {
        pictureSelectionModel.enableCrop(enableCrop);
        return this;
    }

    /**
     * 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
     *
     * @param withAspectRatioX
     * @param withAspectRatioY
     * @return
     */
    public PictureUtils withAspectRatio(int withAspectRatioX, int withAspectRatioY) {
        pictureSelectionModel.withAspectRatio(withAspectRatioX, withAspectRatioY);
        return this;
    }

    /**
     * @param freeStyleCropEnabled 裁剪框是否可拖拽 true or false
     * @return
     */
    public PictureUtils freeStyleCropEnabled(boolean freeStyleCropEnabled) {
        pictureSelectionModel.freeStyleCropEnabled(freeStyleCropEnabled);
        return this;
    }

    /**
     * @param circleDimmedLayer 是否圆形裁剪 true or false
     * @return
     */
    public PictureUtils circleDimmedLayer(boolean circleDimmedLayer) {
        pictureSelectionModel.circleDimmedLayer(circleDimmedLayer);
        return this;
    }

    /**
     * @param showCropFrame 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
     * @param showCropGrid  是否显示裁剪矩形网格 圆形裁剪时建议设为false   true or false
     * @return
     */
    public PictureUtils showCropFrame(boolean showCropFrame, boolean showCropGrid) {
        pictureSelectionModel.showCropFrame(showCropFrame).showCropGrid(showCropGrid);
        return this;
    }

    /**
     * 裁剪压缩质量 默认90
     *
     * @param cropCompressQuality
     * @return
     */
    public PictureUtils cropCompressQuality(int cropCompressQuality) {
        pictureSelectionModel.cropCompressQuality(cropCompressQuality);
        return this;
    }

    /**
     * 裁剪宽高比，设置如果大于图片本身宽高则无效
     *
     * @param cropW
     * @param cropH
     * @return
     */
    public PictureUtils cropWH(int cropW, int cropH) {
        pictureSelectionModel.cropWH(cropW, cropH);
        return this;
    }

    /**
     * 裁剪是否可旋转图片
     *
     * @param rotateEnabled
     * @return
     */
    public PictureUtils rotateEnabled(boolean rotateEnabled) {
        pictureSelectionModel.rotateEnabled(rotateEnabled);
        return this;
    }

    /**
     * 裁剪是否可放大缩小图片
     *
     * @param scaleEnabled
     * @return
     */
    public PictureUtils scaleEnabled(boolean scaleEnabled) {
        pictureSelectionModel.scaleEnabled(scaleEnabled);
        return this;
    }

    /**
     * 是否显示uCrop工具栏，默认不显示 true or false
     *
     * @param hideBottomControls
     * @return
     */
    public PictureUtils hideBottomControls(boolean hideBottomControls) {
        pictureSelectionModel.hideBottomControls(hideBottomControls);
        return this;
    }

    /**
     * 是否压缩 true or false
     *
     * @param compress
     * @return
     */
    public PictureUtils compress(boolean compress) {
        pictureSelectionModel.compress(compress);
        return this;
    }

    /**
     * 压缩图片保存地址
     *
     * @param compressSavePath
     * @return
     */
    public PictureUtils compressSavePath(String compressSavePath) {
        pictureSelectionModel.compressSavePath(compressSavePath);
        return this;
    }

    /**
     * 小于多少kb的图片不压缩
     *
     * @param minimumCompressSize
     * @return
     */
    public PictureUtils minimumCompressSize(int minimumCompressSize) {
        pictureSelectionModel.minimumCompressSize(minimumCompressSize);
        return this;
    }

    /**
     * 同步true或异步false 压缩 默认同步
     *
     * @param synOrAsy
     * @return
     */
    public PictureUtils synOrAsy(boolean synOrAsy) {
        pictureSelectionModel.synOrAsy(synOrAsy);
        return this;
    }

    /**
     * @param videoQuality      视频录制质量 0 or 1
     * @param recordVideoSecond 视频秒数录制 默认60s
     * @return
     */
    public PictureUtils videoSet(int videoQuality, int recordVideoSecond) {
        pictureSelectionModel.videoQuality(videoQuality).recordVideoSecond(recordVideoSecond);
        return this;
    }

    /**
     * @param code 回调状态码 最后必须调!!!
     * @return
     */
    public PictureUtils forResult(int code) {
        pictureSelectionModel.forResult(code);
        return this;
    }

    /**
     * 拍照保存图片格式后缀,默认jpeg
     *
     * @param suffixType
     * @return
     */
    public PictureUtils imageFormat(String suffixType) {
        pictureSelectionModel.imageFormat(suffixType);
        return this;
    }

    /**
     *  selectionMedia(selectList)// 手写是否传入已选图片
     *
     * @param list
     * @return
     */
    public PictureUtils selectionMedia(List<LocalMedia> list) {
        pictureSelectionModel.selectionMedia(list);
        return this;
    }

    public static void deleteCache(final Context context, Activity activity) {
//        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
//        RxPermissions permissions = new RxPermissions(activity);
//        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onNext(Boolean aBoolean) {
//                if (aBoolean) {
//                    PictureFileUtils.deleteCacheDirFile(context);
//                } else {
//                    Toast.makeText(context,
//                            context.getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//            }
//        });
    }

    /**
     * @param activity
     * @param themeStyleId
     * @param position
     * @param path
     * @param list
     */
    public static void preView(Activity activity, @StyleRes int themeStyleId, int position, String path, List list) {

        create(activity).themeStyle(themeStyleId).openExternalPreview(position, path, list);
    }

    public static void preView(Fragment fragment, @StyleRes int themeStyleId, int position, String path, List list) {

        create(fragment).themeStyle(themeStyleId).openExternalPreview(position, path, list);
    }
}
