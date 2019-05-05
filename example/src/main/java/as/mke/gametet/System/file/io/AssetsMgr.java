package as.mke.gametet.System.file.io;
import java.io.*;
import android.content.*;

public class AssetsMgr
{
	public InputStream getInputStream(Context c,String filename) throws Exception{
	 return c.getAssets().open(filename);
	}
	
	public void writeByteStream(byte[] data,String filename) throws Exception{
		
		File f=new File(filename);
		FileOutputStream fout=new FileOutputStream(f);
		
		fout.write(data);
		fout.close();
		
	}
	
	
	
	
}
