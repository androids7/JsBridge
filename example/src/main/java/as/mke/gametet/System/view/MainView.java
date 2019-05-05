package as.mke.gametet.System.view;
import android.view.*;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceHolder;
//设置游戏视图

import android.content.*;
import android.graphics.*;
import android.app.*;
import as.mke.gametet.System.graphics.button.*;
import java.util.*;
import android.util.*;

public class MainView extends SurfaceView implements Callback

{
	public  Canvas paper;//画布
	
	int SCRW,SCRH;
	//活动句柄
	Activity activity;
	
	SurfaceHolder holder;//容器句柄
	Thread td;//绘画线程
	public int scrW,scrH;
	
	public boolean thread_sp=true;//线程开关
	
	//缓存图片格式
	Bitmap.Config scr_config;
	
	Bitmap cvs_cache;//屏幕上缓存
	
	
	Rect src,dst;//屏幕缩放比例
	
	
	List<GButton> btn_list;
	
	
	
	public MainView(Context ctx){
		super(ctx);
		holder=this.getHolder();
		holder.addCallback(this);
		
	    setFocusableInTouchMode(true);
		
		SCRW=ScreenProfile.ScrW;
		SCRH=ScreenProfile.ScrH;
	}

	public MainView(Context ctx,AttributeSet set){
		super(ctx,set);
		holder=this.getHolder();
		holder.addCallback(this);

	    setFocusableInTouchMode(true);

		SCRW=ScreenProfile.ScrW;
		SCRH=ScreenProfile.ScrH;
	}
	
	
	
	//设置句柄
	public void setHandle(Activity act){
		this.activity=act;
		
		
	}
	
	
	
	//初始化内容
	public void init(){
		
		/*
		this.setZOrderOnTop(true);
		this.setZOrderMediaOverlay(true);
		
		*/
		//配置图片格式
	   scr_config=Bitmap.Config.ARGB_8888;
	   
	   //创建屏幕缓存图片
		cvs_cache=Bitmap.createBitmap(SCRW,SCRH,scr_config);
		
		paper=new Canvas(cvs_cache);
		
		
		
		//缩放画布到屏幕上
		src=new Rect(0,0,ScreenProfile.getScreenWidth(activity),ScreenProfile.getScreenHeight(activity));
		dst=new Rect(0,0,SCRW,SCRH);
		
		scrW=ScreenProfile.getScreenWidth(activity);
		scrH=ScreenProfile.getScreenHeight(activity);
		
		//初始化按钮控件
		btn_list=new ArrayList<GButton>();
	}
	
	
	
	public void addButton(GButton btn){
		btn_list.add(btn);
	}
	
	
	
	@Override
	public void surfaceCreated(SurfaceHolder p1)
	{
		thread_sp=true;
		

		//绘画代码在里面执行
		td=new Thread(){
			public void run(){




				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{}


				//只要开关是开的就一直执行
				while(thread_sp){

					Canvas canvas=holder.lockCanvas();

					//如果能获取到屏幕句柄才操作
					if(canvas!=null){

						/**这里进行绘制操作*/

						canvas.drawBitmap(cvs_cache,dst,src,null);


						/***************/


						//释放屏幕句柄
						holder.unlockCanvasAndPost(canvas);

					}



				}
			}
		};
		
		
		td.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder p1)
	{
		
		

		//关闭线程开关
		thread_sp=false;
		
		
		//释放资源
		//relasedRes();
	}

	@Override
	public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4)
	{
		// TODO: Implement this method
		
		//屏幕可能切换等等
		
		//init();
		
	}

	
//启游戏动线程
	public void runGame(){
		td.start();
		
	}
	
	
	
	//返回画布句柄
	public Canvas getPaper(){
		
		return paper;
	}
	public void relasedRes(){
		
		
		
		//回收屏幕缓存
		cvs_cache.recycle();
	}

	public MotionEvent event;
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		
		for(int i=0;i<btn_list.size();i++){
			
			btn_list.get(i).setTouchEvent(event);
		}
		
		
		
		//this.event=event;
		//super.onTouchEvent(event);
		
		return true;
	}

	
	public MotionEvent getTouchEvent(){
		return event;
	}
	
	
	
}
