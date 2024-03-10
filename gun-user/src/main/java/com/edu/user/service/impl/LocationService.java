package com.edu.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveUserLocation(String userId, double longitude, double latitude) {
        Point point = new Point(longitude, latitude);
        redisTemplate.opsForGeo().add("locations", new Point(longitude, latitude), userId);
    }

    public GeoResults<RedisGeoCommands.GeoLocation<String>> findNearbyUsers(double longitude, double latitude, double distanceInKm) {
        Circle within = new Circle(new Point(longitude, latitude), new Distance(distanceInKm, Metrics.KILOMETERS));
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().sortAscending();
        return redisTemplate.opsForGeo().radius("locations", within, args);
    }
}
