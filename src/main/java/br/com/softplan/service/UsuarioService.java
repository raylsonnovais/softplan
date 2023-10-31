package br.com.softplan.service;

import br.com.softplan.domain.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    @Transactional
    Usuario criarUsuario(Usuario usuario);
    @Transactional
    Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado);
    @Transactional
    void deletarUsuario(Long id);
    List<Usuario> listarUsuarios();
    Usuario obterUsuarioPorId(Long id);
    Usuario fazerLogin(String email, String senha);
    void fazerLogout(Long usuarioId);
    Usuario obterUsuarioPorNome(String nome);
}
