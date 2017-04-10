package com.bing.lan.asynchttpclientdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testAsyncHttpClient();
    }

    private void testAsyncHttpClient() {
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("key", "value");
        params.put("more", "data");

        AsyncHttpResponseHandler responseHandler = new ResponseHandler();
        AsyncHttpResponseHandler jsonHandler = new JsonHandler();
        AsyncHttpResponseHandler textHandler = new TextHandler();
        client.get("http://www.baidu.com", params, textHandler);
    }

    class JsonHandler extends JsonHttpResponseHandler {

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            // Handle resulting parsed JSON response here
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
            // Handle resulting parsed JSON response here
        }
    }

    class TextHandler extends TextHttpResponseHandler {

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {

        }
    }

    class ResponseHandler extends AsyncHttpResponseHandler {

        @Override
        public void onStart() {
            // called before request is started
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] response) {
            // called when response HTTP status is "200 OK"

            log.d("onSuccess(): " + statusCode);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
        }

        @Override
        public void onRetry(int retryNo) {
            // called when request is retried
        }
    }
}
