package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNameOf(String nameOf);
    boolean existsByNameOf(String nameOf);
}
