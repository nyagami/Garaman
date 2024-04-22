package com.nyagami.gara.repository;

import com.nyagami.gara.model.SupplierModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<SupplierModel, Long>, CrudRepository<SupplierModel, Long> {
    Page<SupplierModel> findByNameContaining(String name, Pageable pageable);
}
