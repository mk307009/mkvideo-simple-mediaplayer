package pl.video.m4.mkvideo;

import pl.video.m4.mkvideo.R.drawable;
import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;

public class VideoController extends MediaController{
	private ImageButton mute;
	private ImageButton fullScreen;
	Context myContext;
	final String TAG = "VIDEOCONTROLLER";
	private VideoPlayer vp;
	private AudioManager am;
	private int volume = 2;

	public VideoController(Context context, VideoPlayer player) {
		super(context);
		myContext = context;
		vp = player;
		// TODO Auto-generated constructor stub
	}
	 
	 @Override
	 public void setAnchorView(View view) {
	     super.setAnchorView(view);

	     mute = new ImageButton(myContext);
	     fullScreen = new ImageButton(myContext);
	     
	     if (Common.isMute()) mute.setImageResource(drawable.ic_muteoff);
	     else mute.setImageResource(drawable.ic_muteon);
	     mute.setBackgroundResource(0);
	     mute.setOnClickListener(muteListener);
	     
	     if (Common.isFullScreen()) fullScreen.setImageResource(drawable.ic_minscreen);
	     else fullScreen.setImageResource(drawable.ic_fullscreen);
	     fullScreen.setBackgroundResource(0);
	     fullScreen.setOnClickListener(fullListener);

	     LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	     params.gravity = Gravity.RIGHT;
	     params.topMargin = 15;

	     LayoutParams paramsFS = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	     paramsFS.gravity = Gravity.LEFT;
	     paramsFS.topMargin = 15;
	        
	     addView(mute, params);
	     addView(fullScreen, paramsFS);
	     
	}
	 
	OnClickListener muteListener = new OnClickListener() {
		    public void onClick(View v) {
		    	am = (AudioManager) myContext.getSystemService(Context.AUDIO_SERVICE);
		    	
		    	if (volume != am.getStreamVolume(AudioManager.STREAM_MUSIC)
		    			&& am.getStreamVolume(AudioManager.STREAM_MUSIC) != 0) volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		    	
		    	if (Common.isMute()) {
		    		Log.d(TAG, "mute off \n ringer: "+am.getStreamVolume(AudioManager.STREAM_MUSIC));
		    		am.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
		    		mute.setImageResource(drawable.ic_muteon);
		    		Common.setMute(false);
				}else{
					mute.setImageResource(drawable.ic_muteoff);
					am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
					Common.setMute(true);
					Log.d(TAG, "mute on \n ringer: "+am.getStreamVolume(AudioManager.STREAM_MUSIC));
				}
		    }
	};
	
	OnClickListener fullListener = new OnClickListener() {
	    public void onClick(View v) {
	    	if (Common.isFullScreen()) {
	    		Log.d(TAG, "full off \n ");
	    		fullScreen.setImageResource(drawable.ic_fullscreen);
	    		Common.setFullScreen(false);
	    		vp.recreate();
			}else{
				fullScreen.setImageResource(drawable.ic_minscreen);
				Common.setFullScreen(true);
				Log.d(TAG, "full on \n ");
				vp.recreate();
			}
	    }
	};
	
	
}
