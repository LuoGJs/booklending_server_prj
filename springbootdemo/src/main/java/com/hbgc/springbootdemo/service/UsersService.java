package com.hbgc.springbootdemo.service;

import com.hbgc.springbootdemo.domain.Users;
import com.hbgc.springbootdemo.repository.UsersRepository;

public interface UsersService extends BaseService<Users, Integer, UsersRepository> {

    Users login(String username, String password);

    Users queryUsersByMobile(String mobile);

    Users queryByUsername(String username);

}
