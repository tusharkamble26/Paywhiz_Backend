package com.paywhiz.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paywhiz.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
