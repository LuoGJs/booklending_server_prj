package com.hbgc.springbootdemo.service.impl;


import com.hbgc.springbootdemo.domain.BookMessage;
import com.hbgc.springbootdemo.domain.WhoLoveWhichBook;
import com.hbgc.springbootdemo.repository.CollectionRepository;
import com.hbgc.springbootdemo.service.BookMessageService;
import com.hbgc.springbootdemo.service.CollectionService;
import com.hbgc.springbootdemo.view.CollectionView;
import com.hbgc.springbootdemo.view.CollectionViewVo;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl  extends BaseServiceImpl<WhoLoveWhichBook, Integer, CollectionRepository> implements CollectionService {

    @Resource
    private BookMessageService bookMessageService;



    @Override
    public void save(String bookname, int uid) {

       BookMessage bookMessage= bookMessageService.queryByBookname(bookname);
       WhoLoveWhichBook whoLoveWhichBook=new WhoLoveWhichBook();
       whoLoveWhichBook.setUid(uid);
       whoLoveWhichBook.setBid(bookMessage.getId());
       super.dao.save(whoLoveWhichBook);
    }

    @Override
    public List<CollectionView> queryCollection(int uid) {

        List<CollectionView> collectionViewList=new ArrayList<CollectionView>();
        List<CollectionViewVo> collectionViewVoList=super.dao.queryCollection(uid);
        for (CollectionViewVo  collectionViewVo:
             collectionViewVoList ) {

            CollectionView collectionView=new CollectionView();
            collectionView.setBookname(collectionViewVo.getbookname());
            collectionView.setAuthname(collectionViewVo.getauthname());
            collectionView.setLocation(collectionViewVo.getlocation());
            collectionView.setUsenum(collectionViewVo.getusenum());
            collectionViewList.add(collectionView);
        }
        return collectionViewList;
    }

    @Override
    public void delete(String bookname, int uid) {
       BookMessage bookMessage= bookMessageService.queryByBookname(bookname);
       int bid=bookMessage.getId();
      WhoLoveWhichBook w= super.dao.queryByBidAndUid(bid,uid);
      super.dao.delete(w);

    }


}
