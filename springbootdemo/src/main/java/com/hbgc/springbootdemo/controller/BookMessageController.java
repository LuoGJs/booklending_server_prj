package com.hbgc.springbootdemo.controller;

import com.hbgc.springbootdemo.domain.BookMessage;
import com.hbgc.springbootdemo.json.Json;
import com.hbgc.springbootdemo.service.BookMessageService;
import com.hbgc.springbootdemo.view.EbookView;
import com.hbgc.springbootdemo.view.NoEbookView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bookmessage")
public class BookMessageController extends BaseController {

    @Resource
    private BookMessageService bookMessageService;

//根据类别查找图书信息
    @GetMapping("/catalog")
    public Map<String,Object> queryBookByCatalog(int Cid){
        List<BookMessage> bookMessageList=new ArrayList<BookMessage>();


        try {
            bookMessageList=bookMessageService.queryBookMessagesByCid(Cid);
            return Json.success(bookMessageList,"查询书籍资料成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询书籍资料失败！");
        }

    }

//查找所有图书信息
    @GetMapping("/allbook")
    public Map<String,Object> queryAllBook(){
        List<BookMessage> bookMessageList=new ArrayList<BookMessage>();


        try {
            bookMessageList=bookMessageService.findAll();
            return Json.success(bookMessageList,"查询所有书籍资料成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询所有书籍资料失败！");
        }

    }

//根据图书名字查找图书信息
    @GetMapping("/bookname")
    public Map<String,Object> queryBookByBookname(String bookname){
        List<BookMessage> bookMessageList=new ArrayList<BookMessage>();


        try {
            bookMessageList=bookMessageService.findBookMessagesByBooknameContaining(bookname);
            return Json.success(bookMessageList,"根据书名查找书籍成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("根据书名查找书籍失败！");
        }

    }

    //查询所有电子书的视图
    @GetMapping("/ebook")
    public Map<String,Object> queryEbook(){
        List<EbookView> ebookViewList=new ArrayList<EbookView>();

        try {
            ebookViewList=bookMessageService.queryEbookView();
            return Json.success(ebookViewList,"查找所有电子书成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查找电子书失败");
        }


    }




    //查询所有纸质书的视图
    @GetMapping("/noebook")
    public Map<String,Object> queryNoEbook(){
        List<NoEbookView> noEbookViewList=new ArrayList<NoEbookView>();

        try {
            noEbookViewList=bookMessageService.queryNoEbookView();
            return Json.success(noEbookViewList,"查找所有纸质书成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查找所有纸质书失败");
        }


    }


    //根据书名查询纸质书的视图
    @GetMapping("/noebookbybookname")
    public Map<String,Object> queryNoEbookByBookname(String bookname){
        List<NoEbookView> noEbookViewList=new ArrayList<NoEbookView>();

        try {
            noEbookViewList=bookMessageService.queryNoeBookViewByBookname(bookname);
            return Json.success(noEbookViewList,"查找纸质书成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查找纸质书失败");
        }


    }


  //根据ID查找
    @GetMapping("/bookmessageid")
    public Map<String,Object> queryBookById(int id){
        try {
            BookMessage bookMessage=bookMessageService.queryById(id);
            return Json.success(bookMessage,"成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("失败！");
        }

    }


    //模糊查找
    @GetMapping("/booknamelike")
    public Map<String,Object> queryBookByNameLike(String bookname){
        List<BookMessage>bookMessageList=new ArrayList<BookMessage>();
        try {
            bookMessageList=bookMessageService.findBookMessagesByBooknameContaining(bookname);
            return Json.success(bookMessageList,"成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("失败！");
        }

    }
}
