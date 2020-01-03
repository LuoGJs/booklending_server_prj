package com.hbgc.springbootdemo.repository;


import com.hbgc.springbootdemo.domain.WhoLendWhichBook;
import com.hbgc.springbootdemo.view.CollectionViewVo;
import com.hbgc.springbootdemo.view.LendBookViewVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LendBookLinkRepository extends BaseRepository<WhoLendWhichBook, Integer> {


    @Query(nativeQuery = true, value = "SELECT l.id as id, b.bookname as bookname,b.location as location,b.use_num as usenum,l.starttime as starttime,l.deadline as deadline,a.authname as authname\n" +
            "FROM who_lend_which_book l,users u,book_message b,author a\n" +
            "WHERE l.uid=u.uid AND l.bid=b.id AND b.aid=a.id AND l.uid=?1 and l.flag=false ")
    List<LendBookViewVo> queryLendBook(int uid);

    WhoLendWhichBook queryById(int id);


    @Query(nativeQuery = true, value = "SELECT l.id as id, b.bookname as bookname,b.location as location,b.use_num as usenum,l.starttime as starttime,l.deadline as deadline,a.authname as authname,l.modify_time as modifytime\n" +
            "FROM who_lend_which_book l,users u,book_message b,author a\n" +
            "WHERE l.uid=u.uid AND l.bid=b.id AND b.aid=a.id AND l.flag=TRUE AND l.uid=?1 ")
    List<LendBookViewVo> queryRetuenBook(int uid);

}
