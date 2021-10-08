package br.com.supera.gamestore.rest;

import br.com.supera.gamestore.dtos.CarrinhoDTO;

import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.services.CarrinhoService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
@RequestMapping("/api/v1/carrinhos")
@CrossOrigin("*")
public class CarrinhoRest {

    private final CarrinhoService carrinhoService;

    public CarrinhoRest(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<List<CarrinhoDTO>> listarTodosUsuarios() {
        List<Carrinho> list = carrinhoService.listarTodosCarrinhos();
        List<CarrinhoDTO> listDTO = list.stream().map(obj -> new CarrinhoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> buscarCarrinhoPorId(@PathVariable Long id){
        Carrinho obj = carrinhoService.buscarCarrinhoPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Carrinho> criarCarrinho(@RequestBody Carrinho obj) {
        Carrinho newObj = carrinhoService.criarCarrinho(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/carrinhos/{id}").buildAndExpand(newObj.getCarrinhoId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        carrinhoService.deletarCarrinho(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrinho> atualizarCarrinho(@PathVariable Long id, @RequestBody Carrinho obj) {
        Carrinho newObj = carrinhoService.atualizaCarrinho(id, obj);
        return ResponseEntity.ok().body(newObj);
    }
}
