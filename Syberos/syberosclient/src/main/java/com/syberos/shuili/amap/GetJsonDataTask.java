package com.syberos.shuili.amap;

import android.os.AsyncTask;
import android.util.Log;

import com.syberos.shuili.config.GlobleConstants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/5/4
 * @time: 下午4:33
 * @email: ZhaoDongshuang@syberos.com
 */
public class GetJsonDataTask extends AsyncTask<String, Void, String> {

    private final static String TAG = GetJsonDataTask.class.getSimpleName();

    public GetJsonDataTask() {
        super();
    }

    /**
     * 这里的String参数对应AsyncTask中的第一个参数
     * 这里的String返回值对应AsyncTask的第三个参数
     * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
     * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
     */
    @Override
    protected String doInBackground(String... urls) {
        String path = GlobleConstants.mapServer + "/WEGIS-00-WEB_SERVICE/WSWebService";

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(60000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            String str = "{\"requestData\":{\"typeList\":[\"CWS\",\"DIKE\",\"HYST\",\"IRR\",\"PUST\",\"SD\",\"WAGA\"],\"radius\":0,\"targetId\":\"search.GeoSearchLogic\",\"geo\":'" + urls[0] + "'}}";
            pw.print("{\"requestData\":{\"typeList\":[\"CWS\",\"DIKE\",\"HYST\",\"IRR\",\"PUST\",\"SD\",\"WAGA\"],\"radius\":0,\"targetId\":\"search.GeoSearchLogic\",\"geo\":'"+ urls[0] +"'}}");
            pw.flush();
            pw.close();
            os.close();

            conn.connect();

            int statusCode = conn.getResponseCode();
            Log.d(TAG, "==========" + statusCode);
            if (isCancelled()) {
                Log.d(TAG, "GetJsonDataTask is Cancelled");
                return null;
            }
            String resp_content = "";
            if (statusCode == 200) {
                InputStream is = conn.getInputStream();
                resp_content = readInputStreamToString(is);
            }
            return resp_content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //字节流转换成字符串
    private static String readInputStreamToString(InputStream is) {
        byte[] result;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            baos.close();
            result = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return "获取数据失败";
        }
        return new String(result);
    }

}
