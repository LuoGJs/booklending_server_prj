package com.hbgc.springbootdemo.repository;

import com.hbgc.springbootdemo.domain.Users;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends BaseRepository<Users, Integer> {

    Users findByUsernameAndPassword(String username, String password);


    Users queryUsersByMobile(String mobile);


    Users queryByUsername(String username);

}
