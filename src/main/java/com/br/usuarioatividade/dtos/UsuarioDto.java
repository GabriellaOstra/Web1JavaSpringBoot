package com.br.usuarioatividade.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UsuarioDto {

    @NotBlank
    @Size(max = 11)
    private String fone;
    @NotBlank
    @Size(max = 6)
    private String senha;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 11)
    private String cpf;

}
