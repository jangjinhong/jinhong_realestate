package com.example.jinhong_realestate.repository;

import com.example.jinhong_realestate.entity.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
//    List<Agent> findByAddressContaining(String address);
    Page<Agent> findByAddressContaining(String address, Pageable pageable);
}
