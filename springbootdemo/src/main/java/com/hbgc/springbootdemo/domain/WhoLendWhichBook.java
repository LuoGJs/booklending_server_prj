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
@Table(name = "WhoLendWhichBook")
public class WhoLendWhichBook  extends BaseEntity implements Serializable, Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int uid;
    private int bid;
    private String starttime;
    private String deadline;
}
