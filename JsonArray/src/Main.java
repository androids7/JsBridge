import java.util.*;
import org.json.*;

public class Main
{
	public static void main(String[] args)
	{
			JSONArray arr=new JSONArray();
			arr.put("hello");
			arr.put(200);
			JSONArray a=new JSONArray();
			a.put(100);
			a.put(200);
			arr.put(a);
			
			
		try
		{
			JSONArray j=new JSONArray(arr.toString());
			
			System.out.println( "value:"+j.getJSONArray(2).getInt(0));
			
		}
		catch (JSONException e)
		{}
		System.out.println(arr.toString());
		
		
	}
}
