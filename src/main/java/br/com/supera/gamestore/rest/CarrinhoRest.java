package br.com.supera.gamestore.rest;

import br.com.supera.gamestore.dtos.CarrinhoDTO;
import br.com.supera.gamestore.dtos.UsuarioDTO;

import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.Usuario;
import br.com.supera.gamestore.services.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
}
