package as.mke.gametet.System.graphics.base;
import android.app.*;
import as.mke.gametet.System.view.*;
import android.view.View.*;
import android.view.*;

public abstract class TouchView implements OnTouchListener
{
	public TouchView(){
		
	}
	public abstract void onDraw(MainView view);
	//public abstract void onTouch(OnTouchListener listener);
	//public abstract boolean onTouch(MotionEvent p2);
}
