package com.example2.carroservice.services;

import com.example2.carroservice.entitys.Carro;
import com.example2.carroservice.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAllCarro(){
        return carroRepository.findAll();
    }

    public Carro getCarroById(int id){
        return carroRepository.findById(id).orElse(null);
    }

    public Carro save(Carro datos){
        Carro newCarro = carroRepository.save(datos);
        return newCarro;
    }

    public List<Carro> getCarroByUsuarioId(int usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }

}
