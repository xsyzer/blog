package com.xsyz.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-14   10:36
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();

}
