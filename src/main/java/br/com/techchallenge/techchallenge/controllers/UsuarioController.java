package br.com.techchallenge.techchallenge.controllers;

import br.com.techchallenge.techchallenge.dtos.UsuarioRequestDTO;
import br.com.techchallenge.techchallenge.entities.Usuario;
import br.com.techchallenge.techchallenge.services.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("Acessado o endpoint /usuarios");
        var usuarios = this.usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<Optional<Usuario>> findUsuarioById(
            @PathVariable("id_usuario") Long id
    ) {
        logger.info("/usuarios", id);
        var usuarios = this.usuarioService.findUsuarioById(id);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(
            @Valid @RequestBody UsuarioRequestDTO usuarioDTO
    ) {
        logger.info("POST -> /usuarios");
        this.usuarioService.saveUsuario(usuarioDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id_usuario}")
    public ResponseEntity<Void> updateUsuario(
            @PathVariable("id_usuario") Long id,
            @RequestBody Usuario usuario
    ) {
        logger.info("PUT -> /usuarios");
        this.usuarioService.updateUsuario(usuario, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<Void> deleteUsuario(
            @PathVariable("id_usuario") Long id
    ) {
        logger.info("DELETE -> /usuarios");
        this.usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }
}
