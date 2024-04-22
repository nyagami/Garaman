package com.nyagami.gara.repository;

import com.nyagami.gara.model.AccessoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AccessoryRepository extends PagingAndSortingRepository<AccessoryModel, Long>,  CrudRepository<AccessoryModel, Long> {
    Page<AccessoryModel> findByNameOrProductCodeContaining(String name, String productCode, Pageable pageable);
}
