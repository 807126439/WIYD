package com.spr.core.utils.img;
//package com.wb.core.utils.img;
//
//import java.awt.Image;
//import java.awt.Rectangle;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.RandomAccessFile;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//import java.util.regex.Pattern;
//
//import javax.swing.SwingUtilities;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
//import org.artofsolving.jodconverter.OfficeDocumentConverter;
//import org.artofsolving.jodconverter.document.DefaultDocumentFormatRegistry;
//import org.artofsolving.jodconverter.document.DocumentFormat;
//import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
//import org.artofsolving.jodconverter.office.OfficeManager;
//
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import com.sun.pdfview.PDFFile;
//import com.sun.pdfview.PDFPage;
//import com.wb.core.common.exception.MyException;
//import com.wb.core.common.utils.Assert;
//import com.wb.core.utils.FileOperation;
// 
//public class PdfToJpgUtilsNew {  
//	
//	private static Logger logger = Logger.getLogger(PdfToJpgUtilsNew.class);
//	private final static long MAXSIZE = 80000000; //最大限制转化大小8MB
//	
//	
//	public static String getOfficeHome() {  
//	    String osName = System.getProperty("os.name");  
//	    if (Pattern.matches("Linux.*", osName)) {  
//	        return "/opt/openoffice.org3";  
//	    } else if (Pattern.matches("Windows.*", osName)) {  
//	        return "C:/Program Files (x86)/OpenOffice 4";  
//	    } else if (Pattern.matches("Mac.*", osName)) {  
//	        return "/Application/OpenOffice.org.app/Contents";  
//	    }  
//	    return null;  
//	}  
//	
//	
//	public static File convertFileToPdf(File sourceFile,String pdfTempPath) throws IOException{
//		 if(sourceFile.length() > MAXSIZE){
//	    	 throw new MyException("the size of file  out of range");
//	     }
//		 
//		String fileType = FileOperation.getFileType(sourceFile.getName());
//		 
//		 //临时pdf文件
//		File pdfFile = new File(pdfTempPath);
//		if(!pdfFile.exists()){
//			pdfFile.createNewFile();
//		}
//	 
//		 	     
//		if(!fileType.equals("pdf")){
//			    DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
//			
//			    String officeHome = getOfficeHome();  
//			    config.setOfficeHome(officeHome);  
//			    config.setPortNumber(8010);
//			  
//			    OfficeManager officeManager = config.buildOfficeManager();  
//			    officeManager.start();  
//			  
//			    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);  
//			    			  
//			    converter.convert(sourceFile, pdfFile);  
//			      			  
//			    officeManager.stop();  			
//				 		 
//	         
//		        System.out.println("转化pdf成功....");
//		        logger.info("转化pdf成功....");
//		 
//		 }else{	
//			 //复制pdf文件到新的文件
//			 FileUtils.copyFile(sourceFile, pdfFile);
//			
//		 }
//		
//		
//		return pdfFile;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//    public static void setup(String sourcePath,String savePath) throws IOException {   	
//    	    	
//        // load a pdf from a byte buffer  
//        File file = new File(sourcePath); 
//        Assert.isTrue(file.isFile(),"is invalid file!");
//        
//        RandomAccessFile raf = new RandomAccessFile(file, "r");  
//        FileChannel channel = raf.getChannel();  
//        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel .size());  
//        PDFFile pdffile = new PDFFile(buf);  
// 
//        System.out.println("页数： " + pdffile.getNumPages());  
// 
//       
//        for (int i = 1; i <= pdffile.getNumPages(); i++) {  
//            // draw the first page to an image  
//            PDFPage page = pdffile.getPage(i);  
// 
//            // get the width and height for the doc at the default zoom  
//            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()  
//                    .getWidth(), (int) page.getBBox().getHeight());  
// 
//            // generate the image  
//            Image img = page.getImage(rect.width, rect.height, // width &  
//                                                                // height  
//                    rect, // clip rect  
//                    null, // null for the ImageObserver  
//                    true, // fill background with white  
//                    true // block until drawing is done  
//                    );  
// 
//            BufferedImage tag = new BufferedImage(rect.width, rect.height,  BufferedImage.TYPE_INT_RGB);  
//            tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,  null); 
//           
//                    
//            FileOutputStream out = new FileOutputStream(savePath); // 输出到文件流
//           
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
//            encoder.encode(tag); // JPEG编码  
// 
//            out.close();  
//            
//          
//        }  
// 
//        // show the image in a frame  
//        // JFrame frame = new JFrame("PDF Test");  
//        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//        // frame.add(new JLabel(new ImageIcon(img)));  
//        // frame.pack();  
//        // frame.setVisible(true);  
//       
//    }  
// 
//    public static void main(final String[] args) {  
//        SwingUtilities.invokeLater(new Runnable() {  
//            public void run() {  
//                try {  
//                    PdfToJpgUtilsNew.setup("D:/pdf-test/FreeMarker_Manual_zh_CN.pdf","D:/pdf-test");  
//                } catch (IOException ex) {  
//                    ex.printStackTrace();  
//                }  
//            }  
//        });  
//    }  
// 
//} 
