package com.yzeng.hackernews.test.dagger.utils;


import android.util.Log;

import java.io.IOException;
import java.net.URI;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockInterceptor implements Interceptor {
    private final static String TAG = MockInterceptor.class.getSimpleName();

    public final static int RESPONSE_WITH_COMMENTS = 1;
    public final static int RESPONSE_WITHOUT_COMMENTS = 2;

    final int type;

    public MockInterceptor(int type) {
        this.type = type;

    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response;
        {
            Log.d(TAG, "---- DEBUG --- DEBUG -- DEBUG - DEBUG -- DEBUG --- DEBUG ----");
            Log.d(TAG, "----                FAKE SERVER RESPONSES                ----");
            String responseString;
            // Get Request URI.
            final  URI uri = chain.request().url().uri();
            Log.d(TAG, "---- Request URL: " + uri.toString());
            // Get Query String.
            final String query = uri.getPath();
            // Parse the Query String.

            if(query.contains("item")){
                switch (type)
                {
                    case RESPONSE_WITH_COMMENTS:
                        responseString = Utils.STORY_STR_COMMENTS;
                        break;
                    case RESPONSE_WITHOUT_COMMENTS:
                        responseString = Utils.STORY_STR_NO_COMMENTS;
                        break;
                    default:
                        responseString = Utils.STORY_STR_COMMENTS;
                        break;
                }
            }else if(query.contains("topstories"))
            {
                responseString = Utils.LIST_STR;
            }
            else {
                responseString = "";
            }

            response = new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        }

        return response;
    }
}
