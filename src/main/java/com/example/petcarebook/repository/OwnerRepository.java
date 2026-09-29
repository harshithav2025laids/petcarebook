package com.example.petcarebook.repository;

import com.example.petcarebook.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}