package com.co.ufps.curpro.back.app.models.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.co.ufps.curpro.back.app.constante.ConstantesFile;
import com.co.ufps.curpro.back.app.dto.ArchivoDto;

@Service
public class FileServiceImpl implements IFileService{

	public static final Path root = Paths.get("uploadsImages");
	public static final Path rootFile = Paths.get("uploadsFile");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
			Files.createDirectory(rootFile);
		} catch (Exception e) {
			// throw new RuntimeException("No se puede iniciar el storage");
			System.out.println("las carpetas de los archivos ya esta creada");
		}
	}

	@Override
	public void deleteFile(String nameFile) {
		try {
			Boolean delete = Files.deleteIfExists(rootFile.resolve(nameFile));
		} catch (Exception e) {
			throw new RuntimeException("Error en el borrado");
		}
	}

	@Override
	public ArchivoDto saveImage(MultipartFile file) {
		String nombreFile = System.currentTimeMillis() + " - " +file.getOriginalFilename();
		try {
			Files.copy(file.getInputStream(), root.resolve(nombreFile));
		} catch (Exception e) {
			throw new RuntimeException("No se puede guardar el archivo");
		}
		return ArchivoDto.builder().nombre(nombreFile).build();
	}

	@Override
	public String saveFile(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), rootFile.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("No se puede guardar el archivo");
		}
		return null;
	}

	@Override
	public Resource loadImage(String nameFile) {
		try {
			Path file = root.resolve(nameFile);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public Resource loadFile(String nameFile) {
		try {
			Path file = rootFile.resolve(nameFile);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteImage(String nameFile) {
		try {
			Boolean delete = Files.deleteIfExists(root.resolve(nameFile));
		} catch (Exception e) {
			throw new RuntimeException("Error en el borrado");
		}
	}
}
