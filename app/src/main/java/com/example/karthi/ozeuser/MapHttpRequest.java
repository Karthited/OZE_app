package com.example.karthi.ozeuser;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthi on 11/19/2016.
 */

public class MapHttpRequest extends AsyncTask<LatLng, Void, Void> {
    String host = "";
    String api = "";
    String id = "";

    @Override
    protected Void doInBackground(LatLng... args) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test");

        LatLng latLng = (LatLng)args[0];
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("latitude", String.valueOf(latLng.latitude)));
        params.add(new BasicNameValuePair("longitude", String.valueOf(latLng.longitude)));

        //json
        JSONArray jsona=new JSONArray();

        jsona.put(String.valueOf(latLng.latitude));
        jsona.put(String.valueOf(latLng.longitude));

        String jsons=jsona.toString();
        try {
            StringEntity se=new StringEntity(jsons);
            httpPost.setEntity(se);
            httpPost.setHeader("Content-Type","application/json");
            //
            //httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            Log.d("response", httpResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
        };
        return null;
    }
}
