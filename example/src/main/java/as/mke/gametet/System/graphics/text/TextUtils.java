package as.mke.gametet.System.graphics.text;
import android.graphics.*;

public class TextUtils
{
	
	public static Rect getTextWidthHeight(Paint pFont,String str){
		
	Rect rect = new Rect();
	pFont.getTextBounds(str, 0, str.length(), rect);
	return rect;
	}
	
}
