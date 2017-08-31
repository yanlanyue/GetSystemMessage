package myrudeness.com.cn.getsystemmessage.util;

import android.os.Environment;
import android.os.StatFs;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 */
public class FileHelper
{
    /**
     * 获取SD卡的剩余空间
     * 
     * @return SD卡的剩余的字节数
     */
    public static long getFreeSD()
    {
        long nAvailableCount = 0l;
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
                .getPath());
        long lSize = stat.getBlockSize();
        long lBlock = stat.getAvailableBlocks();
        nAvailableCount = lSize * lBlock;
        return nAvailableCount;
    }
}
