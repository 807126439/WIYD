package com.wb.core.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class FilePatten {
   private final static String FILE_PREFIX_PATH = "plug-in/common/images/";
	
	public final static String PINTUREPATTEN = FILE_PREFIX_PATH+"fileIcon/picture_icon.png";
	public final static String ABLUM_PATTEN = FILE_PREFIX_PATH+"fileIcon/image_photo.png";
	
	
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
    	FILE_BIG_PATTEN_MAP.put("html",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_html.png" );
    	FILE_BIG_PATTEN_MAP.put("mp3",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_mp3.png" );
    	FILE_BIG_PATTEN_MAP.put("zip",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_zip.png" );
    	FILE_BIG_PATTEN_MAP.put("rar",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_rar1.png" );
    	FILE_BIG_PATTEN_MAP.put("doc",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_Officeword.png" );
    	FILE_BIG_PATTEN_MAP.put("xls",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_xlsx.png" );
    	FILE_BIG_PATTEN_MAP.put("xlsx",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_xlsx.png" );
    	FILE_BIG_PATTEN_MAP.put("txt",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_txt.png" );
    	FILE_BIG_PATTEN_MAP.put("sql",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_txt.png" );
    	FILE_BIG_PATTEN_MAP.put("rmvb",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_rmvb_icon.png" );
    	FILE_BIG_PATTEN_MAP.put("ppt",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_PPT.png" );
    	FILE_BIG_PATTEN_MAP.put("avi",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_avi.png" );
    	FILE_BIG_PATTEN_MAP.put("wav",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_wav.png" );
    	FILE_BIG_PATTEN_MAP.put("pdf",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_pdf.png" );
    	FILE_BIG_PATTEN_MAP.put("exe",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_exe.png" );
    	
    	FILE_BIG_PATTEN_MAP.put("accdb",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_accdb.png" );
    	FILE_BIG_PATTEN_MAP.put("css",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_css.png" );
    	FILE_BIG_PATTEN_MAP.put("docx",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_docx.png" );
    	FILE_BIG_PATTEN_MAP.put("eml",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_eml.png" );
    	FILE_BIG_PATTEN_MAP.put("fla",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_fla.png" );
    	FILE_BIG_PATTEN_MAP.put("ind",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_ind.png" );
    	FILE_BIG_PATTEN_MAP.put("ini",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_ini.png" );
    	FILE_BIG_PATTEN_MAP.put("midi",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_midi.png" );
    	FILE_BIG_PATTEN_MAP.put("mov",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_mov.png" );
    	FILE_BIG_PATTEN_MAP.put("mpeg1",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_mpeg.png" );
    	FILE_BIG_PATTEN_MAP.put("mpeg2",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_mpeg.png" );
    	FILE_BIG_PATTEN_MAP.put("pptx",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_pptx.png" );
    	FILE_BIG_PATTEN_MAP.put("proj",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_proj.png" );
    	FILE_BIG_PATTEN_MAP.put("psd",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_psd.png" );
    	FILE_BIG_PATTEN_MAP.put("pst",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_pst.png" );
    	FILE_BIG_PATTEN_MAP.put("pub",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_pub.png" );
    	FILE_BIG_PATTEN_MAP.put("set",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_settings.png" );
    	FILE_BIG_PATTEN_MAP.put("url",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_url.png" );
    	FILE_BIG_PATTEN_MAP.put("vsd",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_vsd.png" );
    	FILE_BIG_PATTEN_MAP.put("wma",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_wma.png" );
    	FILE_BIG_PATTEN_MAP.put("wmv",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_wmv.png" );
    	FILE_BIG_PATTEN_MAP.put("dll",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_dll.png" );
    	FILE_BIG_PATTEN_MAP.put("mkv",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_mkv.png" );
    	FILE_BIG_PATTEN_MAP.put("flv",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_flv.png" );
    	FILE_BIG_PATTEN_MAP.put("swf",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_swf.png" );
    	FILE_BIG_PATTEN_MAP.put("iso",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_iso.png" );
    	FILE_BIG_PATTEN_MAP.put("mp4",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_mp4.png" );
    	
    	FILE_BIG_PATTEN_MAP.put("asf",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_asf" );
    	FILE_BIG_PATTEN_MAP.put("ts",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_ts.png" );
    	FILE_BIG_PATTEN_MAP.put("3gp",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_3gp.png" );
    	FILE_BIG_PATTEN_MAP.put("m4v",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_m4v.png" );
    	FILE_BIG_PATTEN_MAP.put("f4v",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_f4v.png" );
    	FILE_BIG_PATTEN_MAP.put("rm",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_rm.png" );
    	FILE_BIG_PATTEN_MAP.put("ram",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_ram.png" );
    	FILE_BIG_PATTEN_MAP.put("vqf",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_vqf.png" );
    	FILE_BIG_PATTEN_MAP.put("aiff",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_aiff.png" );
    	FILE_BIG_PATTEN_MAP.put("aif",FILE_PREFIX_PATH+"fileIcon/Msqq.Com_aiff.png" );
    }
	
    
    
  
    
    
    public final static String getPatternUrl(String temp){
    	Iterator<Entry<String, String>> entryiterator = FILE_BIG_PATTEN_MAP.entrySet().iterator(); 
    	while (entryiterator.hasNext()) {  
          Entry<String,String> entry =  entryiterator.next();
          if(temp.equals(entry.getKey())){
        	  return entry.getValue();
          }
    	}
    	  
    	return FILE_PREFIX_PATH+"fileIcon/Msqq.Com_common.png";
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
