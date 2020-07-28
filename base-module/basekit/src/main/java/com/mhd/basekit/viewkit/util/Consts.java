package com.mhd.basekit.viewkit.util;

/**
 * @content：常量
 * @author： liyanmei
 * @time： 2018/6/4$
 * @email： lym_liyanmei@qq.com
 */
public class Consts {

    //    预览图片的大小，或者支持预览的大小
//    public static final int PREVIEW_SIZE_WIDTH = 720;
//    public static final int PREVIEW_SIZE_HEIGHT = 576;

//    public static final int PREVIEW_SIZE_WIDTH = 1280;
//    public static final int PREVIEW_SIZE_HEIGHT = 720;

    //    实际预览大小
/*    public static final int PREVIEW_REAL_SIZE_WIDTH = 720;
    public static final int PREVIEW_REAL_SIZE_HEIGHT = 288;    */
    //    实际预览大小
//    public static final int PREVIEW_REAL_SIZE_WIDTH = 1280;
//    public static final int PREVIEW_REAL_SIZE_HEIGHT = 720;

    //    全屏时，控件大小
//    public static final int VIEW_SIZE_WIDTH = 1080;
//    public static final int VIEW_SIZE_HEIGHT = 1920;
//    设备大小
//    public static final int VIEW_SIZE_WIDTH = 1024;
//    public static final int VIEW_SIZE_HEIGHT = 600;


    //    获取图片原始数据的文件名
    public static final String PHOTO_FILE_NAME = "ImgData1.dat";

    //    存放图片数据的路径
    public static final String PHOTO_PATH_DAT = "a_photo_adas/dat";
    public static final String PHOTO_PATH_BMP = "a_photo_adas/bmp";
    public static final String PHOTO_PATH_YUV = "a_photo_adas/yuv";

    //    log日志
    public static final String LOG_FILE_DIR = "a_photo_adas";
    public static final String LOG_FILE_NAME = LOG_FILE_DIR + "/log.txt";

    public static final String SOUND_NAME = "dong";
    public static final String SOUND_LANE = "warning_lane";
    public static final String SOUND_CAR = "warning_car";
    public static final String SOUND_STOPGO = "warning_stopgo";

    public static final int SOUND_TIMES = 0;
    /**
     * 新so文件的目录
     */
    public static final String SO_FILE_PATH_NAME = "a_usr_lib/libadas.so";


    /*    //    拍照后图片的大小
        public static final int PICTURE_SIZE_WIDTH = 720;
        public static final int PICTURE_SIZE_HEIGHT = 288;
        //    录像大小
        public static final int VIDEO_SIZE_WIDTH = 720;
        public static final int VIDEO_SIZE_HEIGHT = 288; */
    //    拍照后图片的大小
    public static final int PICTURE_SIZE_WIDTH = 1280;
    public static final int PICTURE_SIZE_HEIGHT = 720;
    //    录像大小
    public static final int VIDEO_SIZE_WIDTH = 1280;
    public static final int VIDEO_SIZE_HEIGHT = 720;


    // 额外的摄像头
    public static final int CAMERA_EXTRA_BACK = 2;
    public static final int CAMERA_EXTRA_FRONT = 3;
    /**
     * 录像的相关参数
     */
    public static final int CAMERA_VIDEO_ENCODING_BITRATE = 10000000;//10M
//    public static final int CAMERA_VIDEO_ENCODING_BITRATE=4000000;//4M

    //    public static final int CAMERA_VIDEO_FRAME_RATE = 30;
    public static final int CAMERA_VIDEO_FRAME_RATE = 30;


    /**
     * 新添加的功能：
     * 在预览的时候给图片绘制标线，在距离上下边48px的位置绘制两条线；然后在底部绘制两个矩形
     */
    public static final int TOP_BOTTOM_MARGIN = 48;
    public static final int RECTANGLE_WIDTH_HEIGHT = 100;
    public static final int PUB_LINE_WIDTH = 2;// 所有标线的宽度

    /**
     * 打开声音的命令
     */
    public static final String[] SHELL_MEDIA = new String[]{"pt2369 1 1", "pt2369 0 128"};// 喇叭用于播放本地音乐

    // 授权应答码的位数
//    public static final int identificationLenght = 25;
    // 手机标识位默认是16位的，不够的话，在首位补零
//    public static final int phoneImeiLen = 16;


    // TODO 授权的URL
    public static final String HOST_URL = " http://adaspass.top:8088/";

    //    授权网址
    public static final String URL_AUTHORIZATION = HOST_URL + "api/product/pms";
    public static final String URL_LOAD = HOST_URL + "api/product/imei";


}
