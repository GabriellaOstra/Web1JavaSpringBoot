package com.br.usuarioatividade.controllers;

import com.br.usuarioatividade.dtos.UsuarioDto;
import com.br.usuarioatividade.models.UsuarioModel;
import com.br.usuarioatividade.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario-atividade")
public class UsuarioController {

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //recebe dados de entrada e verificando
    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioDto usuarioDto){

        if(usuarioService.existsByFone(usuarioDto.getFone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Atenção: esse número já está cadastrado!");
        }
        if(usuarioService.existsByEmail(usuarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Atenção: esse email já está cadastrado!");
        }
        if(usuarioService.existsByCpf(usuarioDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Atenção: esse CPF já está cadastrado!");
        }

        var usuarioModel  = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }

    //altera dados do usuario passado o id
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid UsuarioDto usuarioDto){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if (!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id usuário não encontrado.");
        }
        var usuarioModel = usuarioModelOptional.get();
        usuarioModel.setFone(usuarioDto.getFone());
        usuarioModel.setSenha(usuarioDto.getSenha());
        usuarioModel.setNome(usuarioDto.getNome());
        usuarioModel.setEmail(usuarioDto.getEmail());
        usuarioModel.setCpf(usuarioDto.getCpf());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModel));
    }

    //lista todos os registros do BD
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAllUsuario(){
        return  ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    //consulta um usuario especifico no BD passado o id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário com esse id");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());

    }





}
