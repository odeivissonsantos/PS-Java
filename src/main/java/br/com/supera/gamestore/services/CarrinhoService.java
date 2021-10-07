package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.CarrinhoDAO;
import br.com.supera.gamestore.dao.ItemCarrinhoDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.Produto;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    private final CarrinhoDAO carrinhoDAO;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final ItemCarrinhoDAO itemCarrinhoDAO;

    public CarrinhoService(CarrinhoDAO carrinhoDAO, ProdutoService produtoService, UsuarioService usuarioService, ItemCarrinhoDAO itemCarrinhoDAO) {
        this.carrinhoDAO = carrinhoDAO;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.itemCarrinhoDAO = itemCarrinhoDAO;
    }

    //Busca um carrinho passando por parâmetro um id.
    public Carrinho buscarCarrinhoPorId(Long carrinhoId) {
        return verificaSeExisteCarrinho(carrinhoId);
    }

    /*
        verifica existe um Carrinho na base de dados passando por parâmetro um id,
        caso contrario é lançado uma excessão
    */
    private Carrinho verificaSeExisteCarrinho(Long id) throws ObjectNotFoundException {
        return carrinhoDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Carrinho com "+ id +" não encontrado, Tipo: " + Carrinho.class.getName()));
    }

}
