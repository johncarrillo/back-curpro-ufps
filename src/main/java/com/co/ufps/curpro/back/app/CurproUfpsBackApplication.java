package com.co.ufps.curpro.back.app;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.co.ufps.curpro.back.app.models.service.IFileService;

@SpringBootApplication
@Component
public class CurproUfpsBackApplication implements CommandLineRunner{

	@Resource
	private IFileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(CurproUfpsBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileService.init();
	}

}
