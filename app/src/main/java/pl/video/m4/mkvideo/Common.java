package pl.video.m4.mkvideo;

import android.os.Environment;

public class Common {
	static private String url;
	static private boolean isFullScreen = false;
	static private boolean isPlaylist = false;
	static private boolean isMute = false;
	static private String fName = null;
	
    static void setUrl(String string){
    	url = string;
    }
    
    static String getUrl(){
    	//if (url == null) url = Environment.getExternalStorageDirectory().getPath()+"/Kiler.mp4";
    	return url;
    }
    
    static void setFullScreen(boolean full){
    	isFullScreen = full;
    }
    
    static boolean isFullScreen(){
    	return isFullScreen;
    }
    
    static void setFileName(String name){
    	fName = name;
    }
    
    static String getFileName(){
    	return fName;
    }
    
    static void setMute(boolean mute){
    	isMute = mute;
    }
    
    static boolean isMute(){
    	return isMute;
    }
    
    static void setIsPlaylist(boolean play){
    	isPlaylist = play;
    }
    
    static boolean isOnPlaylist(){
    	return isPlaylist;
    }
}