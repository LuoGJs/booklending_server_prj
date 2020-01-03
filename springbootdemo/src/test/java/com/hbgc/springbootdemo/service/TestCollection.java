package com.hbgc.springbootdemo.service;

import com.hbgc.springbootdemo.view.CollectionView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCollection {

    @Resource
    private CollectionService collectionService;

    @Test
    public  void testQueryCollection(){
        List<CollectionView> collectionViewList=new ArrayList<>();
        collectionViewList= collectionService.queryCollection(1);
        for (CollectionView collectionView:
                collectionViewList ) {
            System.out.println(collectionView.getBookname());
        }

    }


    @Test
    public  void testDeleteCollection(){

         collectionService.delete("京华烟云",1);



    }
}
