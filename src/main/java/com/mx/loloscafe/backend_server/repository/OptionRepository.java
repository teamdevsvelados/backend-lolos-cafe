package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Option;
import com.mx.loloscafe.backend_server.model.enums.OptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {


    Option findByTypeOf (OptionType type);
}