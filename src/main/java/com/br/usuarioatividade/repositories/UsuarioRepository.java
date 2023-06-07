package com.br.usuarioatividade.repositories;

import com.br.usuarioatividade.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    boolean existsByFone(String fone);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);


}
