package com.xsyz.blog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author xsyz
 * @created 2020-11-23   17:33
 */
public class Test {
    public static void main(String[] args) {
        List<String> y = new ArrayList<>();
        y.add("2011");
        y.add("2011");
        y.add("2012");
        HashSet<String> year = new HashSet<>(y);
        //y.stream().forEach(e->year.add(e));
        System.out.println(year);
    }
}
