package ueba;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.concurrent.TimeUnit;
//this class is used for getting the httpconnection
public class httpconnection {
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout((1000), TimeUnit.SECONDS)
            .writeTimeout((1000), TimeUnit.SECONDS)
            .readTimeout((1000), TimeUnit.SECONDS)
            .build();

    public static OkHttpClient getconnection()
    {
        return okHttpClient;

    }
    public Request postresponse(String request, String url, String ueba_csrf, String cookie, String content_type)
    {

        RequestBody formData = new FormBody.Builder()
                .add("req", request)
                .add("ueba_csrf", ueba_csrf)
                .build();
        Request httpReq = new Request.Builder()
                .addHeader("Cookie", cookie)
                .addHeader("Content-Type",content_type)
                .url(url)
                .post(formData)
                .build();

        return  httpReq;
    }
    public Request getresponse(String url,String cookie,String contenttype)
    {
        Request request = new Request.Builder()
                .addHeader("Cookie",cookie)
                .addHeader("Content-Type",contenttype)
                .url(url)
                .build();
        return request;
    }

}
