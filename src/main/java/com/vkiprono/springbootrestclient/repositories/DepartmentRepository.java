package com.vkiprono.springbootrestclient.repositories;

import com.vkiprono.springbootrestclient.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
