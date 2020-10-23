package com.xsyz.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author xsyz
 * @created 2020-10-21   10:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BT {
    private Long blogsId;
    private Long tagsId;
}
