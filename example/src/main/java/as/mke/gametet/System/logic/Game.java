package as.mke.gametet.System.logic;
import as.mke.gametet.System.view.*;
import android.view.*;

public abstract class Game
{
	
	MotionEvent event;
	public abstract void logic();
	
	
	public Game(MainView view){
		
	}
	
	
	
	public abstract void onTouchEvent(MotionEvent event);
		
	
	
	public void pause(){
		
		
	}
	
	
	public void destroy(){
		
	}
	
	public void resume(){
		
	}
	
	
}
