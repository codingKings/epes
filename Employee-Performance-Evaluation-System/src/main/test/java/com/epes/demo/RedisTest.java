package com.epes.demo;

import com.epes.demo.entity.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Date: 2018/1/19
 * Time: 14:20
 *
 * @Author lixingjie
 * @Modifice
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void test1() throws Exception {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)-1);
        Date date=curr.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);

        System.out.println("格式化后的日期：" + dateNowStr);
    }

    @Test
    public void test2() throws Exception {
        String str1="2015-02-08 20:20:20";
        String str2="2015-01-08 10:10:10";
        int res=str1.compareTo(str2);
        if(res>0)
            System.out.println("str1>str2");
        else if(res==0)
            System.out.println("str1=str2");
        else
            System.out.println("str1<str2");
    }


 /*   @Test
    public void testObj() throws Exception {
        UserInfo user=new UserInfo();
        user.setUname("张思");
        user.setSex("女");
        user.setAddress("江北");
        user.setAge(23);
        ValueOperations<String, UserInfo> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }*/
}

