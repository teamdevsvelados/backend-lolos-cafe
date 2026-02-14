package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.SizeNotFoundException;
import com.mx.loloscafe.backend_server.model.Size;
import com.mx.loloscafe.backend_server.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    private final SizeRepository sizeRepository;

    @Autowired
    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public List<Size> getSizes(){
        return sizeRepository.findAll();
    }

    public Size createSize(Size newSize){
        return sizeRepository.save(newSize);
    }

    public Size findByNameOf (String nameOf){
        return sizeRepository.findByNameOf(nameOf);
    }

    public Size findById(Integer id){
        return sizeRepository.findById(id).orElseThrow(() -> new SizeNotFoundException(id));
    }


    public void deleteSizeById(Integer id){
        if(sizeRepository.existsById(id)){
            sizeRepository.deleteById(id);
        } else {
            throw new SizeNotFoundException(id);
        }
    }

    //Update Size by ID
    public Size updateSizeById(Size size, Integer id){
        return sizeRepository.findById(id)
                .map(sizeData ->{
                    sizeData.setNameOf(size.getNameOf());
                    return sizeRepository.save(sizeData);
                })
                .orElseThrow(()-> new SizeNotFoundException(id));
    }
}
