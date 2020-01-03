package com.hbgc.springbootdemo.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendBookView {

    private int id;
    private String bookname;
    private String location;
    private int usenum;
    private String starttime;
    private String deadline;
    private String authname;
    private  String modifytime;


}
