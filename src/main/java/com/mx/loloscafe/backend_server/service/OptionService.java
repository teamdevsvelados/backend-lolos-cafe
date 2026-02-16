package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.OptionNotFoundException;
import com.mx.loloscafe.backend_server.model.Option;
import com.mx.loloscafe.backend_server.model.enums.OptionType;
import com.mx.loloscafe.backend_server.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    // Method to get all the options
    public List<Option> getOptions() {
        return optionRepository.findAll();
    }

    // Method create new option.
    public Option createOption(Option newOption) {
        return optionRepository.save(newOption);
    }

    // Method find by tipOf option (TEMPERATURA, LECHE, EXTRA)
    public Option findByTypeOf(OptionType type) {
        return optionRepository.findByTypeOf(type);
    }

    // Method to recover by optionById
    public Option findById(Integer id) {
        return optionRepository.findById(id).orElseThrow(() -> new OptionNotFoundException(id));
    }

    // Method to delete option by ID
    public void deleteOptionById(Integer id) {
        if(optionRepository.existsById(id)) {
            optionRepository.deleteById(id);
        } else {
            throw new OptionNotFoundException(id);
        }
    }

    // Method to update an option
//    public Option updateOptionById(Option option, Integer id) {
//        return optionRepository.findById(id)
//                .map(optionData -> {
//                    optionData.setOpyionType
//                })
//    }

}
