package pl.video.m4.mkvideo;

public class Options implements Comparable<Options>{
	private String name;
	private String data;
	private String path;
	
	public Options(String n,String d,String p)
	{
		name = n;
		data = d;
		path = p;
	}
	public String getName()
	{
		return name;
	}
	public String getData()
	{
		return data;
	}
	public String getPath()
	{
		return path;
	}
	
	@Override
	public int compareTo(Options opt) {
		if(this.name != null)
			return this.name.toLowerCase().compareTo(opt.getName().toLowerCase()); 
		else 
			throw new IllegalArgumentException();
	}
}