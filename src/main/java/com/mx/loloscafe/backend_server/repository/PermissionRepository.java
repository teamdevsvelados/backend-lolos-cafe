
package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Optional<Permission> findByNameOf(String nameOf);
    boolean existsByNameOf(String nameOf);

}
