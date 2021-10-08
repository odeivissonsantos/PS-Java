package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.ProdutoDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Produto;
import br.com.supera.gamestore.util.BigDecimalConverter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe utilizada para realizar o CRUD e regra de negócio para Produtos.
 */

@Service
public class ProdutoService {

    // Construtores Bigdecimal, Produto DAO e Injeção de depência JPA.
    private final ProdutoDAO produtoDAO;
    private final BigDecimalConverter bigDecimalConverter;

    public ProdutoService(ProdutoDAO produtoDAO, BigDecimalConverter bigDecimalConverter) {
        this.produtoDAO = produtoDAO;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    /*
     * @return: Retorna uma lista de produtos cadastrados na base de dados.
     */
    public List<Produto> listarTodosProdutos() {
        return (List<Produto>) produtoDAO.findAll();
    }

    /*
     * @param: id
     * @return: Busca um Produto na base de dados.
     */
    public Produto buscarProdutoPorId(Long id) {
        return verificaSeExisteProduto(id);
    }

    /*
     * @return: Retorna a criação de um Produto na base de dados.
     */
    public Produto criarProduto(Produto obj) {
        obj.setId(null);
        obj.setPreco(bigDecimalConverter.converter(obj.getPreco().toString()));
        obj = produtoDAO.save(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um produto na base de dados e atualiza suas informações
     */
    public Produto atualizarProduto(Long id, Produto obj) {
        Produto newObj = verificaSeExisteProduto(id);
        atualizaData(newObj, obj);
        return produtoDAO.save(newObj);
    }

    //Verifica se existe um Produto na base de dados e deleta.
    public void deletarProduto(Long id) {
        verificaSeExisteProduto(id);
        produtoDAO.deleteById(id);
    }

    /*
     * método com a responsabilidade de passar os novos dados de um
       produto para um produto já existente na base de dados.
     */
    private void atualizaData(Produto newObj, Produto obj) {
        newObj.setNome(obj.getNome());
        newObj.setPontuacao(obj.getPontuacao());
        newObj.setPreco(obj.getPreco());
        newObj.setUrlImagem(obj.getUrlImagem());
    }

    /*
     * @param: id
     * @return: verifica existe Produto na base de dados, caso contrario é lançado uma excessão
     */
    private Produto verificaSeExisteProduto(Long id) throws ObjectNotFoundException {
        return produtoDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com "+ id +" não encontrado, Tipo: " + Produto.class.getName()));
    }

}
