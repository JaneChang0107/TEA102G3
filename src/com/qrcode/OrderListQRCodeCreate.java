package com.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class OrderListQRCodeCreate {
	
	public String creater(String host,String oid) {
		String action = "http://" + host + "/TEA102G3/OrderArrive?oid=" + oid;
		int width = 300;
		int height = 300;
		String format = "jpg";
		String base64 = "";
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			BitMatrix matrix = new MultiFormatWriter().encode(action, BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToStream(matrix, format, os);
			
			byte[] b = os.toByteArray();
			
			base64 = Base64.getEncoder().encodeToString(b);
			
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return base64;
	}
	
}
