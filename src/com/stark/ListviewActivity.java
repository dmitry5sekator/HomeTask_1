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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListviewActivity extends Activity 
{
        public static ProgressBar progressBar;
        public static  ArrayList<Item> xxx;
        public static  ListView listView;
        public static Button button;
        public static EditText editText;
        
        public static  MyArrayAdapter ad;
        
        static Object g;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView)findViewById(R.id.list);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        button = (Button)findViewById(R.id.Button);
        editText = (EditText)findViewById(R.id.EditText);
        g = this;
        progressBar.setProgress(0);
       new MyTask().execute();
       button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String request = editText.getText().toString();
				ArrayList <Item> temp = new ArrayList<Item>();
				for(int i = 0;i < xxx.size();i++)
				{
					if(xxx.get(i).getName().indexOf(request) != -1)
					{
						temp.add(xxx.get(i));
					}
				}
				MyArrayAdapter temp_adapter = new MyArrayAdapter((Context)g,R.layout.list_item,temp);
				listView.setAdapter(temp_adapter);
				
			}
		});
    }
    public static void startUpList()
    {
    	progressBar.setVisibility(ProgressBar.GONE);
    	ad = new MyArrayAdapter((Context)g,R.layout.list_item,xxx);
    	listView.setAdapter(ListviewActivity.ad);
    	button.setVisibility(android.widget.Button.VISIBLE);
    	button.setText("Find cars");
    	editText.setVisibility(android.widget.EditText.VISIBLE);
    }
}