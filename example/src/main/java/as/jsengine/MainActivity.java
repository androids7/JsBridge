package as.jsengine;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;
import as.mke.gametet.System.view.*;
import android.webkit.*;
import android.*;
import android.support.v4.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.content.pm.*;
import android.widget.*;


public class MainActivity extends Activity implements OnClickListener {

	
	
	// sd卡权限 
	private String[] SdCardPermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE}; 
//手机状态权限 
	private String[] readPhoneStatePermission = {Manifest.permission.READ_PHONE_STATE}; 
//定位权限 
	private String[] locationPermission = {Manifest.permission.ACCESS_FINE_LOCATION};
	
	
	
	
	private final String TAG = "MainActivity";

	BridgeWebView webView;

	Button button;
	
	MyEngine eng;

	int RESULT_CODE = 0;

	ValueCallback<Uri> mUploadMessage;

	ValueCallback<Uri[]> mUploadMessageArray;

    public static class Location {
        String address;
    }

    public static class User {
        String name;
        Location location;
        String testStr;
    }

	MainView main;
	
	
	protected void judgeSdCardPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, SdCardPermission[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, SdCardPermission, 100);
            }else{
				doSdCardResult();
			}
        }else{
			doSdCardResult();
        }
    }
	
	
	
	private void doSdCardResult(){
		
		

		/*
        User user = new User();
        Location location = new Location();
        location.address = "SDU";
        user.location = location;
        user.name = "大头鬼";

        webView.callHandler("functionInJs", new Gson().toJson(user), new CallBackFunction() {
				@Override
				public void onCallBack(String data) {

				}
			});

        webView.send("hello");
		
		*/
	}
	
	@Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                  //  doSdCardResult();
                }
            }else{
				//弹出对话框提示用户去设置，如果不设置直接退出
			}
        }
        if (requestCode == 200) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 //   doPhoneStateResult();
                }
                else{
					//弹出对话框提示用户去设置，如果不设置直接退出
				}
            }
        }
        if (requestCode == 300) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   // doLocationResult();
                }
                else{
					//弹出对话框提示用户去设置，如果不设置直接退出
				}
            }
        }
    }
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		
		
		//main=new MainView(this);
		
		
		setContentView(R.layout.activity_main);
		
		main=(MainView)findViewById(R.id.view);
		main.setHandle(this);
		main.init();
		
        webView =findViewById(R.id.webView);
		//new BridgeWebView(this);
		//(BridgeWebView) findViewById(R.id.webView);
		
		judgeSdCardPermission();

		
		
		
		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(this);

		webView.setDefaultHandler(new DefaultHandler());

		webView.setWebChromeClient(new WebChromeClient() {

			@SuppressWarnings("unused")
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
				this.openFileChooser(uploadMsg);
			}

			@SuppressWarnings("unused")
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
				this.openFileChooser(uploadMsg);
			}

			public void openFileChooser(ValueCallback<Uri> uploadMsg) {
				mUploadMessage = uploadMsg;
				pickFile();
			}

			@Override
			public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
				mUploadMessageArray = filePathCallback;
				pickFile();
				return true;
			}
		});

		
		
		
		
	

		//webView.loadUrl("file:///storage/emulated/0/XL-Html/Project/56900089/demo.html");
		
		
		webView.loadUrl("file:///sdcard/1/demo.html");
		
		
		 eng=new MyEngine(this,webView,main);
		eng.initregister();
		
		
	}

	public void pickFile() {
		Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
		chooserIntent.setType("image/*");
		startActivityForResult(chooserIntent, RESULT_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == RESULT_CODE) {
			if (null == mUploadMessage && null == mUploadMessageArray){
				return;
			}
			if(null!= mUploadMessage && null == mUploadMessageArray){
				Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
				mUploadMessage.onReceiveValue(result);
				mUploadMessage = null;
			}

			if(null == mUploadMessage && null != mUploadMessageArray){
				Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
				mUploadMessageArray.onReceiveValue(new Uri[]{result});
				mUploadMessageArray = null;
			}

		}
	}

	@Override
	public void onClick(View v) {
		if (button.equals(v)) {
			
			
			/*
            webView.callHandler("init", "data from Java", new CallBackFunction() {

				@Override
				public void onCallBack(String data) {
					// TODO Auto-generated method stub
					Log.i(TAG, "reponse data from js " + data);
					Toast.makeText(MainActivity.this,data,0).show();
				}

			});
			*/
			
			User user = new User();
			Location location = new Location();
			location.address = "SDU";
			user.location = location;
			user.name = "大头鬼";
			
			webView.callHandler("functionInJs", new Gson().toJson(user), new CallBackFunction() {
					@Override
					public void onCallBack(String data) {

						
					}
				});
			
		}

	}

}
