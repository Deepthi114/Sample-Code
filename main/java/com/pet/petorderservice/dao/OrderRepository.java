package com.pet.petorderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pet.petorderservice.domain.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {


}
