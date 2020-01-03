package com.hbgc.springbootdemo.repository;

import com.hbgc.springbootdemo.domain.Catalog;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatalogRepository extends BaseRepository<Catalog,Integer> {


    @Query("from Catalog c where c.eBook=0")
    List<Catalog> findNoeBookCatalog();


    @Query("from Catalog c where c.eBook=1")
    List<Catalog> findeBookCatalog();
}
