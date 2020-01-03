package com.hbgc.springbootdemo.service;

import com.hbgc.springbootdemo.view.LendBookView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBookLend {

    @Resource
    private LendBookLinkService lendBookLinkService;

//    @Test
//    public  void testUpdate(){
//        try {
//            LendBookView lendBookView=new LendBookView();
//            lendBookView.setId(15);
//            lendBookView.setUsenum();
//            lendBookLinkService.myupdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("修改成功");
//
//    }
}
