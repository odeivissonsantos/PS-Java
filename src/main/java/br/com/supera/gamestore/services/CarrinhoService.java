package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.CarrinhoDAO;
import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoDAO carrinhoDAO;
    private final ProdutoService produtoService;

    public CarrinhoService(CarrinhoDAO carrinhoDAO, ProdutoService produtoService) {
        this.carrinhoDAO = carrinhoDAO;
        this.produtoService = produtoService;
    }

}
