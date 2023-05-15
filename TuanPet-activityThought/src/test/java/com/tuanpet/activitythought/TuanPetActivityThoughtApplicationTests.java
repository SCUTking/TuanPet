package com.tuanpet.activitythought;

import com.alibaba.fastjson.JSON;
import com.tuanpet.activitythought.entity.ActivitythoughtEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TuanPetActivityThoughtApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
//        ActivitythoughtEntity activitythoughtEntity = new ActivitythoughtEntity();
//        activitythoughtEntity.setPhotos(new String[] { "tag1", "tag2", "tag3" });
//        String jsonString = JSON.toJSONString(activitythoughtEntity);
//        System.out.println(jsonString);
//        ActivitythoughtEntity newUser = JSON.parseObject(jsonString, ActivitythoughtEntity.class);
//        System.out.println(newUser);

        String data = "['a','b']";
        String substring = data.substring(1, data.length() - 1);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            String sub = split[i].substring(1, split[i].length() - 1);
            split[i]=sub;
        }
        System.out.println(split.length);
    }

}
