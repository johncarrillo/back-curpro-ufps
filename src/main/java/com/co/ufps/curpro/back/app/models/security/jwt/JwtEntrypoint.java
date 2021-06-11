package com.co.ufps.curpro.back.app.models.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class JwtEntrypoint implements AuthenticationEntryPoint{
	
	private final static Logger logger = LoggerFactory.getLogger(JwtEntrypoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("fail en el metodo commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales Erroneas");
		
	}
	
}
