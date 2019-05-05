package as.mke.gametet.System.graphics.button;
import as.mke.gametet.System.graphics.base.*;
import android.app.*;
import as.mke.gametet.System.view.*;
import android.view.View.*;
import android.view.*;
import android.graphics.*;
import as.mke.gametet.System.graphics.text.*;

public class GButton extends TouchView implements OnTouchListener
{
	String text;
	Paint p;
	OnTouchListener listener;
	int x,y;
	RectF fontsize;
	
	
	int lastX,lastY;
	
	//Activity act;
	GButtonEvent buttonevent;
	
	public GButton(){
	//Activity act){
		//super(act);
		p=new Paint();
		p.setTextSize(56);
		p.setColor(Color.BLACK);
		//this.act=act;
	}

	
	public void setText(String str){
		this.text=str;
	   Rect tmp=	TextUtils.getTextWidthHeight(p,str);
		fontsize=new RectF(tmp);
		
		
	}
	
	
	public void setPosition(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void setWidthHeight(int w,int h){
		Rect tmp=new Rect(0,0,w,h);
		fontsize.set(tmp);
		p.setTextSize(w*h/2);
	}
	
	public void setEvent(GButtonEvent be){
		
		this.buttonevent=be;
	}
	
	MainView view;
	
	@Override
	public void onDraw(MainView view)
	{
		this.view=view;
		/*
		view.setFocusableInTouchMode(true);
		view.requestFocus();
		
		view.setOnTouchListener(this);
	*/
		
		fontsize=new RectF(x,y-fontsize.height(),fontsize.width()+x+8,y+8);
		Paint rect=new Paint();
		rect.setColor(Color.WHITE);
		view.paper.drawRect	(fontsize,rect);
		view.paper.drawText(text,x,y,p);
	}

	@Override
	public boolean onTouch(View vv,MotionEvent event)
	{
		int x = (int) event.getX();  
        int y = (int) event.getY();  
        switch (event.getAction()) {  
            case MotionEvent.ACTION_DOWN:  
                // 记录触摸点坐标  
                lastX = x*ScreenProfile.ScrW/view.scrW;
				//ScreenProfile.getScreenWidth(act);  
                lastY = y*ScreenProfile.ScrH/view.scrH;
				//ScreenProfile.getScreenHeight(act);  
				
				if(isTouch(lastX,lastY,fontsize)){
					
					buttonevent.onClick();
				}
				
                break;  
            case MotionEvent.ACTION_MOVE:  
                // 计算偏移量  
                int offsetX = x - lastX;  
                int offsetY = y - lastY;  
                // 在当前left、top、right、bottom的基础上加上偏移量  
				/*
                layout(getLeft() + offsetX,  
					   getTop() + offsetY,  
					   getRight() + offsetX,  
					   getBottom() + offsetY);  
					   */
                break;  
        }
		return true;
	}

	
	public void setTouchEvent(MotionEvent event){
		int x = (int) event.getX();  
        int y = (int) event.getY();  
        switch (event.getAction()) {  
            case MotionEvent.ACTION_DOWN:  
                // 记录触摸点坐标  
                lastX = x*ScreenProfile.ScrW/view.scrW;
				//ScreenProfile.getScreenWidth(act);  
                lastY = y*ScreenProfile.ScrH/view.scrH;
				//ScreenProfile.getScreenHeight(act);  

				if(isTouch(lastX,lastY,fontsize)){

					buttonevent.onClick();
				}

                break;  
            case MotionEvent.ACTION_MOVE:  
                // 计算偏移量  
                int offsetX = x - lastX;  
                int offsetY = y - lastY;  
                // 在当前left、top、right、bottom的基础上加上偏移量  
				/*
				 layout(getLeft() + offsetX,  
				 getTop() + offsetY,  
				 getRight() + offsetX,  
				 getBottom() + offsetY);  
				 */
                break;  
        }
		
	}
	
	
	/*
//设置点击事件

	public void  setTouchEvent(OnTouchListener listener){
		this.listener=listener;
	}

	*/
	//getRectF用来给触控判断是否点击中范围
	public RectF getRectF(){
		return fontsize;
	}
	
	
	public boolean isTouch(int tx,int ty,RectF rec){
		
		return( tx>=rec.left&&tx<=x+rec.width())&&(ty>=rec.top&&ty<=y+rec.bottom);
	}
}
