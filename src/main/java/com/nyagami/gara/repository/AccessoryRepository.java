package com.nyagami.gara.repository;

import com.nyagami.gara.model.AccessoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccessoryRepository extends PagingAndSortingRepository<AccessoryModel, Long>,  CrudRepository<AccessoryModel, Long> {
    Page<AccessoryModel> findByNameContaining(String name, Pageable pageable);
}
