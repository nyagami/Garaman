package com.nyagami.gara.repository;

import com.nyagami.gara.data.AccessoryOrderStatus;
import com.nyagami.gara.model.AccessoryOrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccessoryOrderRepository extends PagingAndSortingRepository<AccessoryOrderModel, Long>, CrudRepository<AccessoryOrderModel, Long> {
    Page<AccessoryOrderModel> findByStatus(AccessoryOrderStatus status, Pageable pageable);
}
