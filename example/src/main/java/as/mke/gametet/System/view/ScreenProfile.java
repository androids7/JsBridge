package as.mke.gametet.System.view;
import android.content.*;
import android.hardware.display.*;
import android.util.*;
import android.app.*;

public class ScreenProfile
{
	public static int ScrW=1280;
	public static int ScrH=720;
	public static int  getScreenWidth(Activity act){
		DisplayMetrics dm = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
		
	}
	
	public static int  getScreenHeight(Activity act){

		DisplayMetrics dm = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
		
	}
	
}

