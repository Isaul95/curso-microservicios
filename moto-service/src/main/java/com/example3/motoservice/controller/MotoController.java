package com.example3.motoservice.controller;

import com.example3.motoservice.entitys.Moto;
import com.example3.motoservice.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {
    @Autowired
    private MotoService motoService;


    @GetMapping
    public ResponseEntity<List<Moto>> listaMotos(){
        List<Moto> usuarios = motoService.getAllMoto();
        if(usuarios.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMotoId(@PathVariable("id") int id){
        Moto moto = motoService.getMotoById(id);
        if(moto == null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(moto);
    }


    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto datos){
        Moto newMoto = motoService.save(datos);

        return ResponseEntity.ok(newMoto);
    }


    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Moto>> listaMotosByUsuarioId(@PathVariable("usuarioId") int usuarioId){
        List<Moto> moto = motoService.getMotoByUsuarioId(usuarioId);
        if(moto.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(moto);
    }



}
