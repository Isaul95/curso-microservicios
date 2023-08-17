package com.example1.usuarioservice.service;

import com.example1.usuarioservice.entitys.Usuarios;
import com.example1.usuarioservice.feignclients.CarroFeignClient;
import com.example1.usuarioservice.feignclients.MotoFeignClient;
import com.example1.usuarioservice.modelos.Carros;
import com.example1.usuarioservice.modelos.Moto;
import com.example1.usuarioservice.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate; // Con el restemplate hacemos la conexion del MICROSERVICIO Carro para poder llamar la URL
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroFeignClient carroFeignClient; // Con el FeignClient se hace la conexion del microservicio carro
    @Autowired
    private MotoFeignClient motoFeignClient; // Con el FeignClient se hace la conexion del microservicio moto


    /** ----------------------------------------------     Rest Template     -------------------------------------------

     * Desde Proyecto USER mandamos a llamar el microservicios de CARRO hacemos la conexion con el restTemplate, haciendo el consumo de todos los carros por el usuario

     * Es similar del restemplate a como se hace el consumo de un web service REST, indicandole la uri del servicio
     */
    public List<Carros> getCarrosByIdUser(int usuarioId){
        List<Carros> carrosList = restTemplate.getForObject("http://localhost:8191/carros/usuarios/" + usuarioId, List.class);
        return carrosList;
    }

    public List<Moto> getMotosByIdUser(int usuarioId){
        List<Moto> motosList = restTemplate.getForObject("http://localhost:8192/moto/usuarios/" + usuarioId, List.class);
        return motosList;
    }


    /** ----------------------------------------------     Feign Client     -------------------------------------------
     *
     *Esto lo que hace es hacer un enlace con la interface al otro proyecto Microservicio describiendo la URL y el nombre del controller
     */

    public Carros saveCarro(int usuarioId, Carros carro){
        carro.setUsuarioId(usuarioId);
        Carros nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    // SAVE MOTOOO
    public Moto saveMoto(int usuarioId, Moto Moto){
        Moto.setUsuarioId(usuarioId);
        Moto nuevoMoto = motoFeignClient.save(Moto);
        return nuevoMoto;
    }


    /** Uso de MAP java para obtener usuario - vehiculos  */
    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        Usuarios usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario == null){
            resultado.put("Mensaje", "El usuario no Existe");
            return resultado;
        }

        resultado.put("Usuario",usuario);
        List<Carros> carros = carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()){
            resultado.put("Carros","El usuario NO tiene autos asignados...!");
        }else{
            resultado.put("Carros",carros);
        }
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()){
            resultado.put("Motos","El usuario NO tiene autos asignados...!");
        }else{
            resultado.put("Motos",motos);
        }

        return resultado;
    }





    public List<Usuarios> getAllUser(){
        return usuarioRepository.findAll();
    }
    public Usuarios getUserById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuarios save(Usuarios datos){
        Usuarios newUser = usuarioRepository.save(datos);
        return newUser;
    }

}
