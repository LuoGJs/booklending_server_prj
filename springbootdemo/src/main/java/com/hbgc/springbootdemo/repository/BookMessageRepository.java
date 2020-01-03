package com.hbgc.springbootdemo.repository;


import com.hbgc.springbootdemo.domain.BookMessage;
import com.hbgc.springbootdemo.view.EbookViewVo;
import com.hbgc.springbootdemo.view.NoEbookViewVo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookMessageRepository extends BaseRepository<BookMessage, Integer> {


    List<BookMessage> queryBookMessagesByCid(int Cid);

    List<BookMessage> findBookMessagesByBooknameContaining(String bookname);


    @Query(nativeQuery = true, value = "select b.bookname as bookname,a.authname as authname,c.cname as catalog,b.brief_introducation as brief_introducation,b.detail as detail,b.images as images,b.use_num as ebookuser\n" +
            "from book_message b,catalog c,author a\n" +
            "where b.cid=c.cid and b.aid=a.id and c.e_book=1\n")
    List<EbookViewVo> queryEbookView();


    @Query(nativeQuery = true, value = "select b.bookname as bookname,a.authname as authname,c.cname as catalog,b.use_num as noebookuser,b.location\n" +
            "from book_message b,catalog c,author a\n" +
            "where b.cid=c.cid and b.aid=a.id and c.e_book=0\n")
    List<NoEbookViewVo> queryNoEbookView();

    @Modifying
    @Query(nativeQuery = true, value = "select b.bookname as bookname,a.authname as authname,c.cname as catalog,b.use_num as noebookuser,b.location\n" +
            "from book_message b,catalog c,author a\n" +
            "where b.cid=c.cid and b.aid=a.id and c.e_book=0 and b.bookname=?1\n")
    List<NoEbookViewVo> queryNoeBookViewByBookname(String bookname);


    BookMessage queryByBookname(String bookname);

    BookMessage queryById(int id);
}
