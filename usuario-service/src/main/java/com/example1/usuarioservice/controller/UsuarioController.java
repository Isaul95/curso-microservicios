package com.example1.usuarioservice.controller;

import com.example1.usuarioservice.entitys.Usuarios;
import com.example1.usuarioservice.modelos.Carros;
import com.example1.usuarioservice.modelos.Moto;
import com.example1.usuarioservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Usuarios>> listaUsuarios(){
        List<Usuarios> usuarios = usuarioService.getAllUser();
        if(usuarios.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerUsuarioId(@PathVariable("id") int id){
        Usuarios usuarios = usuarioService.getUserById(id);
        if(usuarios == null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }


    @PostMapping
    public ResponseEntity<Usuarios> guardarUsuario(@RequestBody Usuarios datos){
        Usuarios newUsuarios = usuarioService.save(datos);

        return ResponseEntity.ok(newUsuarios);
    }


    /** ----------------------------------------------     Rest Template     -------------------------------------------

     *  Consulta de los Microservicios de Carros y Motos
     */

    // Consuta del microsrvicio de CARROS...
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carros>> listaCarros(@PathVariable("usuarioId") int id){
        Usuarios usuarios = usuarioService.getUserById(id);
        if(usuarios == null){
            return ResponseEntity.notFound().build();
        }
        List<Carros> carros = usuarioService.getCarrosByIdUser(id);
        return ResponseEntity.ok(carros);
    }


    // Consuta del microsrvicio de MOTOS...
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listaMotos(@PathVariable("usuarioId") int id){
        Usuarios usuarios = usuarioService.getUserById(id);
        if(usuarios == null){
            return ResponseEntity.notFound().build();
        }
        List<Moto> moto = usuarioService.getMotosByIdUser(id);
        return ResponseEntity.ok(moto);
    }


    /** ----------------------------------------------     Feign Client     -------------------------------------------
     * Save carro desde --- Controller USUARIO
     */

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carros> guardarCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carros datos){
        Carros newCarro = usuarioService.saveCarro(usuarioId, datos);

        return ResponseEntity.ok(newCarro);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto motos){
        Moto newMoto = usuarioService.saveMoto(usuarioId, motos);

        return ResponseEntity.ok(newMoto);
    }


    /** ----------------------------------------------     Feign Client     -------------------------------------------
     * get USUARIO - CARRO - MOTO desde --- Controller USUARIO
     */
    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarAllVehiculosForUser(@PathVariable("usuarioId") int usuarioId){
        Map<String, Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }


}
