package com.herokuapp.livraria.logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class AddImg implements Logica {

	private static final String SAVE_DIR = "/home/pompeu/workspace/livraria/WebContent/imgs";

	public AddImg() {

	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
	
		String savePath = SAVE_DIR+ File.separator ;


		Part part = req.getPart("file");
		System.out.println(part);
		String fileName = extractFileName(part);
		
		InputStream is = part.getInputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		byte[] buffer = new byte[is.available()];
		is.read(buffer);
	 
	    File targetFile = new File(savePath+File.separator+fileName);
	    OutputStream outStream = new FileOutputStream(targetFile);
	    outStream.write(buffer);
		/*
		 * String fileName = extractFileName(part); part.write(savePath +
		 * File.separator + fileName);
		 */
		/*
		 * System.out.println(part.getName()); for (Part part : req.getParts())
		 * { fileName = extractFileName(part); part.write(savePath +
		 * File.separator + fileName); }
		 */
		req.setAttribute("result", "Upload has been done successfully!");

		return "/";
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}
