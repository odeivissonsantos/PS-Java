package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.CarrinhoDAO;
import br.com.supera.gamestore.dao.ItemCarrinhoDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.ItemCarrinho;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe utilizada para realizar o CRUD e regra de negócio para Carrinho.
 */


@Service
public class CarrinhoService {

    // Construtores e Injeção de depência JPA.
    private final CarrinhoDAO carrinhoDAO;
    private final ProdutoService produtoService;
    private final ItemCarrinhoDAO itemCarrinhoDAO;
    private final UsuarioService usuarioService;

    public CarrinhoService(CarrinhoDAO carrinhoDAO, ProdutoService produtoService, ItemCarrinhoDAO itemCarrinhoDAO, UsuarioService usuarioService) {
        this.carrinhoDAO = carrinhoDAO;
        this.produtoService = produtoService;
        this.itemCarrinhoDAO = itemCarrinhoDAO;
        this.usuarioService = usuarioService;
    }

    /*
     * @param: id
     * @return: Busca um Carrinho na base de dados.
     */
    public Carrinho buscarCarrinhoPorId(Long carrinhoId) {
        return verificaSeExisteCarrinho(carrinhoId);
    }

    /*
     * @return: Retorna uma lista de Carrinho cadastrados na base de dados.
     */
    public List<Carrinho> listarTodosCarrinhos() {
        return carrinhoDAO.findAll();
    }

    /*
     * @return: Retorna a criação de um Carrinho na base de dados.
     */
    public Carrinho criarCarrinho(Carrinho obj) {
        obj.setCarrinhoId(null);
        //obj.setUsuario(usuarioService.buscarUsuarioPorId(obj.getUsuario().getId()));
        //obj = carrinhoDAO.save(obj);

        //Percorre o array de itens busca os preços e os id's dos produtos
        /*for (ItemCarrinho ic : obj.getItens()) {
            ic.setProduto(produtoService.buscarProdutoPorId(ic.getProduto().getId()));
            ic.setPrecoUnitario(ic.getProduto().getPreco());
            ic.setCarrinho(obj);
        }
        itemCarrinhoDAO.saveAll(obj.getItens());
        */
        this.calculaCarrinho(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um Carrinho na base de dados,
                acrescenta ou decrementa itens do carrinho
     */
    public Carrinho atualizaCarrinho(Long id,  Carrinho obj) {
        this.verificaSeExisteCarrinho(id);
        obj.setCarrinhoId(id);
        this.calculaCarrinho(obj);
        return carrinhoDAO.save(obj);
    }

    //Verifica se existe um carrinho na base de dados e deleta.
    public void deletarCarrinho(Long id) {
        this.verificaSeExisteCarrinho(id);
        carrinhoDAO.deleteById(id);
    }

    /*
    Método responsavel por realizar o calculo do carrinho
     */
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
     * @param: id
     * @return: verifica existe Carrinho na base de dados, caso contrario é lançado uma excessão
     */
    private Carrinho verificaSeExisteCarrinho(Long id) throws ObjectNotFoundException {
        return carrinhoDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Carrinho com id: " + id + " não encontrado, Tipo: " + Carrinho.class.getName()));
    }

}
