package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.CarrinhoDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Carrinho;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class CarrinhoService {

    private final CarrinhoDAO carrinhoDAO;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    public CarrinhoService(CarrinhoDAO carrinhoDAO, UsuarioService usuarioService, ProdutoService produtoService) {
        this.carrinhoDAO = carrinhoDAO;
        this.usuarioService = usuarioService;
        this.produtoService = produtoService;
    }

    //Busca um carrinho passando por parâmetro um id.
    public Carrinho buscarCarrinhoPorId(Long carrinhoId) {
        return verificaSeExisteCarrinho(carrinhoId);
    }

    // Busca todas os carrinhos
    public List<Carrinho> listarTodosCarrinhos() {
        return carrinhoDAO.findAll();
    }

    // Criar um novo Carrinho;
    public Carrinho criarCarrinho(Long id_usu, Carrinho obj) {
        obj.setCarrinhoId(null);
        obj.setUsuario(usuarioService.buscarUsuarioPorId(id_usu));
        this.calculaCarrinho(obj);
        return carrinhoDAO.save(obj);
    }

    //atualiza o carrinho, tanto incrementa como decrementa os itens.
    public Carrinho atualizaCarrinho(Long id,  Carrinho obj) {
        this.verificaSeExisteCarrinho(id);
        obj.setCarrinhoId(id);
        this.calculaCarrinho(obj);
        return carrinhoDAO.save(obj);
    }

    //deleta todo carrinho
    public void deletarCarrinho(Long id) {
        this.verificaSeExisteCarrinho(id);
        carrinhoDAO.deleteById(id);
    }

    private void calculaCarrinho(Carrinho carrinho) {
        carrinho.getItens().stream().forEach(obj -> {

            /*
            Verifica os itens do carrinho com o parâmetro preço e compara se o frete vai ser gratis;
             */
            if (obj.getProduto().getPreco().compareTo(new BigDecimal(250.0)) < 0) {
                carrinho.setFrete(carrinho.getFrete().add(BigDecimal.TEN));
            }

            // soma o valor dos produtos
            carrinho.setSubTotal(carrinho.getSubTotal().add(obj.getProduto().getPreco()));

            // coloca o valor atual do produto
            obj.setPrecoUnitario(obj.getProduto().getPreco());
        });

        // soma o valor do frete com o valor da soma dos produtos
        carrinho.setTotal(carrinho.getTotal().add(carrinho.getSubTotal()).add(carrinho.getFrete()));
    }

    /*
        verifica existe um Carrinho na base de dados passando por parâmetro um id,
        caso contrario é lançado uma excessão
    */
    private Carrinho verificaSeExisteCarrinho(Long id) throws ObjectNotFoundException {
        return carrinhoDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Carrinho com id: " + id + " não encontrado, Tipo: " + Carrinho.class.getName()));
    }

}
