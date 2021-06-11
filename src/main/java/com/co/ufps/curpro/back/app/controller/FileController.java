package com.co.ufps.curpro.back.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.co.ufps.curpro.back.app.dto.ArchivoDto;
import com.co.ufps.curpro.back.app.models.service.IFileService;


@RestController
@CrossOrigin("*")
public class FileController {

	@Autowired
	private IFileService fileService;

	@PostMapping("/uploadImage")
	public ArchivoDto uploadFile(@RequestParam("files") MultipartFile file) {
		System.out.println(file);
		return fileService.saveImage(file);
	}

	@GetMapping("fileImage/{filename}")
	public ResponseEntity<Resource> getFile(@PathVariable(value="filename") String fileName) {
		Resource file = fileService.loadImage(fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getFilename() + "\"")
				.body(file);
	}
}
