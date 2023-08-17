package com.example2.carroservice.controller;

import com.example2.carroservice.entitys.Carro;
import com.example2.carroservice.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    private CarroService carroService;


    @GetMapping
    public ResponseEntity<List<Carro>> listaCarros(){
        List<Carro> usuarios = carroService.getAllCarro();
        if(usuarios.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerUsuarioId(@PathVariable("id") int id){
        Carro carro = carroService.getCarroById(id);
        if(carro == null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carro);
    }


    @PostMapping
    public ResponseEntity<Carro> guardarUsuario(@RequestBody Carro datos){
        Carro newUsuarios = carroService.save(datos);

        return ResponseEntity.ok(newUsuarios);
    }


    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Carro>> listaCarrosByUsuarioId(@PathVariable("usuarioId") int usuarioId){
        List<Carro> carros = carroService.getCarroByUsuarioId(usuarioId);
        if(carros.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }


}
