package com.cs178.gmaps_trial1;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;

//from vogella trial
public class MainActivity extends Activity {
	 static final LatLng main = new LatLng(10.30046, 123.88822);
	  static final LatLng tc = new LatLng(10.35410, 123.91145);
	  static final LatLng bacayan = new LatLng(10.37478, 123.91919);
	  private GoogleMap map;
	  
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        
        final Marker main_ = map.addMarker(new MarkerOptions().position(main)
                .title("USC - Main"));
        final Marker tc_ = map.addMarker(new MarkerOptions().position(tc)
                .title("USC - TC"));
        final Marker bc_ = map.addMarker(new MarkerOptions().position(bacayan)
                .title("Bacayan"));
            
        
         // Move the camera instantly to hamburg with a zoom of 15.
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(tc, map.getMaxZoomLevel()));

            // Zoom in, animating the camera.
          //  map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
    			
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            	
    		public boolean onMarkerClick(Marker marker) {
    			// TODO Auto-generated method stub
    			alert.setTitle("Want to travel?");
    			
    		if(marker.equals(tc_)){
    		alert.setMessage("Go to USC - Main Campus?").setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog,int id) {
    							map.animateCamera(CameraUpdateFactory.newLatLngZoom(main, map.getMaxZoomLevel()));
    				}
    			  }).setNegativeButton("No",new DialogInterface.OnClickListener() {
    				
    				  
    		public void onClick(DialogInterface dialog,int id) {}
    		});
    		} else if(marker.equals(main_)){
    		alert.setMessage("Go to Bacayan?").setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog,int id) {
    					map.animateCamera(CameraUpdateFactory.newLatLngZoom(bacayan, map.getMaxZoomLevel()));
    				}
    			  }).setNegativeButton("No",new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog,int id) {}
    			});
    				
    			}else if(marker.equals(bc_)){
    			alert.setMessage("Go to US C - TC Campus?")
    				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog,int id) {
    					map.animateCamera(CameraUpdateFactory.newLatLngZoom(tc, map.getMaxZoomLevel()));
    				}
    			  }).setNegativeButton("No",new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog,int id) {}
    		});
    		}
    		
    		AlertDialog ad = alert.create();
    		ad.show();
    			
    		return false;
    			}
    		});
        }

          @Override
          public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.activity_main, menu);
            return true;
          }

        }

/* public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SupportMapFragment fragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, fragment).commit();
    }
} */