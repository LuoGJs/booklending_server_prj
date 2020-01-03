package com.hbgc.springbootdemo.controller;


import com.hbgc.springbootdemo.json.Json;
import com.hbgc.springbootdemo.service.CollectionService;
import com.hbgc.springbootdemo.view.CollectionView;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("collection")
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    @GetMapping("/save")
    public Map<String,Object> saveLendbookLink(String bookname,String id){

        try {
            int uid=Integer.parseInt(id);
            collectionService.save(bookname,uid);
            return Json.success(null,"收藏成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("收藏失败");
        }


    }




    @GetMapping("/query")
    public Map<String,Object> queryCollection(int uid){

        try {
            List<CollectionView> collectionViewList=new ArrayList<>();
            collectionViewList=collectionService.queryCollection(uid);
            return Json.success(collectionViewList,"查询喜爱成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询喜爱失败");
        }


    }


    @DeleteMapping("/delete")
    public Map<String,Object> deleteCollection(String bookname,int uid){

        try {
            collectionService.delete(bookname,uid);
            return Json.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("删除失败");
        }


    }
}
