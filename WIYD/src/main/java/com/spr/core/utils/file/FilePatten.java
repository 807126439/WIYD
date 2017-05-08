package com.spr.core.utils.file;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class FilePatten {
   
	public final static String PINTUREPATTEN = "images/fileIcon/picture_icon.png";
	public final static String ABLUM_PATTEN = "images/fileIcon/image_photo.png";
    public final static Map<String, String> FILE_BIG_PATTEN_MAP = new HashMap<String, String>();  
	private static List<String> pictureFormats = new ArrayList<String>();
	private static List<String> videoFormats = new ArrayList<String>();
	private static List<String> audioFormats = new ArrayList<String>();
	private static List<String> officeFormats = new ArrayList<String>();
   
    private FilePatten(){}  
    static{  
    	//初始化文件类型信息  
    	getPattern();
    	//初始化图片格式
    	initPictureFormats();
    	//初始化视频格式
    	initVideoFormats();
    	//初始化音频格式
    	initAudioFormats();
    	//初始化办公文件类型
    	initOfficeFormats();
    }  
	
    
    private static void initPictureFormats(){
    	pictureFormats.add("jpeg");
    	pictureFormats.add("jpg");
    	pictureFormats.add("png");
    	pictureFormats.add("bmp");   	
    	pictureFormats.add("gif");   	
    	pictureFormats.add("pcx");
    	pictureFormats.add("tiff");   	
    	pictureFormats.add("tga");
    	pictureFormats.add("exif");
    	pictureFormats.add("fpx");
    	pictureFormats.add("svg");
    	pictureFormats.add("psd");
    	pictureFormats.add("cdr");
    	pictureFormats.add("pcd");
    	pictureFormats.add("dxf");
    	pictureFormats.add("ufo");
    	pictureFormats.add("eps");
    	pictureFormats.add("eps");
    	pictureFormats.add("ai");   
    	pictureFormats.add("hdri");
    	
    	
    	
    }
   
    
    private static void initVideoFormats(){
    	videoFormats.add("mp4");
    	videoFormats.add("mkv");
    	videoFormats.add("flv");
    	videoFormats.add("rm");
      	videoFormats.add("avi");
      	videoFormats.add("wmv");
      	videoFormats.add("rmvb");
    	videoFormats.add("mpeg");
    	videoFormats.add("mov");
    	videoFormats.add("asf");  
    	videoFormats.add("asx");
    	videoFormats.add("navi");
    	videoFormats.add("3gp"); 	
    	videoFormats.add("f4v");  
    	videoFormats.add("wmv9");
    	videoFormats.add("webm");
    	
    }
    
    private static void initAudioFormats(){
    	
    	audioFormats.add("mp3");
    	audioFormats.add("m4a");
    	audioFormats.add("oga");
    	audioFormats.add("wma");
    	audioFormats.add("wav");
    	audioFormats.add("asf");
    	audioFormats.add("aac");
    	audioFormats.add("vqf");
    	audioFormats.add("flac");
    	audioFormats.add("ape");
    	audioFormats.add("mid");
    	audioFormats.add("ogg");
    	audioFormats.add("au");
    }
    
    
    private static void initOfficeFormats(){
    	officeFormats.add("doc");
    	officeFormats.add("docx");
    	officeFormats.add("ppt");
    	officeFormats.add("pptx");
    	officeFormats.add("xls");
    	officeFormats.add("xlsx");
    	officeFormats.add("txt");
    	officeFormats.add("pdf");
  
    	 	
    }
    
    
	
    private static void getPattern() {
    	FILE_BIG_PATTEN_MAP.put("html","images/fileIcon/Msqq.Com_html.png" );
    	FILE_BIG_PATTEN_MAP.put("mp3","images/fileIcon/Msqq.Com_mp3.png" );
    	FILE_BIG_PATTEN_MAP.put("zip","images/fileIcon/Msqq.Com_zip.png" );
    	
    	
    	
    	/*
    	FILE_BIG_PATTEN_MAP.put("rar","images/fileIcon/Msqq.Com_rar1.png" ); 
    	FILE_BIG_PATTEN_MAP.put("doc","images/fileIcon/Msqq.Com_Officeword.png" );
    	FILE_BIG_PATTEN_MAP.put("xls","images/fileIcon/Msqq.Com_xlsx.png" );
    	FILE_BIG_PATTEN_MAP.put("xlsx","images/fileIcon/Msqq.Com_xlsx.png" );
    	FILE_BIG_PATTEN_MAP.put("txt","images/fileIcon/Msqq.Com_txt.png" );
    	FILE_BIG_PATTEN_MAP.put("pdf","images/fileIcon/Msqq.Com_pdf.png" );
    	FILE_BIG_PATTEN_MAP.put("ppt","images/fileIcon/Msqq.Com_PPT.png" );*/
    	FILE_BIG_PATTEN_MAP.put("rar","images/fileIcon/fileicon-large-rar.png" ); 
    	FILE_BIG_PATTEN_MAP.put("doc","images/fileIcon/fileicon-large-doc.png" );
    	FILE_BIG_PATTEN_MAP.put("xls","images/fileIcon/fileicon-large-xls.png" );
    	FILE_BIG_PATTEN_MAP.put("xlsx","images/fileIcon/fileicon-large-xls.png" );
    	FILE_BIG_PATTEN_MAP.put("txt","images/fileIcon/fileicon-large-txt.png" );
     	FILE_BIG_PATTEN_MAP.put("pdf","images/fileIcon/fileicon-large-pdf.png" );
     	FILE_BIG_PATTEN_MAP.put("ppt","images/fileIcon/fileicon-large-ppt.png" );
    	
    	FILE_BIG_PATTEN_MAP.put("sql","images/fileIcon/Msqq.Com_txt.png" );
    	FILE_BIG_PATTEN_MAP.put("rmvb","images/fileIcon/Msqq.Com_rmvb_icon.png" );  	
    	FILE_BIG_PATTEN_MAP.put("avi","images/fileIcon/Msqq.Com_avi.png" );
    	FILE_BIG_PATTEN_MAP.put("wav","images/fileIcon/Msqq.Com_wav.png" );  
    	FILE_BIG_PATTEN_MAP.put("exe","images/fileIcon/Msqq.Com_exe.png" );   	
    	FILE_BIG_PATTEN_MAP.put("accdb","images/fileIcon/Msqq.Com_accdb.png" );
    	FILE_BIG_PATTEN_MAP.put("css","images/fileIcon/Msqq.Com_css.png" );
    	FILE_BIG_PATTEN_MAP.put("docx","images/fileIcon/Msqq.Com_docx.png" );
    	FILE_BIG_PATTEN_MAP.put("eml","images/fileIcon/Msqq.Com_eml.png" );
    	FILE_BIG_PATTEN_MAP.put("fla","images/fileIcon/Msqq.Com_fla.png" );
    	FILE_BIG_PATTEN_MAP.put("ind","images/fileIcon/Msqq.Com_ind.png" );
    	FILE_BIG_PATTEN_MAP.put("ini","images/fileIcon/Msqq.Com_ini.png" );
    	FILE_BIG_PATTEN_MAP.put("midi","images/fileIcon/Msqq.Com_midi.png" );
    	FILE_BIG_PATTEN_MAP.put("mov","images/fileIcon/Msqq.Com_mov.png" );
    	FILE_BIG_PATTEN_MAP.put("mpeg1","images/fileIcon/Msqq.Com_mpeg.png" );
    	FILE_BIG_PATTEN_MAP.put("mpeg2","images/fileIcon/Msqq.Com_mpeg.png" );
    	FILE_BIG_PATTEN_MAP.put("pptx","images/fileIcon/Msqq.Com_pptx.png" );
    	FILE_BIG_PATTEN_MAP.put("proj","images/fileIcon/Msqq.Com_proj.png" );
    	FILE_BIG_PATTEN_MAP.put("psd","images/fileIcon/Msqq.Com_psd.png" );
    	FILE_BIG_PATTEN_MAP.put("pst","images/fileIcon/Msqq.Com_pst.png" );
    	FILE_BIG_PATTEN_MAP.put("pub","images/fileIcon/Msqq.Com_pub.png" );
    	FILE_BIG_PATTEN_MAP.put("set","images/fileIcon/Msqq.Com_settings.png" );
    	FILE_BIG_PATTEN_MAP.put("url","images/fileIcon/Msqq.Com_url.png" );
    	FILE_BIG_PATTEN_MAP.put("vsd","images/fileIcon/Msqq.Com_vsd.png" );
    	FILE_BIG_PATTEN_MAP.put("wma","images/fileIcon/Msqq.Com_wma.png" );
    	FILE_BIG_PATTEN_MAP.put("wmv","images/fileIcon/Msqq.Com_wmv.png" );
    	FILE_BIG_PATTEN_MAP.put("dll","images/fileIcon/Msqq.Com_dll.png" );
    	FILE_BIG_PATTEN_MAP.put("mkv","images/fileIcon/Msqq.Com_mkv.png" );
    	FILE_BIG_PATTEN_MAP.put("flv","images/fileIcon/Msqq.Com_flv.png" );
    	FILE_BIG_PATTEN_MAP.put("swf","images/fileIcon/Msqq.Com_swf.png" );
    	FILE_BIG_PATTEN_MAP.put("iso","images/fileIcon/Msqq.Com_iso.png" );
    	FILE_BIG_PATTEN_MAP.put("mp4","images/fileIcon/Msqq.Com_mp4.png" );
    	
    	FILE_BIG_PATTEN_MAP.put("asf","images/fileIcon/Msqq.Com_asf" );
    	FILE_BIG_PATTEN_MAP.put("ts","images/fileIcon/Msqq.Com_ts.png" );
    	FILE_BIG_PATTEN_MAP.put("3gp","images/fileIcon/Msqq.Com_3gp.png" );
    	FILE_BIG_PATTEN_MAP.put("m4v","images/fileIcon/Msqq.Com_m4v.png" );
    	FILE_BIG_PATTEN_MAP.put("f4v","images/fileIcon/Msqq.Com_f4v.png" );
    	FILE_BIG_PATTEN_MAP.put("rm","images/fileIcon/Msqq.Com_rm.png" );
    	FILE_BIG_PATTEN_MAP.put("ram","images/fileIcon/Msqq.Com_ram.png" );
    	FILE_BIG_PATTEN_MAP.put("vqf","images/fileIcon/Msqq.Com_vqf.png" );
    	FILE_BIG_PATTEN_MAP.put("aiff","images/fileIcon/Msqq.Com_aiff.png" );
    	FILE_BIG_PATTEN_MAP.put("aif","images/fileIcon/Msqq.Com_aiff.png" );
    }
	
    
    
  
    
    
    public final static String getPatternUrl(String temp){
    	Iterator<Entry<String, String>> entryiterator = FILE_BIG_PATTEN_MAP.entrySet().iterator(); 
    	  while (entryiterator.hasNext()) {  
              Entry<String,String> entry =  entryiterator.next();
              if(temp.equals(entry.getKey())){
            	  return entry.getValue();
              }
    	  }
    	return "images/fileIcon/Msqq.Com_common.png";
    }
    
    
    public static boolean isPicture(String type){
    	
    	return pictureFormats.contains(type.toLowerCase());
    }
    
    public static boolean isVideo(String type){
    	
    	return videoFormats.contains(type.toLowerCase());
    }
    
    public static boolean isAudio(String type){
    	
    	return audioFormats.contains(type.toLowerCase());
    }
    
    
    
   public static boolean isOfficeFile(String type){
    	
    	return officeFormats.contains(type.toLowerCase());
    }
}
