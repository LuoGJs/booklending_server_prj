package com.hbgc.springbootdemo.controller;

import com.hbgc.springbootdemo.domain.LendBookMessage;
import com.hbgc.springbootdemo.domain.WhoLendWhichBook;
import com.hbgc.springbootdemo.json.Json;
import com.hbgc.springbootdemo.service.LendBookLinkService;

import com.hbgc.springbootdemo.view.LendBookView;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("lendbook")
public class LendBookController {

    @Resource
    private LendBookLinkService lendBookLinkService;


    @PostMapping("/save")
    public Map<String, Object> saveLendbookLink(@RequestBody LendBookMessage lendBookMessage) {
        try {
            lendBookLinkService.saveLendBookLik(lendBookMessage);
            return Json.success(null, "借书成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("借书失败");
        }
    }


    @GetMapping("/query")
    public Map<String, Object> querylendbook(int uid) {
        List<LendBookView> lendBookViewList = new ArrayList<LendBookView>();
        try {
            lendBookViewList = lendBookLinkService.queryLendBook(uid);
            return Json.success(lendBookViewList, "查询借书成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询借书失败");
        }
    }


    @GetMapping("/queryreturn")
    public Map<String, Object> queryreturnbook(int uid) {
        List<LendBookView> returnBookViewList = new ArrayList<LendBookView>();
        try {
            returnBookViewList = lendBookLinkService.queryReturnBook(uid);
            return Json.success(returnBookViewList,"查询还书成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询还书失败");
        }
    }


    @PutMapping("/update")
    public Map<String, Object> updateLendbook(@RequestBody LendBookView lendBookView ) {
        try {

            lendBookLinkService.myupdate(lendBookView);
            return Json.success(null, "还书成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("还书失败");
        }
    }
}
