package com.hbgc.springbootdemo.service;


import com.hbgc.springbootdemo.domain.WhoLoveWhichBook;
import com.hbgc.springbootdemo.repository.CollectionRepository;
import com.hbgc.springbootdemo.view.CollectionView;


import java.util.List;


public interface CollectionService extends BaseService<WhoLoveWhichBook, Integer, CollectionRepository> {
    void save(String bookname, int uid);

    List<CollectionView> queryCollection(int uid);

    void delete(String bookname,int uid);
}
