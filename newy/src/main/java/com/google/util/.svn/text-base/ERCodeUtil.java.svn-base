package com.google.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zklc.weixin.util.SystemMessage;


/**
 * 二维码Util类
 */
public class ERCodeUtil
{
	//二维码图片路径   E:\soft\nginx-1.7.12
	private static final String ERCODE_PATH = SystemMessage.NGINX_URL+"qrload";
	private static final String ERCODE_PATHNEW = SystemMessage.NGINX_URL+"qrload/final";
//	private static final String ERCODE_URL="http://211.100.34.177:9898/qrload/";
	private static final String WEB_CREATEQR_URL=SystemMessage.getString("YUMING")+"/user/userAction!webCreateQrcode.action";
	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 400;
	// LOGO宽度
	private static final int WIDTH = 90;
	// LOGO高度
	private static final int HEIGHT = 90;

	public static BufferedImage createImage(String content, String imgPath,
			boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
						: 0xFFFFFFFF);
			}
		}
		if (imgPath == null || "".equals(imgPath)) {
			return image;
		}
		// 插入图片
		ERCodeUtil.insertImage(image, imgPath, needCompress);
		return image;
	}

	/**
	 * 插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param imgPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, String imgPath,
			boolean needCompress) throws Exception {
		File file = new File(imgPath);
		Image src = null;
		if (!file.exists()) {
			URL url = new URL(imgPath);
			src = ImageIO.read(url);
		}else {
			src = ImageIO.read(new File(imgPath));
		}
		if(src != null){
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			if (needCompress) { // 压缩LOGO
				if (width > WIDTH) {
					width = WIDTH;
				}
				if (height > HEIGHT) {
					height = HEIGHT;
				}
				Image image = src.getScaledInstance(width, height,
						Image.SCALE_SMOOTH);
				BufferedImage tag = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(image, 0, 0, null); // 绘制缩小后的图
				g.dispose();
				src = image;
			}
			// 插入LOGO
			Graphics2D graph = source.createGraphics();
			int x = (QRCODE_SIZE - width) / 2;
			int y = (QRCODE_SIZE - height) / 2;
			graph.drawImage(src, x, y, width, height, null);
			Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
			graph.setStroke(new BasicStroke(3f));
			graph.draw(shape);
			graph.dispose();
		}
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存储地址
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static String encode(String content, String imgPath, String destPath,
			boolean needCompress) throws Exception {
		String returnStr=null;
		BufferedImage image = ERCodeUtil.createImage(content, imgPath,needCompress);
//		FileUtil.mkdirs(destPath);
		ImageIO.write(image, FORMAT_NAME, new File(destPath));
		returnStr=destPath;
		System.out.println("returnStr  >>>>>>>>>>>>>>>>> returnStr  "+returnStr);
		return returnStr;
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath, String destPath)
			throws Exception {
		ERCodeUtil.encode(content, imgPath, destPath, false);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String destPath,
			boolean needCompress) throws Exception {
		ERCodeUtil.encode(content, null, destPath, needCompress);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static void encode(String content, String destPath) throws Exception {
		ERCodeUtil.encode(content, null, destPath, false);
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param output
	 *            输出流
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath,
			OutputStream output, boolean needCompress) throws Exception {
		BufferedImage image = ERCodeUtil.createImage(content, imgPath,
				needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param output
	 *            输出流
	 * @throws Exception
	 */
	public static void encode(String content, OutputStream output)
			throws Exception {
		ERCodeUtil.encode(content, null, output, false);
	}

	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码图片
	 * @return
	 * @throws Exception
	 */
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
				image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	/**
	 * 解析二维码
	 * 
	 * @param path
	 *            二维码图片地址
	 * @return
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		return ERCodeUtil.decode(new File(path));
	}

	public static void main(String[] args) throws Exception {
		String text = "http://hulu.jilianji.com?loginName=maomao_998";
		ERCodeUtil.encode(text, "", "D:/ercode.png", false);
	}
	
	/**
	 * 生成二维码图片
	 * @param text 二维码信息
	 * @param logoPic logo图片路径
	 * @param codePic 生成二维码图片路径
	 * @param logoCompress 是否压缩logo质量
	 * @return
	 */
	public  static String createQRcode(String text,String logoPic,String codePic,Boolean logoCompress){
		String returnStr=null;
		String savePath=ERCODE_PATH;
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        String ymd = sdf.format(new Date());
	        savePath += "/"+ymd  ;
		 File dir = new File(savePath);
	        if(!dir.exists()){
	            dir.mkdirs();
	        }
	        //建日期目录
	        String fileName=new Date().getTime()+".png";
	        savePath+="/"+fileName;
	        returnStr=ymd+"/"+fileName;
	        try {
				ERCodeUtil.encode(text, logoPic, savePath, logoCompress);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return returnStr;
	}
	/**
	 * 生成合成后的二维码图片
	 * @param text 二维码信息
	 * @param logoPic logo图片路径
	 * @param codePic 生成二维码图片路径
	 * @param logoCompress 是否压缩logo质量
	 * @return
	 */
	public  static String createQRcodeNew(String text,String logoPic,String codePic,Boolean logoCompress){
		String returnStr=null;
		String savePath=ERCODE_PATHNEW;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += "/"+ymd  ;
		File dir = new File(savePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		//建日期目录
		String fileName=new Date().getTime()+".png";
		savePath+="/"+fileName;
		returnStr=ymd+"/"+fileName;
		try {
			ERCodeUtil.encode(text, logoPic, savePath, logoCompress);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnStr;
	}
	
	/**
	 * 远程创建二维码
	 * @param requestUrl
	 * @return
	 */
    public static String webCreateQr(String wxOpenid) {
        StringBuffer buffer = null;
        try {
            String requestUrl=WEB_CREATEQR_URL;
//            requestUrl+="loginName?="+loginName;
            System.out.println("wxOpenid====="+wxOpenid);
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setRequestProperty("Charsert", "gbk");
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("POST");
            httpUrlConn.setDoOutput(true);
            System.out.println("requestUrl1====="+requestUrl);
            StringBuffer params = new StringBuffer();
            params.append("wxOpenid").append("=").append(wxOpenid);
            byte[] bypes = params.toString().getBytes();
            httpUrlConn.getOutputStream().write(bypes);

            System.out.println("requestUrl2====="+requestUrl);
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            System.out.println("requestUrl3====="+requestUrl);
            buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            System.out.println("requestUrl4====="+requestUrl);
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            httpUrlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
