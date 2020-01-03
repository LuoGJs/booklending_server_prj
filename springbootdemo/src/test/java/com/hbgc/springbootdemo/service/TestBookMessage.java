package com.hbgc.springbootdemo.service;

import com.hbgc.springbootdemo.domain.BookMessage;
import com.hbgc.springbootdemo.view.EbookView;
import com.hbgc.springbootdemo.view.NoEbookView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBookMessage {

    @Resource
    private BookMessageService bookMessageService;

    @Test
    public  void testQueryBookByCid(){

        List<BookMessage>bookMessageList=new ArrayList<BookMessage>();
        bookMessageList =bookMessageService.queryBookMessagesByCid(20);
        for (BookMessage book:
           bookMessageList  ) {
            System.out.println(book.getBookname());
        }

    }


    @Test
    public  void testQueryEbook(){
        List<EbookView> ebookViewList=new ArrayList<>();
        ebookViewList=bookMessageService.queryEbookView();
        for (EbookView ebookView:
            ebookViewList ) {
            System.out.println(ebookView.getEbookuser());
        }
    }


    @Test
    public  void testQueryNoEbook(){
      List<NoEbookView> noEbookViewList=new ArrayList<>();
      noEbookViewList=bookMessageService.queryNoEbookView();
        for (NoEbookView noEbookView:
            noEbookViewList ) {
            System.out.println(noEbookView.getNoebookuser());
        }
    }


    @Test
    public  void testQueryNoEbookByBookname(){
        List<NoEbookView> noEbookViewList=new ArrayList<>();
        noEbookViewList=bookMessageService.queryNoeBookViewByBookname("西游记");
        for (NoEbookView noEbookView:
                noEbookViewList ) {
            System.out.println(noEbookView.getBookname());
        }
    }


    @Test
    public  void testQueryById(){
        BookMessage bookMessage=  bookMessageService.queryById(24);
        System.out.println(bookMessage.getBookname());
    }


    @Test
    public  void testQueryByBookname(){
        BookMessage bookMessage=  bookMessageService.queryByBookname("京华烟云");
        System.out.println(bookMessage.getAid());
    }
}
