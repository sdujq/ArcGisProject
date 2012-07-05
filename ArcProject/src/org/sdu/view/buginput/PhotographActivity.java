package org.sdu.view.buginput;

import org.sdu.gis.R;

import android.app.Activity; 
import android.content.Intent; 
import android.graphics.Bitmap; 
import android.os.Bundle; 
import android.provider.MediaStore; 
import android.util.Log; 

public class PhotographActivity extends Activity { 
    /** Called when the activity is first created. */ 
    @Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.camera_surface); 
        try { 
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
             startActivityForResult(intent, 0); 
        } catch (Exception e) { 
            System.out.println(e.getMessage());
        } 
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    { 
        try { 
            if (requestCode != 0) { 
                return; 
            } 
            super.onActivityResult(requestCode, resultCode, data); 
            Bundle extras = data.getExtras(); 
            Bitmap b = (Bitmap) extras.get("data"); 
            /*
               得到图片对图片处理...
            */ 
            
              
        } catch (Exception e) { 
            // TODO: handle exception 
            System.out.println(e.getMessage()); 
        } 
    } 

}