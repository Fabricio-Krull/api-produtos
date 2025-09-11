package com.exemplo.api.produtos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.api.produtos.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNome(String nome);
    List<Usuario> findByNomeContaining(String ParteDoNome);
}
