package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.SizeNotFoundException;
import com.mx.loloscafe.backend_server.model.Size;
import com.mx.loloscafe.backend_server.service.SizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sizes")
public class SizeController {
    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public List<Size> getAllSizes(){
        return sizeService.getSizes();
    }

    @PostMapping("/new-size")
    public ResponseEntity<Size> saveSize (@RequestBody Size newSize){
        Size createSize = sizeService.findByNameOf(newSize.getNameOf());
        if (createSize != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(sizeService.createSize(newSize));

        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Size> findSizeById (@PathVariable Integer id){
        try {
            return ResponseEntity.ok(sizeService.findById(id));
        } catch (SizeNotFoundException x){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-size/{id}")
    public ResponseEntity<Size> deleteSizeById (@PathVariable Integer id){
        try { sizeService.deleteSizeById(id);
            return ResponseEntity.noContent().build();
        } catch (SizeNotFoundException x){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-size/{id}")
    public ResponseEntity<Size> updateSizeById (@RequestBody Size size, @PathVariable Integer id){
        try { return ResponseEntity.status(HttpStatus.CREATED).body(sizeService.updateSizeById(size, id));
        } catch (SizeNotFoundException x){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
