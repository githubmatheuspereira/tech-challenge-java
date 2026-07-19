package br.com.techchallenge.techchallenge.repositories;

import br.com.techchallenge.techchallenge.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {

    private final JdbcClient jdbcClient;
    public UsuarioRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.jdbcClient
            .sql("""
                    SELECT * FROM usuario WHERE id_usuario = :id
                    """
            )
            .param("id", id)
            .query(Usuario.class)
            .optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient
            .sql("""
                    SELECT * FROM usuario LIMIT :size OFFSET :offset
                    """
            )
            .param("size", size)
            .param("offset", offset)
            .query(Usuario.class)
            .list();
    }

    @Override
    public Integer save(Usuario usuario) {
        return this.jdbcClient
            .sql("""
                    INSERT INTO usuario (nome, email, usuario_login, senha, endereco, tipo_usuario)
                    VALUES (:nome, :email, :usuario_login, :senha, :endereco, :tipo_usuario)
                    """)
            .param("nome", usuario.getNome())
            .param("email", usuario.getEmail())
            .param("usuario_login", usuario.getUsuarioLogin())
            .param("senha", usuario.getSenha())
            .param("endereco", usuario.getEndereco())
            .param("tipo_usuario", usuario.getTipoUsuario())
            .update();
    }

    @Override
    public Integer update(Usuario usuario, Long id) {
        return this.jdbcClient
            .sql("""
                    UPDATE usuario SET
                    nome = :nome,
                    email = :email,
                    usuario_login = :usuario_login,
                    senha = :senha,
                    endereco = :endereco,
                    tipo_usuario = :tipo_usuario
                    WHERE id_usuario = :id
                    """
            )
            .param("id", id)
            .param("nome", usuario.getNome())
            .param("email", usuario.getEmail())
            .param("usuario_login", usuario.getUsuarioLogin())
            .param("senha", usuario.getSenha())
            .param("endereco", usuario.getEndereco())
            .param("tipo_usuario", usuario.getTipoUsuario())
            .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("""
                        DELETE FROM usuario WHERE id_usuario = :id
                        """)
                .param("id", id)
                .update();
    }
}
