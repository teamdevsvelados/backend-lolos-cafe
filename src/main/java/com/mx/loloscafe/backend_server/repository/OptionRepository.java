package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}