package pl.video.m4.mkvideo;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class FileArrayAdapter extends ArrayAdapter<Options>{

	private Context c;
	private int id;
	private List<Options>items;
	
	public FileArrayAdapter(Context context, int textViewResourceId,
			List<Options> objects) {
		super(context, textViewResourceId, objects);
		c = context;
		id = textViewResourceId;
		items = objects;
	}
	
	public Options getItem(int i)
	 {
		 return items.get(i);
	 }
	
	 @Override
       public View getView(int position, View convertView, ViewGroup parent) {
               View v = convertView;
               if (v == null) {
                   LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   v = vi.inflate(id, null);
               }
               final Options o = items.get(position);
               if (o != null) {
                       TextView t1 = (TextView) v.findViewById(R.id.TextView01);
                       TextView t2 = (TextView) v.findViewById(R.id.TextView02);
                       
                       if(t1!=null)
                       	t1.setText(o.getName());
                       if(t2!=null)
                       	t2.setText(o.getData());
                       
               }
               return v;
       }
}
