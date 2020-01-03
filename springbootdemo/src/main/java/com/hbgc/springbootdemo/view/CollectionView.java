package com.hbgc.springbootdemo.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionView {

    private String bookname;
    private String location;
    private int usenum;
    private String authname;


}
