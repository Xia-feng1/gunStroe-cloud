package com.edu.api.client;

import com.edu.api.dto.StoreDTO;
import com.edu.common.domain.R;
import org.bouncycastle.util.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("store-list-service")
public interface StoreClient {
    @GetMapping("/store/{id}")
    StoreDTO getOneStore(@PathVariable Integer id);
}