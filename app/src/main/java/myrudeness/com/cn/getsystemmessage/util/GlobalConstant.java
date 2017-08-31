package myrudeness.com.cn.getsystemmessage.util;

/**
 * Created by yanjunlin on 2017/8/31 16:19.
 */

public class GlobalConstant {

    /**
     * SD卡路径
     */
    public static final String SDCARD_PATH = "/mnt/sdcard/";

    /**
     * mtn应用的包名
     */
    public static final String MTN_APP_PACKAGE_NAME = "myrudeness.com.cn.getsystemmessage";
    /**
     * sd卡下应用包名下目录
     */
    public static final String SDCARD_BASE_PATH = SDCARD_PATH
            + MTN_APP_PACKAGE_NAME + "/";
    /**
     * 日志保存路径（SD卡）
     */
    public static final String LOG_PATH_SD = SDCARD_BASE_PATH + "Log/";

}
