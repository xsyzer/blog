package com.xsyz.blog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;

/**
 * @author xsyz
 * @created 2020-10-13   21:29
 */
@Aspect
@Component
public class LogAspect {

     Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.xsyz.blog.web.*.*(..))")
    public void log(){
    }
    @Before("log()")
    public void doBefore(JoinPoint joinpoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName();
        Object [] args=joinpoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        logger.info("Request : {}", requestLog);
    }
    @After("log()")
    public void doAfter(){
        logger.info("doAfter--------------------");
    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){

        logger.info("result : {}",result);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object args[];

    }
}
