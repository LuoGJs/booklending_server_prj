package com.hbgc.springbootdemo.service;

import com.hbgc.springbootdemo.domain.BookMessage;
import com.hbgc.springbootdemo.repository.BookMessageRepository;
import com.hbgc.springbootdemo.view.EbookView;
import com.hbgc.springbootdemo.view.NoEbookView;


import java.util.List;

public interface BookMessageService extends BaseService<BookMessage, Integer, BookMessageRepository> {

    List<BookMessage> queryBookMessagesByCid(int Cid);

    List<BookMessage> findBookMessagesByBooknameContaining(String bookname);

    List<EbookView> queryEbookView();

    List<NoEbookView> queryNoEbookView();

    List<NoEbookView> queryNoeBookViewByBookname(String bookname);

    BookMessage queryByBookname(String bookname);

    BookMessage queryById(int id);
}
