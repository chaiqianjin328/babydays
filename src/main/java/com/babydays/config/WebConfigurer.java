package com.babydays.config;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import com.babydays.service.AdminService;
import com.sun.tools.javac.util.Convert;
import lombok.val;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.babydays.interceptor.LoginCheckInterceptor;
import com.babydays.interceptor.WebLoginCheckInterceptor;
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

	@Autowired
	private LoginCheckInterceptor loginCheckInterceptor;
	
	@Autowired
	private WebLoginCheckInterceptor webLoginCheckInterceptor;
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		ArrayList<String> appIncloud = new ArrayList<String>();
		appIncloud.add("/system/**");
		appIncloud.add("/notices/**");
		appIncloud.add("/archives/**");
		appIncloud.add("/upload/**");
		appIncloud.add("/teachprogram/**");
		appIncloud.add("/signin/**");
		appIncloud.add("/growth/**");
		appIncloud.add("/leave/**");
		appIncloud.add("/picvid/**");
		ArrayList<String> notAppIncloud = new ArrayList<String>();
		notAppIncloud.add("/**/*login*");
		registry.addInterceptor(loginCheckInterceptor).addPathPatterns(appIncloud).excludePathPatterns(notAppIncloud);
		ArrayList<String> webIncloud = new ArrayList<String>();
		webIncloud.add("/web/**");
		ArrayList<String> notWebIncloud = new ArrayList<String>();
		notWebIncloud.add("/**/*.css");
		notWebIncloud.add("/**/*.js");
		notWebIncloud.add("/**/*.png");
		notWebIncloud.add("/**/*.jpg");
		notWebIncloud.add("/**/*.jpeg");
		notWebIncloud.add("/**/*.ico");
		notWebIncloud.add("/**/*login*");
		notWebIncloud.add("/**/*toLogin*");
		notWebIncloud.add("/**/*code*");
		notWebIncloud.add("/**/*validateCode*");
		registry.addInterceptor(webLoginCheckInterceptor).addPathPatterns(webIncloud).excludePathPatterns(notWebIncloud);
	}
	
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		 //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //5.将convert添加到converters当中.
        converters.add(fastJsonHttpMessageConverter);
	}





}
