package pl.video.m4.mkvideo;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseFile extends ListActivity {
	Context context;
    private File currentDir;
    private FileArrayAdapter adapter;
   

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();  
        currentDir = new File(Environment.getExternalStorageDirectory().getPath());
        fill(currentDir);
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
        	currentDir = new File(Environment.getExternalStorageDirectory().getPath());
            fill(currentDir);
        }
        	break;
        case R.id.item2:
        	//whichElement = "Video Player";
        	if (Common.getUrl() == null){
        		Toast.makeText(this, "Nie wybrano pliku." ,Toast.LENGTH_SHORT).show();
        	}else{
        	Intent intent = new Intent(context, VideoPlayer.class);
			startActivity(intent);
        	}
        	break;
        default:
        	//whichElement = "none";
        }
        return true;
    }
    
    private void fill(File f)
    {
    	FileFilter formatFilter = new FileFilter() {
			public boolean accept(File file) {
				String pathname = file.getName().toUpperCase();
				if (pathname.endsWith(".M4A") || pathname.endsWith(".MP4") || pathname.endsWith(".3GP")
						 || pathname.endsWith(".AAC") || pathname.endsWith(".MP3")
						 || pathname.endsWith(".FLAC") || pathname.endsWith(".OGG")
						 || pathname.endsWith("WAV") || pathname.endsWith(".WEBM")){
					return true;
				}
				else if (file.isDirectory()){
					return true;
				}
				return false;
			}
    	};
    	
    	 File[]dirs = f.listFiles(formatFilter);
		 this.setTitle("Current Dir: "+f.getName());
		 List<Options>dir = new ArrayList<Options>();
		 List<Options>fls = new ArrayList<Options>();
		 	
		 try{
			 for(File ff: dirs)
			 {
				 
				if(ff.isDirectory())
					dir.add(new Options(ff.getName(),"Folder",ff.getAbsolutePath()));
				else
				{
					float x;
					x = ff.length();
					x = x / 1048576;
					x *= 100;
			        x = Math.round(x);
			        x /= 100;
					fls.add(new Options(ff.getName(),"File Size: "+x+"MB",ff.getAbsolutePath()));
				}
			 }
		 }catch(Exception e)
		 {  }
		 
		 Collections.sort(dir);
		 Collections.sort(fls);
		 dir.addAll(fls);
		 if(!f.getName().equalsIgnoreCase("sdcard"))
			 dir.add(0,new Options("..","Parent Directory",f.getParent()));
		 adapter = new FileArrayAdapter(ChooseFile.this,R.layout.file_choose,dir);
		 this.setListAdapter(adapter);
    }
    
    @Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(list, view, position, id);
		Options opt = adapter.getItem(position);
		if(opt.getData().equalsIgnoreCase("folder")||opt.getData().equalsIgnoreCase("parent directory")){
				currentDir = new File(opt.getPath());
				fill(currentDir);
		}
		else
		{
			onFileClick(opt);
		}
	}
    
    private void onFileClick(Options opt)
    {	
    	//Log.d(TAG, ": "+o.getPath()+"\n");
    	if (!Common.isOnPlaylist()){
    		Common.setUrl(opt.getPath());
    		Common.setFileName(opt.getName());
    		Intent intent = new Intent(context, VideoPlayer.class);
    		startActivity(intent);
    	} else {
    		Toast.makeText(this, "Dodano: "+opt.getName(), Toast.LENGTH_SHORT).show();
    	}
    }
}
