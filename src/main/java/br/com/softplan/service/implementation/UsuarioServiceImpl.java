package br.com.softplan.service.implementation;

import br.com.softplan.domain.Sessao;
import br.com.softplan.domain.Usuario;
import br.com.softplan.repository.SessaoRepository;
import br.com.softplan.repository.UsuarioRepository;
import br.com.softplan.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final SessaoRepository sessaoRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, SessaoRepository sessaoRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.sessaoRepository = sessaoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Usuario criarUsuario(Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com este email.");
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenhaHash());
        usuario.setSenhaHash(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = obterUsuarioPorId(id);


        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());


        if (usuarioAtualizado.getSenhaHash() != null) {
            String senhaCriptografada = passwordEncoder.encode(usuarioAtualizado.getSenhaHash());
            usuarioExistente.setSenhaHash(senhaCriptografada);
        }

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    @Transactional
    public void deletarUsuario(Long id) {
        Usuario usuario = obterUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

    @Override
    public Usuario fazerLogin(String email, String senha) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        if (!passwordEncoder.matches(senha, usuario.getSenhaHash())) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        return usuario;
    }

    @Override
    @Transactional
    public void fazerLogout(Long usuarioId) {
        List<Sessao> sessoesDoUsuario = sessaoRepository.findByUsuarioId(usuarioId);

        sessoesDoUsuario.stream().forEach(sessaoRepository::delete);
    }

    @Override
    public Usuario obterUsuarioPorNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

}

