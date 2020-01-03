package com.hbgc.springbootdemo.service.impl;

import com.hbgc.springbootdemo.domain.BookMessage;
import com.hbgc.springbootdemo.repository.BookMessageRepository;
import com.hbgc.springbootdemo.service.BookMessageService;
import com.hbgc.springbootdemo.view.EbookView;
import com.hbgc.springbootdemo.view.EbookViewVo;
import com.hbgc.springbootdemo.view.NoEbookView;
import com.hbgc.springbootdemo.view.NoEbookViewVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookMessageServiceImpl extends BaseServiceImpl<BookMessage, Integer, BookMessageRepository> implements BookMessageService {
    @Override
    public List<BookMessage> queryBookMessagesByCid(int Cid) {
        return super.dao.queryBookMessagesByCid(Cid);
    }

    @Override
    public List<BookMessage> findBookMessagesByBooknameContaining(String bookname) {
        return super.dao.findBookMessagesByBooknameContaining(bookname);
    }

    @Override
    public List<EbookView> queryEbookView() {
        List<EbookView> ebookViewList = new ArrayList<EbookView>();
        List<EbookViewVo> ebookViewVoList = super.dao.queryEbookView();
        for (EbookViewVo ebookViewVo : ebookViewVoList) {
            EbookView ebookView = new EbookView();
            ebookView.setBookname(ebookViewVo.getbookname());
            ebookView.setAuthname(ebookViewVo.getauthname());
            ebookView.setBriefIntroducation(ebookViewVo.getbriefIntroducation());
            ebookView.setCatalog(ebookViewVo.getcatalog());
            ebookView.setDetail(ebookViewVo.getdetail());
            ebookView.setImages(ebookViewVo.getimages());
            ebookView.setEbookuser(ebookViewVo.getebookuser());
            ebookViewList.add(ebookView);
        }

        return ebookViewList;
    }


    @Override
    public List<NoEbookView> queryNoEbookView() {
        List<NoEbookView> noEbookViewList = new ArrayList<NoEbookView>();
        List<NoEbookViewVo> noEbookViewVoList = super.dao.queryNoEbookView();
        for (NoEbookViewVo noEbookViewVo: noEbookViewVoList ) {
            NoEbookView  noEbookView=new NoEbookView();
            noEbookView.setBookname(noEbookViewVo.getbookname());
            noEbookView.setAuthname(noEbookViewVo.getauthname());
            noEbookView.setCatalog(noEbookViewVo.getcatalog());
            noEbookView.setLocation(noEbookViewVo.getlocation());
            noEbookView.setNoebookuser(noEbookViewVo.getnoebookuser());
            noEbookViewList.add(noEbookView);
        }

        return noEbookViewList;
    }

    @Override
    public List<NoEbookView> queryNoeBookViewByBookname(String bookname) {
        List<NoEbookView> noEbookViewList = new ArrayList<NoEbookView>();
        List<NoEbookViewVo> noEbookViewVoList =super.dao.queryNoeBookViewByBookname(bookname);
        for (NoEbookViewVo noEbookViewVo: noEbookViewVoList ) {
            NoEbookView  noEbookView=new NoEbookView();
            noEbookView.setBookname(noEbookViewVo.getbookname());
            noEbookView.setAuthname(noEbookViewVo.getauthname());
            noEbookView.setCatalog(noEbookViewVo.getcatalog());
            noEbookView.setLocation(noEbookViewVo.getlocation());
            noEbookView.setNoebookuser(noEbookViewVo.getnoebookuser());
            noEbookViewList.add(noEbookView);
        }

        return noEbookViewList;
    }

    @Override
    public BookMessage queryByBookname(String bookname) {
        return super.dao.queryByBookname(bookname);
    }

    @Override
    public BookMessage queryById(int id) {
        return super.dao.queryById(id);
    }
}
