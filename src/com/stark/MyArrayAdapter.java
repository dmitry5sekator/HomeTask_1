package com.stark;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter
{
	/////////////
	
	//////////////
	ViewHolder holder;
	private final Context context;
	private ArrayList<Item> items;
	@SuppressWarnings("unchecked")
	public MyArrayAdapter(Context context, int ViewResourceId,ArrayList<Item> items) 
	{
		super(context, ViewResourceId, items);
		this.context = context;
		this.items = items;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
      View v = convertView;
      if (v == null) {
    	  
    	  LayoutInflater inflater = (LayoutInflater)context.getSystemService
    		      (Context.LAYOUT_INFLATER_SERVICE);
          v = inflater.inflate(R.layout.list_item, null);
         // holder.id = (TextView) v.findViewById(R.id.carid);
    	 // holder.name = (TextView) v.findViewById(R.id.carname);
    	  //holder.url = (TextView) v.findViewById(R.id.carurl);
    	  //holder.icon = (ImageView) v.findViewById(R.id.caricon);
      }
      
//      if(holder.id == null)
//      {
//    	  holder.id = (TextView) v.findViewById(R.id.carid);
//    	  holder.name = (TextView) v.findViewById(R.id.carname);
//    	  holder.url = (TextView) v.findViewById(R.id.carurl);
//    	  holder.icon = (ImageView) v.findViewById(R.id.caricon);
//      }
      Item o = items.get(position);
      if (o != null) {
              TextView id = (TextView) v.findViewById(R.id.carid);
              TextView name =  (TextView) v.findViewById(R.id.carname);
              TextView url = (TextView) v.findViewById(R.id.carurl);
              ImageView icon = (ImageView) v.findViewById(R.id.caricon);
              id.setText(o.getId());
              name.setText(o.getName());
              url.setText(o.getUrl());
              icon.setImageBitmap(o.getIcon());
      }
      
      return v;
		}
}
	
class ViewHolder {
	   public TextView id;
	   public TextView name;
	   public TextView url;
	   
	   public ImageView icon;
	   
	   
	   
	}

//private ArrayList<Order> items;
//
//public OrderAdapter(Context context, int textViewResourceId, ArrayList<Order> items) {
//        super(context, textViewResourceId, items);
//        this.items = items;
//}
//
//@Override
//public View getView(int position, View convertView, ViewGroup parent) {
//        View v = convertView;
//        if (v == null) {
//            LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = vi.inflate(R.layout.row, null);
//        }
//        Order o = items.get(position);
//        if (o != null) {
//                TextView tt = (TextView) v.findViewById(R.id.toptext);
//                TextView bt = (TextView) v.findViewById(R.id.bottomtext);
//                if (tt != null) {
//                      tt.setText("Name: "+o.getOrderName());                            }
//                if(bt != null){
//                      bt.setText("Status: "+ o.getOrderStatus());
//                }
//        }
//        return v;
//}