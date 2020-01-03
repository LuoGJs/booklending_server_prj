package com.hbgc.springbootdemo.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoEbookView {

private  String bookname;
private  String authname;
private  String catalog;
private  int noebookuser;
private  String location;

}
