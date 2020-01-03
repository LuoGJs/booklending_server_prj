package com.hbgc.springbootdemo.repository;

import com.hbgc.springbootdemo.domain.WhoLoveWhichBook;
import com.hbgc.springbootdemo.view.CollectionView;
import com.hbgc.springbootdemo.view.CollectionViewVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CollectionRepository extends BaseRepository<WhoLoveWhichBook, Integer> {


    @Query(nativeQuery = true, value = "SELECT b.bookname as bookname,b.location as location,b.use_num as usenum,a.authname as authname\n" +
            "FROM who_love_which_book l,users u,book_message b,author a\n" +
            "WHERE l.uid=u.uid AND l.bid=b.id and b.aid=a.id AND l.uid=?1")
    List<CollectionViewVo> queryCollection(int uid);



    WhoLoveWhichBook queryByBidAndUid(int Bid,int Uid);

}
