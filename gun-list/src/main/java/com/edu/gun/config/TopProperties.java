package com.edu.gun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "gun.top-list")
public class TopProperties {
    // 热度榜最大数量
    private Integer maxTop;
}
