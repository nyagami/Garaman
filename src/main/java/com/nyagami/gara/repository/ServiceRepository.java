package com.nyagami.gara.repository;

import com.nyagami.gara.model.ServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServiceRepository extends PagingAndSortingRepository<ServiceModel, Long>, CrudRepository<ServiceModel, Long> {
    Page<ServiceModel> findByNameContaining(String name, Pageable pageable);
}
