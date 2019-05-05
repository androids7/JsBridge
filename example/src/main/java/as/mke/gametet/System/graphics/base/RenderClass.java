package as.mke.gametet.System.graphics.base;
import as.mke.gametet.System.view.*;
import android.graphics.*;
import android.content.pm.*;
import android.app.*;

//基本渲染单位
public abstract class RenderClass
{
	/*
	Bitmap scr_cache;//屏幕缓存
	Canvas paper;
	
	
	MainView view;
	public RenderClass(MainView view){
		this.view=view;
	}
	
	
	*/
	
	public RenderClass(Activity act){
		
	}
	
	
	public abstract void onDraw(MainView view);
		
		//将图片绘制出来
		//view.paper.drawBitmap(scr_cache,0,0,null);
		
		
		
		
		
		
		
	
	
}
