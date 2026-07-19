package br.com.techchallenge.techchallenge.dtos;

import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotNull(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Email é obrigatório")
        String email,
        @NotNull(message = "Login é obrigatório")
        String usuarioLogin,
        @NotNull(message = "Senha é obrigatória")
        String senha,
        @NotNull(message = "Endereço é obrigatório")
        String endereco,
        @NotNull(message = "Tipo de usuário é obrigatório")
        String tipoUsuario
) {
}
