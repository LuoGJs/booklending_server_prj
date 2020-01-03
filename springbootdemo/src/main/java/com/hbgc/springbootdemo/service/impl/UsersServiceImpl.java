package com.hbgc.springbootdemo.service.impl;

import com.hbgc.springbootdemo.domain.Users;
import com.hbgc.springbootdemo.repository.UsersRepository;
import com.hbgc.springbootdemo.service.UsersService;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl extends BaseServiceImpl<Users,Integer, UsersRepository> implements UsersService {

    @Override
    public Users login(String username, String password) {
        return super.dao.findByUsernameAndPassword(username,password);
    }

    @Override
    public Users queryUsersByMobile(String mobile) {
        return super.dao.queryUsersByMobile(mobile);
    }

    @Override
    public Users queryByUsername(String username) {
        return super.dao.queryByUsername(username);
    }
}
