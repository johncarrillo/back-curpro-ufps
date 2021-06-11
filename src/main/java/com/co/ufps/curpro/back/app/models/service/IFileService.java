package com.co.ufps.curpro.back.app.models.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.co.ufps.curpro.back.app.dto.ArchivoDto;

public interface IFileService {

	public void init();

	public ArchivoDto saveImage(MultipartFile file);

	public String saveFile(MultipartFile file);

	public Resource loadImage(String nameFile);

	public Resource loadFile(String nameFile);

	public void deleteImage(String nameFile);

	public void deleteFile(String nameFile);
}
