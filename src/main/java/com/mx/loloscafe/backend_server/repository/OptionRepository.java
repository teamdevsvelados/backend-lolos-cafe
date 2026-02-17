package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Option;
import com.mx.loloscafe.backend_server.model.enums.OptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
    //administrador ver todo
    List<Option> findByType(OptionType type);

    //cliente ver las opciones activas.
    List<Option> findByTypeAndAvailableTrue(OptionType type);

//    Option findByTypeOf (OptionType type);
}