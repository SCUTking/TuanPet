package com.tuanpet.interaction.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient("tuanpet-user")
public interface UserInfoFeign {
    @RequestMapping(value = "/user/info/",method = RequestMethod.GET)
    public String getUserInfo(@RequestParam Integer userId);
}
