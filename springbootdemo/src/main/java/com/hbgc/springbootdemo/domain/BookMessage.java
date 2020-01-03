package com.hbgc.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BookMessage")
public class BookMessage extends BaseEntity implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookname;
    private int cid;
    private int  aid;
    private  String images;
    private  String briefIntroducation;
    private  String detail;
    private  String location;
    private  int useNum;

}
