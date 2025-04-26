package com.br.femmcode.femmcode.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.femmcode.femmcode.models.Usuario;


public interface UsuarioRepository extends MongoRepository<Usuario,String>{
    
}
