package com.xsyz.blog.handler;
import org.slf4j.*;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
/**
 * 异常处理类
 *
 * @author xsyz
 * @created 2020-10-13   19:36
 */
@ControllerAdvice
public class ExceptionHandler {
    private final  Logger logger= (Logger) LoggerFactory.getLogger(this.getClass());
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request ,Exception e) throws Exception{
        logger.error("Requst URL : {}，Exception : {}",request.getRequestURL(),e);
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }

}
