package com.herokuapp.livraria.models;

import java.io.Serializable;
import java.util.Base64;

public class ImagemBase64 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String imagem = "data:image/jpeg;base64,";

	public ImagemBase64(byte[] buffer) {
		imagem64(Base64.getEncoder().encodeToString(buffer));
	}

	public ImagemBase64(String buffer) {
		imagem64(buffer);
	}

	public String imagem64(String imagem) {
		this.imagem += imagem;
		return this.imagem;
	}

	@Override
	public String toString() {
		return imagem;
	}

}
