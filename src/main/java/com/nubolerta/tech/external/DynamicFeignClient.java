package com.nubolerta.tech.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "dynamicClient")
public interface DynamicFeignClient {

    @PostMapping("/post-endpoint")
    Object postToDynamicUrl(@RequestBody Object request);
}
