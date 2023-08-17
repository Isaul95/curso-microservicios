package com.example1.usuarioservice.repositorio;

import com.example1.usuarioservice.entitys.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuarios, Integer> {


}
