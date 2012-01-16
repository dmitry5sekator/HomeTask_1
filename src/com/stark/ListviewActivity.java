package com.stark;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import android.content.Context;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListviewActivity extends Activity 
{
        //private ArrayList <HashMap<String, Object>> myBooks = new ArrayList<HashMap<String,Object>>();
        private static final String id = "carid";
        private static final String name = "carname";
        private static final String url = "carurl";
        private static final String icon = "caricon";
        private static final String color = "itemlayout";
        
        
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView listView = (ListView)findViewById(R.id.list);
        getRequest(listView);
    }
    public void getRequest(ListView listView){
        
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://buyersguide.caranddriver.com/api/feed/?mode=json&q=make");
        try{
            HttpResponse response = client.execute(request);
            //txtResult.setText(request(response));
            String begin = request(response);
            examineJSONFile(listView,begin);
            //txtResult.setText(request(response));
        }catch(Exception ex){
            //txtResult.setText("Failed!");
        }
        
    }
    public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }
    void examineJSONFile(ListView listView,String str)
    {
    	try
    	{
    		String x = "";
    		JSONArray entries = new JSONArray(str.substring(str.indexOf("[")));
    		TreeMap <String,Item> myMap = new TreeMap<String,Item>();
    		ArrayList <HashMap<String, Object>> myBooks = new ArrayList<HashMap<String,Object>>();
    		TreeMap<Integer,Item> myB = new TreeMap<Integer,Item>();
    		HashMap<String, Object> hm;
    		for (int i=0;i<entries.length();i++)
    		{
    			JSONObject post = entries.getJSONObject(i);
    			myB.put(post.getInt("id"), new Item(post.getString("name"),post.getString("url"),R.drawable.fuck));
    		}
    		for(Entry<Integer, Item> entry : myB.entrySet()) 
    		{
    			Integer key = entry.getKey();
    			Item value = entry.getValue();
    			hm = new HashMap<String, Object>();
    			hm.put(id, key);
      	        hm.put(name, value.getName());
      	        hm.put(url, value.getUrl());
      	        hm.put(icon, value.getIcon());
      	        myBooks.add(hm);
    		}
    		SimpleAdapter adapter = new SimpleAdapter(this, myBooks, R.layout.list_item,
 	    		   new String[]{id,name,url,icon}, 
 	    		   new int[]{R.id.carid, R.id.carname,R.id.carurl,R.id.caricon});
    		listView.setAdapter(adapter);
    	}
    	catch (Exception je)
    	{
    		
    	}
    }
    private Bitmap getDrawableFromUrl(final String url)
    {
    	Bitmap bmImg = null;
    	URL myFileUrl =null; 
    	Drawable d = null;
    	try 
    	{
    		myFileUrl= new URL(url);
    		HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
    		conn.setDoInput(true);
    		conn.connect();
    		InputStream is = conn.getInputStream();
    		bmImg = BitmapFactory.decodeStream(is);
    		d =new BitmapDrawable(bmImg);
    		//ImageView view = (ImageView)findViewById(R.id.caricon);
    		//view.setImageBitmap(bmImg);
    	 }
    	catch (IOException e) 
    	{
    		e.printStackTrace();
    	}
    	return bmImg;
	}
}