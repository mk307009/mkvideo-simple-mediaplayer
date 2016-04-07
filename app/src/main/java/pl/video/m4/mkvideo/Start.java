package pl.video.m4.mkvideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Start extends Activity{
	Context context;
	final String TAG = "START";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = getApplicationContext();       
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        //String whichElement = "";
 
        switch (item.getItemId()) {
        case R.id.item1:{
        	//whichElement = "Choose File";
        	Common.setIsPlaylist(false);
        	Intent intent = new Intent(context, ChooseFile.class);
			startActivity(intent);
        }
        	break;
        case R.id.item2:{
        	//whichElement = "Video Player";
        	if (Common.getUrl() == null){
        		Toast.makeText(this, "Nie wybrano pliku." ,Toast.LENGTH_SHORT).show();
        	}else{
        	Intent intent = new Intent(context, VideoPlayer.class);
			startActivity(intent);
        	}
        }
        	break;
        /*case R.id.item3:{
        	whichElement = "Playlist";
        	Common.setIsPlaylist(true);
        	Intent intent = new Intent(context, ChooseFile.class);
			startActivity(intent);
        }
        	break;*/
        default:
        	//whichElement = "none";
        }
 
        return true;
    }
}