package com.wb.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wb.core.utils.pojo.VideoInfoItem;

public  class VideoInfoUtils2 {

	private  final static String  ffmpegPath = "D:/java资料/video/ffmpeg";
	private final static String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";  //从视频信息中解析时长
	private final static String regexVideo ="Video: (.*?), (.*?), (.*?)[,\\s]";			//视频格式分辨率
	private final String regexAudio ="Audio: (\\w*), (\\d*) Hz";					//音频编码和频率
	
	
	
	
	

	public static VideoInfoItem getVideoInfo(String videoPath) {
		
		List<String> commands = new java.util.ArrayList<String>();
		commands.add(ffmpegPath);
		commands.add("-i");
		commands.add(videoPath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			final Process p = builder.start();
			
			//从输入流中读取视频信息
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	        StringBuffer sb = new StringBuffer();
	        String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
	        br.close();

	        String videoStr = sb.toString();
            Pattern pattern = Pattern.compile(regexDuration);
	        Matcher m = pattern.matcher(videoStr);
	    	        
	        Pattern pattern2 = Pattern.compile(regexVideo);
	        Matcher m2 = pattern2.matcher(videoStr);
	        
	        if (m.find() && m2.find()) {
		        String fbl = m2.group(3);
		        String[] fblArray = fbl.split("x");
		        
	        	return new VideoInfoItem(Long.parseLong(m.group(1)),m.group(1), fblArray[0], fblArray[1]);
	      	       
	        }
	        	//System.out.println("视频时长："+m.group(1)+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");
	        	//System.out.println("编码格式 ："+m2.group(1)+", 视频格式 ："+m2.group(2)+", 分辨率 ："+m2.group(3));
	       
       
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//格式:"00:00:10.68"
    private static int getTimelen(String timelen){
        int min=0;
        String strs[] = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            min+=Integer.valueOf(strs[0])*60*60;//秒
        }
        if(strs[1].compareTo("0")>0){
            min+=Integer.valueOf(strs[1])*60;
        }
        if(strs[2].compareTo("0")>0){
            min+=Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }
}
