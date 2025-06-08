package com.br.femmcode.femmcode.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.femmcode.femmcode.models.UsuarioModel;


public interface UsuarioRepository extends MongoRepository<UsuarioModel,String>{
    UsuarioModel findByEmail(String email);
    UsuarioModel findByEmailAndSenha(String email, String senha);
}
