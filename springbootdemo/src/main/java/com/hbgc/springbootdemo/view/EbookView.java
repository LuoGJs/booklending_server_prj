package com.hbgc.springbootdemo.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbookView {
   private  String bookname;
   private  String authname;
   private  String catalog;
   private  String briefIntroducation;
   private  String detail;
   private  String images;
   private  int ebookuser;

}
