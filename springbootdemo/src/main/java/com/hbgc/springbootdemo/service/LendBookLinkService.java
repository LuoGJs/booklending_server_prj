package com.hbgc.springbootdemo.service;


import com.hbgc.springbootdemo.domain.LendBookMessage;
import com.hbgc.springbootdemo.domain.WhoLendWhichBook;
import com.hbgc.springbootdemo.repository.LendBookLinkRepository;
import com.hbgc.springbootdemo.view.LendBookView;


import java.util.List;

public interface LendBookLinkService extends BaseService<WhoLendWhichBook,Integer, LendBookLinkRepository>{
    void  saveLendBookLik(LendBookMessage lendBookMessage);

    List<LendBookView> queryLendBook(int uid);

    void myupdate(LendBookView lendBookView);

    List<LendBookView> queryReturnBook(int uid);
}
