package com.jx372.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static final String SAVE_PATH = "/uploads";
	private static final String PREFIX_URL = "/uploads/images/";

	public String restore(MultipartFile multipartFile) {

		String url = "";


		try{
			if(multipartFile.isEmpty()==true){
				return null;
			}

			String orginalFileName = multipartFile.getOriginalFilename();
			String extName = orginalFileName.substring(orginalFileName.lastIndexOf('.'), orginalFileName.length());
//			Long fileSize = multipartFile.getSize();
			String saveFileName = genSaveFileName(extName);

//			System.out.println("##########");
//			System.out.println("fileName = "+ orginalFileName + ", fileSzie = "+fileSize + ", extName = " + extName + ", saveFileName = " + saveFileName);
//			System.out.println("##########");

			wrtieFile(multipartFile, saveFileName);
			
			url = PREFIX_URL + saveFileName;
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
		return url;
	}

	private void wrtieFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		byte[] fileDate = multipartFile.getBytes();
		
		FileOutputStream fos = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
		fos.write(fileDate);
		fos.close();
	}

	private String genSaveFileName(String extName) {
		// TODO Auto-generated method stub
		String fileName = "";

		Calendar calendar = Calendar.getInstance();

		fileName = fileName + calendar.get(Calendar.YEAR);
		fileName = fileName + calendar.get(Calendar.MONTH);
		fileName = fileName + calendar.get(Calendar.DATE);
		fileName = fileName + calendar.get(Calendar.HOUR);
		fileName = fileName + calendar.get(Calendar.MINUTE);
		fileName = fileName + calendar.get(Calendar.SECOND);
		fileName = fileName + calendar.get(Calendar.MILLISECOND);
		fileName = fileName + extName;

		return fileName;
	}

}
