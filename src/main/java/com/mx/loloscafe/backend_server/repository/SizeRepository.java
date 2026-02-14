package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    Size findByNameOf(String nameOf);

}
