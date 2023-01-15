package com.example.dentalClinic.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.example.dentalClinic.apiHandler.ApiResult;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	public static final Logger LOG=Logger.getLogger(CustomAccessDeniedHandler.class);
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		ApiResult apiException=	new ApiResult();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null)
		{
			 LOG.warn("User: " + auth.getName() 
             + " attempted to access the protected URL: "
             + request.getRequestURI());
		}
		response.setStatus(403);
		apiException.setStatusCode("1");
		apiException.setStatusDescription("error");
		apiException.setResult(request.getContextPath() + "/accessDenied");
		String finalResult=mapper.writeValueAsString(apiException);
		response.setContentType("application/json");
		try(PrintWriter writer=response.getWriter())
		{
			writer.write(finalResult);
			
		}
	}

}
