package com.example1.usuarioservice.feignclients;


import com.example1.usuarioservice.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8192")
@RequestMapping("/moto")
public interface MotoFeignClient {

    @PostMapping
    public Moto save(@RequestBody Moto motos);

    @GetMapping("/usuario/{usuarioId}")
    public List <Moto> getMotos(@PathVariable("usuarioId") int usuarioId);


}
