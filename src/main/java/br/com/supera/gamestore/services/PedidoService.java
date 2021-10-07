package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.CarrinhoDAO;
import br.com.supera.gamestore.dao.PedidoDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoDAO pedidoDAO;
    private final ProdutoService produtoService;
    private final CarrinhoDAO itemCarrinhoDAO;
    private final UsuarioService usuarioService;


    public PedidoService(PedidoDAO pedidoDAO,
                         ProdutoService produtoService,
                         CarrinhoDAO itemCarrinhoDAO,
                         UsuarioService usuarioService) {
        this.pedidoDAO = pedidoDAO;
        this.produtoService = produtoService;
        this.itemCarrinhoDAO = itemCarrinhoDAO;
        this.usuarioService = usuarioService;
    }

    public List<Pedido> listarTodosPedidos() {
        return pedidoDAO.findAll();
    }

    public Pedido criarPedido(Pedido obj) {
        Double freteFixo = 10.0;
        obj.setId(null);
        obj.setDataPedido(LocalDate.now());
        obj.setUsuario(usuarioService.buscarUsuarioPorId(obj.getUsuario().getId()));

        obj = pedidoDAO.save(obj);

        for (Carrinho ic : obj.getItens()) {
           // ic.setProduto(produtoService.buscarProdutoPorId(ic.getProduto().getId()));
           // ic.setPreco(ic.getProduto().getPreco());
            //ic.setQuantidade(ic.getQuantidade().getQuantidade());

            if(ic.getQuantidade() % 1 == 0) {
                obj.setFrete(BigDecimal.valueOf((ic.getQuantidade() * freteFixo)));
            }
        }



        itemCarrinhoDAO.saveAll(obj.getItens());



        return obj;
    }

    /*
        verifica existe um Pedido na base de dados passando por parâmetro um id,
        caso contrario é lançado uma excessão
    */
    private Pedido verificaSeExistePedido(Long id) throws ObjectNotFoundException {
        return pedidoDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com "+ id +" não encontrado, Tipo: " + Pedido.class.getName()));
    }



}
