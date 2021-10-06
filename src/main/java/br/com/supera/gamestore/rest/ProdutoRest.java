package br.com.supera.gamestore.rest;

import br.com.supera.gamestore.dtos.ProdutoDTO;
import br.com.supera.gamestore.dtos.UsuarioDTO;
import br.com.supera.gamestore.models.Produto;
import br.com.supera.gamestore.models.Usuario;
import br.com.supera.gamestore.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/produtos")
@CrossOrigin("*")
public class ProdutoRest {

    private final ProdutoService produtoService;

    public ProdutoRest(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodosProdutos() {
        List<Produto> list = produtoService.listarTodosProdutos();
        List<ProdutoDTO> listDTO = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Produto obj = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto obj) {
        Produto newObj = produtoService.criarProduto(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto obj){
        Produto newObj = produtoService.atualizarProduto(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }


}
