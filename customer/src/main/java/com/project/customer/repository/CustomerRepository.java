package com.project.customer.repository;

import com.project.customer.model.Customer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

   Optional<Customer> findById(int id);

   @Cacheable
   @Query("SELECT b from Customer b where b.userName = :userName")
   Customer findByUserName(@Param("userName") String userName);
}
