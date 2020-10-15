package com.xsyz.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xsyz
 * @created 2020-10-14   10:39
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();
}
