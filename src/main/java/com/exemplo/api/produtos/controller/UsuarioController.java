// Não sei se eu posso remover as rotas de produto do projeto, então eu deixei em um banco à parte, assim como a tabela de usuários
// está igual a do banco do projeto, mas não está sendo inserida nele.

package com.exemplo.api.produtos.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.api.produtos.model.Usuario;
import com.exemplo.api.produtos.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("")
    public List<Usuario> listar(){
        return repository.findAll();
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @PatchMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario novoUsuario) {
        return repository.findById(id).map(usuario -> {
            if(novoUsuario.getNome() != null && !novoUsuario.getNome().isBlank() && !(novoUsuario.getNome().equals(usuario.getNome()))){
                usuario.setNome(novoUsuario.getNome());
            }

            if(novoUsuario.getEmail() != null && !novoUsuario.getEmail().isBlank() && !(novoUsuario.getEmail().equals(usuario.getEmail()))){
                usuario.setEmail(novoUsuario.getEmail());
            }

            if(novoUsuario.getDataNascimento() != null && !novoUsuario.getDataNascimento().isBlank() && !(novoUsuario.getDataNascimento().equals(usuario.getDataNascimento()))){
                usuario.setDataNascimento(novoUsuario.getDataNascimento());
            }

            if(!(novoUsuario.getPontuacao() == usuario.getPontuacao())){
                usuario.setPontuacao(novoUsuario.getPontuacao());
            }

            return repository.save(usuario);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }

}