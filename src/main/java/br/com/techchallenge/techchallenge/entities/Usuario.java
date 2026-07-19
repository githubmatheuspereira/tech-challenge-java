package br.com.techchallenge.techchallenge.entities;

import br.com.techchallenge.techchallenge.dtos.UsuarioRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Usuario {

    private Long id_usuario;
    private String nome;
    private String email;
    private String usuarioLogin;
    private String senha;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_ultima_alteracao;
    private String endereco;
    private String tipoUsuario;

    public Usuario(UsuarioRequestDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.usuarioLogin = dto.usuarioLogin();
        this.senha = dto.senha();
        this.endereco = dto.endereco();
        this.tipoUsuario = dto.tipoUsuario();
    }

}