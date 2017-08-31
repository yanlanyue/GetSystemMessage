package myrudeness.com.cn.getsystemmessage.util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;


/**
 * <一句话功能简述>
 * 程序运行异常扑捉类
 */
public class UEHandler implements Thread.UncaughtExceptionHandler
{
    private static final String TAG = "===UEHandler===";
    
    /**
     * 磁盘剩余控件不能小于100k
     */
    private static final int FREE_SIZE = 1024 * 1024;
    
    private File fileErrorLog;
    
    /**
     *构造器
     */
    public UEHandler()
    {
    }
    
    @Override
    public void uncaughtException(Thread thread, Throwable ex)
    {
        // fetch Excpetion Info
        String info = null;
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;
        try
        {
            baos = new ByteArrayOutputStream();
            printStream = new PrintStream(baos);
            ex.printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            info = new String(data);
            data = null;
        }
        catch (Exception e)
        {
            Log.e("错误日志---->", e.toString());
        }
        finally
        {
            try
            {
                if (printStream != null)
                {
                    printStream.close();
                }
                if (baos != null)
                {
                    baos.close();
                }
            }
            catch (Exception e)
            {
                Log.e("错误日志---->", e.toString());
            }
        }
        long threadId = thread.getId();
        StringBuffer sb = new StringBuffer();
        sb.append("Thread.getName()=" + thread.getName() + " id=" + threadId
                + " state=" + thread.getState());
        sb.append("\n" + info);
        sb.append("Error[\n" + info + "]");
        
        writeErrorLog(sb.toString());
        // kill App Progress
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    
    /**
     * 异常日志<BR>
     * 异常日志记录到特定的文件
     * @param content
     */
    private void writeErrorLog(String content)
    {
        if (!android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED)
                || FileHelper.getFreeSD() < FREE_SIZE)
        {
            return;
        }
        
        File f = new File(GlobalConstant.LOG_PATH_SD);
        if (!f.exists())
        {
            f.mkdirs();
        }
        fileErrorLog = new File(GlobalConstant.LOG_PATH_SD + "exception_log_"
                + System.currentTimeMillis() + ".txt");
        FileOutputStream fos = null;
        try
        {
            fileErrorLog.createNewFile();
            fos = new FileOutputStream(fileErrorLog);
            fos.write(content.getBytes());
        }
        catch (Exception e)
        {
            Log.e(TAG, e.toString());
        }
        finally
        {
            try
            {
                if (fos != null)
                {
                    fos.close();
                }
            }
            catch (Exception e)
            {
                Log.e(TAG, e.toString());
            }
        }
    }
}
