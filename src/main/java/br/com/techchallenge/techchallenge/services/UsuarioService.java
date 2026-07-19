package br.com.techchallenge.techchallenge.services;

import br.com.techchallenge.techchallenge.dtos.UsuarioRequestDTO;
import br.com.techchallenge.techchallenge.entities.Usuario;
import br.com.techchallenge.techchallenge.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsuarios(int page, int size) {
        int offset = (page - 1) * size;
        return this.usuarioRepository.findAll(size, offset);
    }

    public Optional<Usuario> findUsuarioById(Long id){
        return this.usuarioRepository.findById(id);
    }

    public void saveUsuario(UsuarioRequestDTO requestDTO) {
        Usuario usuarioEntity = new Usuario(requestDTO);
        var save = this.usuarioRepository.save(usuarioEntity);
        Assert.state(save == 1, "Erro ao salvar usuário: " + requestDTO.nome());
    }

    public void updateUsuario(Usuario usuario, Long id){
        var update = this.usuarioRepository.update(usuario, id);
        if (update == 0) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public void deleteUsuario(Long id){
        var delete = this.usuarioRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Usuário nao encontrado");
        }
    }



}
