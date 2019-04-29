package com.xfy.apiall.core;

import com.xfy.apiall.core.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/*@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest*/
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WxConfigTest {
    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void test(){

        redisUtil.set("love","zhangzq",60*60*30, TimeUnit.SECONDS);
        redisUtil.set("licm","zhangzq",60*60*30, TimeUnit.SECONDS);

       /* UserInfo userInfo = new UserInfo();
        userInfo.setCity("changsha");
        userInfo.setCountry("china");

        redisUtil.set("user-1",userInfo,60*60*30,TimeUnit.SECONDS);

        //UserInfo user = redisUtil.get("user-1");java.base/java.lang.String cannot be cast to com.xfy.apiall.core.dao.UserInfo
        UserInfo user = redisUtil.get("user-1");
        System.out.println(user.getCity());*/

        String zhanzgq = redisUtil.get("licm");
        System.out.println( zhanzgq );

    }


}
