package com.babydays.interceptor;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.babydays.model.BValidate;
import com.babydays.model.Result;
import com.babydays.service.ValidateService;
import com.babydays.util.ResultUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor{

	
	@Autowired
	private ValidateService validateService;
	
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		log.info("application接口拦截器");
		//RequestWrapper myRequestWrapper = new RequestWrapper(request);
		HttpServletRequest myRequestWrapper = request;
		String token = request.getHeader("token");
		if (token != null) {
			System.out.println(token);
			BValidate eixstValidate = validateService.selectValidateByToken(token);
			if (eixstValidate != null) {
				Date date = eixstValidate.getCreatetime();
				Date nowDate = new Date();
				long day =(nowDate.getTime()-date.getTime())/(24*60*60*1000); 
				if (day<31) {
					if (myRequestWrapper != null && ServletFileUpload.isMultipartContent(myRequestWrapper)) {
						ServletRequestContext context = new ServletRequestContext(myRequestWrapper);
						long requestSize = context.contentLength();
						long maxSize = 40*1024*1024;
						if (requestSize > maxSize) {
							response.setContentType("gzip");
							response.setCharacterEncoding("utf-8");
							response.setContentType("application/json; charset=utf-8");
							response.setDateHeader("Date", new Date().getTime());
							PrintWriter writer = response.getWriter();
							Result error = ResultUtil.error(400, "文件过大");
							Object json = JSON.toJSON(error);
							writer.write(json.toString());
							return false;
						} 
					}
					return true;
				} else {
					validateService.deleteByToken(token);
					response.setContentType("gzip");
					response.setCharacterEncoding("utf-8");
					response.setContentType("application/json; charset=utf-8");
					response.setDateHeader("Date", new Date().getTime());
					PrintWriter writer = response.getWriter();
					Result error = ResultUtil.error(500, "token过期");
					Object json = JSON.toJSON(error);
					writer.write(json.toString());
					return false;
				}
			}else {
				response.setContentType("gzip");
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json; charset=utf-8");
				response.setDateHeader("Date", new Date().getTime());
				PrintWriter writer = response.getWriter();
				Result error = ResultUtil.error(500, "token过期");
				Object json = JSON.toJSON(error);
				writer.write(json.toString());
				return false;
			}
		}else {
			response.setContentType("gzip");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.setDateHeader("Date", new Date().getTime());
			PrintWriter writer = response.getWriter();
			Result error = ResultUtil.error(500, "没有权限");
			Object json = JSON.toJSON(error);
			writer.write(json.toString());
			return false;
		}
		
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
