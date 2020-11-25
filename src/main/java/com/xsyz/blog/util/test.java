package com.xsyz.blog.util;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

/**
 * @author xsyz
 * @created 2020-10-31   10:13
 */
public class test {
    public static void main(String[] args) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://123.57.51.99:3307/blog");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("qyongfei2877147906@126.COM");
        System.out.println(druidDataSource.getConnection());
    }
}
