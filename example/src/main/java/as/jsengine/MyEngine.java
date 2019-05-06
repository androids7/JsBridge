package as.jsengine;
import com.github.lzyzsd.jsbridge.*;
import as.mke.gametet.System.view.*;
import android.util.*;
import android.graphics.*;
import com.google.gson.*;
import org.json.*;
import android.widget.*;
import as.jsengine.MainActivity.User;
import as.jsengine.MainActivity.Location;
import java.io.*;
public class MyEngine
{
	MainView view;
	BridgeWebView web;
	MainActivity thiz;
	Bitmap bmpres[];
	
	static int bmploaction=0;
	
	public MyEngine(MainActivity thiz,BridgeWebView webview,MainView view){
		this.web=webview;
		this.view=view;
		this.thiz=thiz;
		
	}
	
	public void initregister(){
		
		
		bmpres=new Bitmap[255];
		
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
			
			
		web.registerHandler("refScr", new BridgeHandler() {

				@Override
				public void handler(String data, CallBackFunction function) {
					//	Log.i( "MyEngine","handler = submitFromWeb, data from web = " + data);

					
				    view.ref();

					function.onCallBack(null);
				}

			});
			
			
			
			web.registerHandler("drawText",new BridgeHandler(){
			
				@Override
				public void handler(String data,CallBackFunction function){
					
					
					String text=null;
					int x=0,y=0;
					
					int r=0,g=0,b=0;
				
					int size=18;
					Paint p=new Paint();
					
					String result="normal";
					
					try
					{
						JSONArray jo=new JSONArray(data);
						
						text=jo.getString(0);
						x=jo.getInt(1);
						y=jo.getInt(2);
						size=jo.getInt(3);
						JSONArray jj= jo.getJSONArray(4);
						
						r=jj.getInt(0);
						g=jj.getInt(1);
						b=jj.getInt(2);
						
						
					}
					catch (JSONException e)
					{
						
						result=e.toString();
						
					}
					
					p.setColor(Color.rgb(r,g,b));
					p.setTextSize(size);
					view.paper.drawText(text,x,y,p);
					function.onCallBack(result+"<br>");
				}
			});
			
			
			
			
			
		web.registerHandler("drawBitmapFromPath", new BridgeHandler() {

				@Override
				public void handler(String data, CallBackFunction function) {
			
			
					String imgpath=null;
					int x=0,y=0;

					String result="normal";
					try
					{
						JSONArray jo=new JSONArray(data);
						
						imgpath=jo.getString(0);
						x=jo.getInt(1);
						y=jo.get(2);
						
					}
					catch (JSONException e)
					{
						result=e.toString();
					}

					try
					{
						InputStream in=new FileInputStream(imgpath);
						
						bmpres[254]=BitmapFactory.decodeStream(in);
						
						
					}
					catch (FileNotFoundException e)
					{
						result=e.toString();
					}

					
					
				
						view.paper.drawBitmap(bmpres[0],x,y,null);
					
					
					function.onCallBack(result+"<br>");
			
			}});
			
			
			
			
			
		web.registerHandler("loadBitmapFromPath", new BridgeHandler() {

				@Override
				public void handler(String data, CallBackFunction function) {


				
				String result="normal";

				int location=bmploaction;
					try
					{
						InputStream in=new FileInputStream(data);
						bmpres[bmploaction] = BitmapFactory.decodeStream(in);
					
						//图片下标增加
						bmploaction++;
						
					}
					catch (Exception e)
					{
						result=e.toString();
					}


				//	view.paper.drawBitmap(bmpres[location],250,100,null);



					

					

					function.onCallBack(""+location);

				}});
			
			
			
		web.registerHandler("drawBitmapForData", new BridgeHandler() {

				@Override
				public void handler(String data, CallBackFunction function) {


					int imglocation=0;
					int x=0,y=0;

					String result="normal";
					try
					{
						JSONArray jo=new JSONArray(data);

						imglocation= Integer.parseInt( jo.getString(0));
						x=jo.getInt(1);
						y=jo.get(2);

					}
					catch (JSONException e)
					{
						result=e.toString();
					}

					



					view.paper.drawBitmap(bmpres[imglocation],x,y,null);

				//	Toast.makeText(thiz,result,0).show();
					function.onCallBack(result+"<br>");

				}});
			
			
			/*
			
		User user = new User();
		Location location = new Location();
		location.address = "SDU";
		user.location = location;
		user.name = "大头鬼";
*/
		web.callHandler("functionInJs", null
		//new Gson().toJson(user)
		, new CallBackFunction() {
				@Override
				public void onCallBack(String data) {

					//Toast.makeText(thiz,data,0).show();

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
