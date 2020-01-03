package com.hbgc.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendBookMessage {

    private String starttime;
    private String deadline;
    private  String username;
    private  String bookname;
}
