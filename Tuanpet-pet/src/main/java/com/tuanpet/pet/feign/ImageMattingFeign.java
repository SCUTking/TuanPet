package com.tuanpet.pet.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("tuanpet-thirdParty")
public interface ImageMattingFeign {

    @RequestMapping(value = "thirdParty/segmentAnimal/getImageMatting",method = RequestMethod.GET)
    public String GetImageMatting(@RequestParam("petPhoto") String imageUrl);
}
