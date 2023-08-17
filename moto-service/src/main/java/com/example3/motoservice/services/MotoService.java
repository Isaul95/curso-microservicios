package com.example3.motoservice.services;

import com.example3.motoservice.entitys.Moto;
import com.example3.motoservice.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MotoService {
    @Autowired
    private MotoRepository motoRepository;


    public List<Moto> getAllMoto(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(int id){
        return motoRepository.findById(id).orElse(null);
    }

    public Moto save(Moto datos){
        Moto newMoto = motoRepository.save(datos);
        return newMoto;
    }

    public List<Moto> getMotoByUsuarioId(int motoId){
        return motoRepository.findByUsuarioId(motoId);
    }

}
