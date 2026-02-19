package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.OptionNotFoundException;
import com.mx.loloscafe.backend_server.exceptions.UserNotFoundException;
import com.mx.loloscafe.backend_server.model.Option;
import com.mx.loloscafe.backend_server.service.OptionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/options")

public class OptionController {
    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    // Get all option
    @GetMapping
    public List<Option> getAllOptions() {
        return optionService.getOptions();
    }

    // Get an option by Id
    @GetMapping("/{id}")
    public ResponseEntity<Option> findOptionById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok( optionService.findById(id));
        } catch (OptionNotFoundException Ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Post one option - US
    @PostMapping("/new-option")
    public ResponseEntity<Option> saveOption(@RequestBody Option newOption) {

        if (optionService.existsById(newOption.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionService.createOption(newOption));
        }
    }

    // Update an option
    @PutMapping("/update-option/{id}")
    public ResponseEntity<Option> updateOption(@RequestBody Option option, @PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionService.updateOptionById(option, id));
        } catch (OptionNotFoundException Ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an option by id
    @DeleteMapping("/delete-option/{id}")
    public ResponseEntity<Option> deleteOptionById(@PathVariable Integer id) {
        try { optionService.deleteOptionById(id);
            return ResponseEntity.noContent().build();
        } catch (OptionNotFoundException Ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
