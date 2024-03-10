package com.edu.user;

import com.edu.user.service.impl.GunReturnListener;
import com.edu.user.service.impl.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;

@SpringBootTest
public class TestDemo {

    @Autowired
    private LocationService locationService;

    @Test
    public void testSaveUserLocation() {
        locationService.saveUserLocation("user9", 117.918261, 39.931457);
        // 添加更多用户位置信息的测试
    }
 
    @Test
    public void testFindNearbyUsers() {
        GeoResults<RedisGeoCommands.GeoLocation<String>> nearbyUsers = locationService.findNearbyUsers(116.418261, 39.921457, 1000);
        // 处理查询结果的测试逻辑
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : nearbyUsers) {
            String userId = result.getContent().getName();
            Distance distance = result.getDistance();
            double distanceValueInKm = distance.getValue() ; //
//            System.out.println(distanceValueInKm);
//            BigDecimal distanceValue = BigDecimal.valueOf(distance.getValue()).divide(BigDecimal.valueOf(1000)); // 将距离值从米转换为公里
//            String plainString = distanceValue.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();// 保留两位小数并四舍五入
            System.out.println("User " + userId + " is " + distanceValueInKm + " away.");
        }
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "delay.queue", durable = "true"),
            exchange = @Exchange(name = "delay.direct", delayed = "true"),
            key = "delay"
    ))
    public void listenDelayMessage(String msg){
        System.out.println("接收到delay.queue的延迟消息："+msg);
    }

}
