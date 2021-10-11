package br.com.supera.gamestore.rest;

import br.com.supera.gamestore.dtos.UsuarioDTO;
import br.com.supera.gamestore.models.Usuario;
import br.com.supera.gamestore.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe responsável por gerar os end points, protocolo http e resposta do status da requisição
 */

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin("*")
public class UsuarioRest {

    private final UsuarioService usuarioService;

    public UsuarioRest(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(description = "Lista todos os usuários cadastrados na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista com todos usuários")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodosUsuarios() {
        List<Usuario> list = usuarioService.listarTodosUsuarios();
        List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @Operation(description = "Retorna um usuário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista os detalhes de um usuário por ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id){
        Usuario obj = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(description = "Insere um novo usuário na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cria um novo usuário")
    })
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario obj) {
        Usuario newObj= usuarioService.criarUsuario(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @Operation(description = "Atualiza um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atualiza um usuário cadastrado base de dados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario obj){
        Usuario newObj = usuarioService.atualizarUsuario(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @Operation(description = "Exclui um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Exclui um usuário passando uum id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
