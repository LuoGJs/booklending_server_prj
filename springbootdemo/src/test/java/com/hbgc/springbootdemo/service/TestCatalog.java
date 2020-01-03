package com.hbgc.springbootdemo.service;

import com.hbgc.springbootdemo.domain.Catalog;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCatalog {
    @Resource
    private  CatalogService catalogService;
    @Test
    public void testCatalog(){
        List<Catalog> catalogList=new ArrayList<Catalog>();
       catalogList= catalogService.findEbookCatalog();
        for (Catalog c:
            catalogList ) {
            System.out.println("***"+c.getCname());
        }
    }
}
