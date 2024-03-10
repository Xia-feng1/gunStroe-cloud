package com.edu.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String userId;
    private Point point;

    // 省略构造函数和 getter/setter 方法
}
