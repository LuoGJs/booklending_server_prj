package com.hbgc.springbootdemo.service.impl;


import com.hbgc.springbootdemo.domain.BookMessage;
import com.hbgc.springbootdemo.domain.LendBookMessage;
import com.hbgc.springbootdemo.domain.Users;
import com.hbgc.springbootdemo.domain.WhoLendWhichBook;
import com.hbgc.springbootdemo.repository.LendBookLinkRepository;
import com.hbgc.springbootdemo.service.BookMessageService;
import com.hbgc.springbootdemo.service.LendBookLinkService;
import com.hbgc.springbootdemo.service.UsersService;
import com.hbgc.springbootdemo.view.LendBookView;
import com.hbgc.springbootdemo.view.LendBookViewVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LendBookLinkServiceImpl extends BaseServiceImpl<WhoLendWhichBook, Integer, LendBookLinkRepository> implements LendBookLinkService {
    @Resource
    private BookMessageService bookMessageService;
    @Resource
    private UsersService usersService;


    @Override
    public void saveLendBookLik(LendBookMessage lendBookMessage) {
        Users users = usersService.queryByUsername(lendBookMessage.getUsername());
        BookMessage bookMessage = bookMessageService.queryByBookname(lendBookMessage.getBookname());
        WhoLendWhichBook whoLendWhichBook = new WhoLendWhichBook();
        whoLendWhichBook.setUid(users.getUid());
        whoLendWhichBook.setBid(bookMessage.getId());
        whoLendWhichBook.setStarttime(lendBookMessage.getStarttime());
        whoLendWhichBook.setDeadline(lendBookMessage.getDeadline());
        super.dao.save(whoLendWhichBook);
        bookMessage.setUseNum(bookMessage.getUseNum() + 1);
        bookMessageService.update(bookMessage);

    }

    @Override
    public List<LendBookView> queryLendBook(int uid) {
        List<LendBookView> lendBookViewList = new ArrayList<LendBookView>();
        List<LendBookViewVo> lendBookViewVoList = super.dao.queryLendBook(uid);
        for (LendBookViewVo lendBookViewVo :
                lendBookViewVoList) {
            LendBookView lendBookView = new LendBookView();
            lendBookView.setId(lendBookViewVo.getid());
            lendBookView.setBookname(lendBookViewVo.getbookname());
            lendBookView.setAuthname(lendBookViewVo.getauthname());
            lendBookView.setLocation(lendBookViewVo.getlocation());
            lendBookView.setUsenum(lendBookViewVo.getusenum());
            lendBookView.setStarttime(lendBookViewVo.getstarttime());
            lendBookView.setDeadline(lendBookViewVo.getdeadline());
            lendBookViewList.add(lendBookView);

        }

        return lendBookViewList;
    }

    @Override
    public void myupdate(LendBookView lendBookView) {
        int id = lendBookView.getId();
        WhoLendWhichBook whoLendWhichBook = super.dao.queryById(id);
        whoLendWhichBook.setFlag(true);
        update(whoLendWhichBook);

    }

    @Override
    public List<LendBookView> queryReturnBook(int uid) {
        List<LendBookView> lendBookViewList = new ArrayList<LendBookView>();
        List<LendBookViewVo> lendBookViewVoList = super.dao.queryRetuenBook(uid);
        for (LendBookViewVo lendBookViewVo:
             lendBookViewVoList ) {
            LendBookView lendBookView = new LendBookView();
            lendBookView.setId(lendBookViewVo.getid());
            lendBookView.setBookname(lendBookViewVo.getbookname());
            lendBookView.setAuthname(lendBookViewVo.getauthname());
            lendBookView.setLocation(lendBookViewVo.getlocation());
            lendBookView.setUsenum(lendBookViewVo.getusenum());
            lendBookView.setStarttime(lendBookViewVo.getstarttime());
            lendBookView.setDeadline(lendBookViewVo.getdeadline());
            lendBookView.setModifytime(lendBookViewVo.getmodifytime());
            lendBookViewList.add(lendBookView);
        }

        return lendBookViewList;
    }
}
