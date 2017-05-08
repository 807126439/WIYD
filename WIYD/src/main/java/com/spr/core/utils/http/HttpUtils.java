package com.spr.core.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

public class HttpUtils {

	/**
	 * 发送GET请求
	 * @param path 请求路径
	 * @param params 请求参数
	 * @param encoding 编码
	 * @return 请求是否成功
	 */
	public static  byte[] sendGETRequest(String path, Map<String, String> params, String ecoding) throws Exception{

		StringBuilder url = new StringBuilder(path);
		url.append("?");
		for(Map.Entry<String, String> entry : params.entrySet()){
			url.append(entry.getKey()).append("=");
			url.append(URLEncoder.encode(entry.getValue(), ecoding));
			url.append("&");
		}
		url.deleteCharAt(url.length() - 1);
		HttpURLConnection conn = (HttpURLConnection)new URL(url.toString()).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
	
		if(conn.getResponseCode() == 200){
			return readStream(conn.getInputStream());
		}
		return null;
	}
	

	/**
	 * 发送Post请求
	 * @param path 请求路径
	 * @param params 请求参数
	 * @param encoding 编码
	 * @return 请求是否成功
	 */
	public static byte[] sendPOSTRequest(String path, Map<String, String> params, String encoding) throws Exception{

		StringBuilder data = new StringBuilder();
		if(params!=null && !params.isEmpty()){
			for(Map.Entry<String, String> entry : params.entrySet()){
				data.append(entry.getKey()).append("=");
				data.append(URLEncoder.encode(entry.getValue(), encoding));
				data.append("&");
			}
			data.deleteCharAt(data.length() - 1);
		}
		byte[] entity = data.toString().getBytes();//生成实体数据
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);//允许对外输出数据

			
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entity);
		
		if(conn.getResponseCode() == 200){
			/*System.out.println("Set-Cookies："+conn.getHeaderField("Cookies"));
			System.out.println(conn.getHeaderFields().toString());*/
			
			return readStream(conn.getInputStream());
		}
		
		return null;
		
		
	}
	
	
	/**
	 * 发送Post请求
	 * @param path		地址
	 * @param params	url地址后面参数
	 * @param formData	传送数据
	 * @param encoding	编码
	 * @return
	 * @throws Exception
	 */
	public static byte[] sendPOSTRequest2(String path, Map<String, String> params,String formData,String encoding) throws Exception{
		StringBuilder url = new StringBuilder(path);
		url.append("?");
		for(Map.Entry<String, String> entry : params.entrySet()){
			url.append(entry.getKey()).append("=");
			url.append(URLEncoder.encode(entry.getValue(), encoding));
			url.append("&");
		}	
		url.deleteCharAt(url.length() - 1);
		
		
		byte[] entity = formData.getBytes("utf-8");//生成实体数据
		HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);//允许对外输出数据
			 
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
		conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));

		OutputStream outStream = conn.getOutputStream();
		outStream.write(entity);
		System.out.println(conn.getResponseCode());
		if(conn.getResponseCode() == 200){			
			
			return readStream(conn.getInputStream());
		}
		
		return null;
		
		
	}
	
	/**
	 * 读取流
	 * @param inStream
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while( (len=inStream.read(buffer)) != -1){
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}
	
	
	
	public static void main(String[] args) {
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("access_token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6Ik1ldGVyaWFsQnJhbmRzIiwiZHQiOjYzNTkzODM3NDU0NjI1MDAwMH0.5edEUzJRW1Ou3F5Jtf5upVk4BFOe9Fp0nAPp70XhWyPjjVR85Ua1bJTDgOH5yUkbQrF2weo4cnrju2O5lpPytA");

		
		try {
			byte[] result = sendGETRequest("http://183.62.232.185:8012/dgcj_dms/Handlers/GetUsers.aspx", params, "utf-8");
			String data = new String(result);
			JSONObject obj = JSONObject.fromObject(data);
			JSONArray array = obj.getJSONArray("userlist");
			
			for (int i = 0; i < array.size(); i++) {
				JSONObject ob = array.getJSONObject(i);
				if(!ob.get("username").equals("null")){
					System.out.println(ob.get("username"));
				}
				
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 判断是否是ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {  
        String header = request.getHeader("X-Requested-With");  
        if (header != null && "XMLHttpRequest".equals(header)){
            return true;  
            
        }else  {
            return false;  
        }
    } 
	
	
}
