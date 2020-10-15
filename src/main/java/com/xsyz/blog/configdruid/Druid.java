package com.xsyz.blog.configdruid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xsyz
 * @created 2020-10-13   21:19
 */
@Configuration
public class Druid {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource getdruid(){
        return new DruidDataSource();
    }
    @Bean
    public ServletRegistrationBean staViewServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initp=new HashMap<>();
        initp.put("loginUsername","admin");
        initp.put("loginPassword","123");
        initp.put("allow","localhost");
        initp.put("deny","192.168.1.1");
        bean.setInitParameters(initp);
        return bean;
    }
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initp=new HashMap<>();
        initp.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initp);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
