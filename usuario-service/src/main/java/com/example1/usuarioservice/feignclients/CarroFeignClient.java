package com.example1.usuarioservice.feignclients;

import com.example1.usuarioservice.modelos.Carros;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "carro-service", url = "http://localhost:8191")
@RequestMapping("/carros")
public interface CarroFeignClient {

    @PostMapping
    public Carros save(@RequestBody Carros carros);

    @GetMapping("/usuario/{usuarioId}")
    public List<Carros> getCarros(@PathVariable("usuarioId") int usuarioId);

}
