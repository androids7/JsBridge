package as.jsengine;
import com.github.lzyzsd.jsbridge.*;
import as.mke.gametet.System.view.*;
import android.util.*;
import android.graphics.*;
import com.google.gson.*;
import org.json.*;
import android.widget.*;

public class MyEngine
{
	MainView view;
	BridgeWebView web;
	MainActivity thiz;
	public MyEngine(MainActivity thiz,BridgeWebView webview,MainView view){
		this.web=webview;
		this.view=view;
		this.thiz=thiz;
	}
	
	public void initregister(){
		
		
		
		
		/*参数是json
		r:
		g:
		b:
		*/
		web.registerHandler("setColor", new BridgeHandler() {

				@Override
				public void handler(String data, CallBackFunction function) {
				//	Log.i( "MyEngine","handler = submitFromWeb, data from web = " + data);
					
				int r=0,g=0,b=0;
				
				String result=null;
				
				String names=null;
					try
					{
						//JSONObject jo=new JSONObject(data);
						JSONArray jo=new JSONArray(data);
						
				//		b=jo.getInt(1);
						
						
					/*	
						
						JSONArray my=new JSONArray();
						my.put(255);
						my.put(100);
						my.put(0);
						names=my.toString();
						*/
						/*
						
						
						
						
						
						*/
						r=jo.get(0);
						g=jo.getInt(1);
						b=jo.getInt(2);
						
					//	names=jo.names().toString();
						
					}
					catch (JSONException e)
					{
						result=e.toString();
					}

				    view.paper.drawColor(Color.rgb(r,g,b));
					
					function.onCallBack(result+"<br>"+names);
				}

			});
			
			
			
			/*
			
			
		web.callHandler("init", "data from Java", new CallBackFunction() {

				@Override
				public void onCallBack(final String data) {
					// TODO Auto-generated method stub
					//	Log.i(TAG, "reponse data from js " + data);
					thiz.runOnUiThread(
						new Runnable(){
					public void run(){
					Toast.makeText(thiz,data,0).show();
					}
					
					}
					
					);
					}
				

			});

		
			*/
			
		
	}
}
