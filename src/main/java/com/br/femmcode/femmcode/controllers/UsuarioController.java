package com.br.femmcode.femmcode.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.femmcode.femmcode.models.UsuarioModel;
import com.br.femmcode.femmcode.repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/")

public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    //---------------------- CADASTRAR O USUÁRIO ----------------------
    @PostMapping("/usuarios") //postando usuário no banco
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario) {
        UsuarioModel obj = repository.save(usuario);
        return ResponseEntity.ok(obj);
    }

     //---------------------- BUSCAR O USUÁRIO ----------------------
    @PostMapping("/usuario")//pegando usuário do banco
    public ResponseEntity<UsuarioModel> obter(@RequestBody UsuarioModel usuario) {
        UsuarioModel obj = repository.findByEmail(usuario.getEmail());
        return ResponseEntity.ok(obj);
    }
    
     //---------------------- LOGIN ----------------------
    @PostMapping("/login")
    public ResponseEntity<UsuarioModel> login(@RequestBody UsuarioModel usuario) {
        UsuarioModel obj = repository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
        if(obj==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(obj);
    }
    

}
