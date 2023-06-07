package com.br.usuarioatividade.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 11)
    private String fone;

    @Column(nullable = false, length = 6)
    private String senha;

    @Column(nullable = false, length = 130)
    private String nome;

    @Column(nullable = false, unique = true, length = 130)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;



}
