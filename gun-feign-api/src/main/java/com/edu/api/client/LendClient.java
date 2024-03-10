package com.edu.api.client;

import com.edu.api.dto.LendDTO;
import com.edu.api.dto.UserDTO;
import com.edu.common.domain.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("lend-list-service")
public interface LendClient {
    @PostMapping("/lend/insert")
    R insertLend(@RequestBody LendDTO lend);

}