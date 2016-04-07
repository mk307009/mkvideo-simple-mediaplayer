package pl.video.m4.mkvideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayer extends Activity implements OnClickListener, OnTouchListener{
	Context context;
	VideoView videoView;
	VideoController videoController;
	final String TAG = "VIDEOPLAYER";
	
	@SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		if (Common.isFullScreen() && display.getOrientation() == 1) requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (!Common.isFullScreen()) setContentView(R.layout.video);
        else setContentView(R.layout.video_full);
        
        context = getApplicationContext();
        
        VideoView videoView = (VideoView) findViewById(R.id.videoView1);
        Uri video = Uri.parse(Common.getUrl());

        videoController = new VideoController(this, this);
        videoController.setAnchorView(videoView);

        videoView.setMediaController(videoController);
        videoView.setVideoURI(video);
        videoView.requestFocus();
        videoView.start();
	}
	
	@Override
	public void onStart() {
	    super.onStart();
        
	    @SuppressWarnings("deprecation")
		int orientation = getWindowManager().getDefaultDisplay().getOrientation();
	    
	    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
	        Log.i(TAG, "PORTRAIT");
	    } else {
	    	Log.i(TAG, "LANDSPACE");
	    }
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
 
        switch (item.getItemId()) {
        case R.id.item1:{
        	Intent intent = new Intent(context, ChooseFile.class);
			startActivity(intent);
        }
        	break;
        case R.id.item2:{
        	Toast.makeText(this, Common.getFileName() ,Toast.LENGTH_SHORT).show();
        }
        	break;
        default:
        	
        }
        return true;
    }
	
	OnClickListener fullSCN = new OnClickListener() {
		@Override
	    public void onClick(View v) {
	    	Log.d(TAG, "on click\n ");
	    	if (Common.isFullScreen()) {
				Common.setFullScreen(false);
				Log.d(TAG, "false\n ");
				recreate();
			}else{
				Common.setFullScreen(true);
				Log.d(TAG, "true run\n ");
				recreate();
			}
	    }
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//Log.d(TAG, "CLICK FULL SCREEN\n "+Common.isFullScreen());
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		/*final ImageButton b = (ImageButton) findViewById(R.id.imageButton1);
		b.setVisibility(View.VISIBLE);
		Log.d(TAG, "touch ss\n ");
		b.postDelayed(new Runnable() {
            public void run() {
                b.setVisibility(View.INVISIBLE);
                Log.d(TAG, "touch run\n ");
            }
        }, 3000);*/
		return false;
	}
}