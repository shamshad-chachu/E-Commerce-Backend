package com.Coorg.ECommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Coorg.ECommerce.Model.Order;


@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

}
