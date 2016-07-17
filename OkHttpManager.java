package com.mooc.test.Utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xinghe on 16/7/17.
 */
public class OkHttpManager {
    private OkHttpClient client;
    private static OkHttpManager oKHttpManager;
    private final Handler mHandler;

    private OkHttpManager() {
        client = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpManager getInstance() {
        if (oKHttpManager == null) {
            oKHttpManager = new OkHttpManager();
        }
        return oKHttpManager;
    }

    //数据回调接口
    public interface DataCallBack{
        void requestFailure(Call call, IOException e);
        void requestSuccess(String result);

    }

    //post方法请求
    public static void post(String url, String json,DataCallBack callBack){
        getInstance().postAsyc(url,json,callBack);
    }
    private void postAsyc(String url, String json, final DataCallBack callBack){
      final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(call,e,callBack);
            }

            @Override
            public void onResponse(Call call, Response response)  {
                try {
                    String result=response.body().string();
                    deliverDataSuccess(result,callBack);
                } catch (IOException e) {
                    deliverDataFailure(call,e,callBack);
                }

            }
        });
        }

    private void deliverDataFailure(final Call call, final IOException e, final DataCallBack callBack) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                   if (callBack!=null){
                       callBack.requestFailure(call,e);
                   }
                }
            });
    }
    private void deliverDataSuccess(final String result, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack!=null){
                    callBack.requestSuccess(result);
                }
            }
        });
    }
}


