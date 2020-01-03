package com.hbgc.springbootdemo.service;

import com.hbgc.springbootdemo.domain.Users;
import com.hbgc.springbootdemo.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUsers {
    @Resource
    private UsersService usersService;
    @Test
  public void testQuery (){
       Users users=usersService.queryUsersByMobile("17336401051");
       if (users!=null){
           System.out.println("该用户已存在");
       }else {
           System.out.println("此手机号可以注册");
       }

    }


    @Test
    public  void testUpdate(){

        Users users=new Users();
        users.setUid(1);
        users.setUsername("zhangsan");
        users.setPassword("123456");
        users.setGender("男");
        users.setMobile("15235438348");
        users.setAvatar("http://q2mwrkwv6.bkt.clouddn.com/4a731a90594a4af544c0c25941171jpeg.jpeg");

        usersService.update(users);

    }

}
