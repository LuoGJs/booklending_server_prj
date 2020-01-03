package com.hbgc.springbootdemo.service;

import com.hbgc.springbootdemo.domain.Catalog;
import com.hbgc.springbootdemo.repository.CatalogRepository;

import java.util.List;

public interface CatalogService extends BaseService<Catalog,Integer, CatalogRepository> {

    List<Catalog> findNoEbookCatalog();

    List<Catalog> findEbookCatalog();

}
