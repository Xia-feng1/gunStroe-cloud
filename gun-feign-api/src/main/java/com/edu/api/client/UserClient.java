package com.edu.api.client;

import com.edu.api.dto.StoreDTO;
import com.edu.api.dto.UserDTO;
import com.edu.common.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-list-service")
public interface UserClient {
    @GetMapping("/user/page")
    R pageUserList(String key, Integer num, Integer size);
    @PostMapping("/login")
    R login(@RequestBody UserDTO user);
}