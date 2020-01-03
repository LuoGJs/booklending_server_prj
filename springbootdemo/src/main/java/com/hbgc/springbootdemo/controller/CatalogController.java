package com.hbgc.springbootdemo.controller;

import com.hbgc.springbootdemo.domain.Catalog;
import com.hbgc.springbootdemo.json.Json;
import com.hbgc.springbootdemo.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("catalog")
public class CatalogController extends BaseController {

    @Resource
    private CatalogService catalogService;

    @GetMapping("/")
    public Map<String,Object> queryAllCatalog(){
        List<Catalog> catalogList;
        try{
            catalogList = catalogService.findAll();
            return Json.success(catalogList,"查询版块资料成功！");
        }catch(Exception ex){
            ex.printStackTrace();
            return Json.fail("查询版块资料失败！");
        }
    }

    @GetMapping("/noebookcata")
    public  Map<String,Object> findNoeBookCatalog(){
        List<Catalog> catalogList;
        try {
            catalogList= catalogService.findNoEbookCatalog();
            return Json.success(catalogList,"查询纸质书类别成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询纸质书类别失败！");
        }

    }



    @GetMapping("/ebookcata")
    public  Map<String,Object> findeBookCatalog(){
        List<Catalog> catalogList;
        try {
            catalogList= catalogService.findEbookCatalog();
            return Json.success(catalogList,"查询电子书类别!");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询电子书类别失败！");
        }

    }

}
