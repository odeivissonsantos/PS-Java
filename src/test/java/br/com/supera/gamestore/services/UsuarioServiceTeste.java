package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.UsuarioDAO;
import br.com.supera.gamestore.dtos.UsuarioDTO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServiceTeste {

    private static final Integer ID        = 1;
    private static final String NOMECOMPLETO       = "Deivisson Santos";
    private static final String CPF        = "66090972088";
    private static final String EMAIL      = "email@test.com";
    private static final Integer INDEX_0   = 0;
    private static final String TELEFONE      = "71991888419";

    private static final String JA_CADASTRADO_NO_SISTEMA = "já cadastrado no sistema";

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioDAO usuarioDAO;

    private Optional<Usuario> optionalUsuario;
    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        iniciaOptionalUsuario();
        iniciaUsuario();
        iniciaUsuarioDTO();
    }

    @Test
    void findByIdSuccessTest() {
        when(usuarioDAO.findById(Long.valueOf(anyInt()))).thenReturn(this.optionalUsuario);

        Usuario response = usuarioService.buscarUsuarioPorId(Long.valueOf(ID));

        if(optionalUsuario.isPresent()) {
            assertEquals(optionalUsuario.get().getId(), response.getId());
            assertEquals(optionalUsuario.get().getNomeCompleto(), response.getNomeCompleto());
            assertEquals(optionalUsuario.get().getCpf(), response.getCpf());
            assertEquals(optionalUsuario.get().getEmail(), response.getEmail());
            assertEquals(optionalUsuario.get().getTelefone(), response.getTelefone());
        }
    }

    @Test
    void findByIdErrorTest() {
        ObjectNotFoundException notFoundException = new ObjectNotFoundException("Usuario com "+ ID +" não encontrado, Tipo: " + Usuario.class.getName());
        when(usuarioDAO.findById(Long.valueOf(anyInt()))).thenThrow(notFoundException);

        try{
            usuarioService.buscarUsuarioPorId(Long.valueOf(ID));
        } catch (Exception ex) {
            assertEquals(notFoundException.getClass(), ex.getClass());
            assertEquals(notFoundException.getMessage(), ex.getMessage());
        }
    }

    @Test
    void findAllTest() {
        List<Usuario> list = List.of(usuario);

        when(usuarioDAO.findAll()).thenReturn(list);
        List<Usuario> response = usuarioService.listarTodosUsuarios();

        assertEquals(response.size(), list.size());
        assertEquals(response.get(INDEX_0).getId(), list.get(INDEX_0).getId());
        assertEquals(response.get(INDEX_0).getNomeCompleto(), list.get(INDEX_0).getNomeCompleto());
        assertEquals(response.get(INDEX_0).getCpf(), list.get(INDEX_0).getCpf());
        assertEquals(response.get(INDEX_0).getEmail(), list.get(INDEX_0).getEmail());
        assertEquals(response.get(INDEX_0).getTelefone(), list.get(INDEX_0).getTelefone());
    }

    @Test
    void createSuccessTest() {
        when(usuarioDAO.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        Usuario response = usuarioService.criarUsuario(usuario);

        assertEquals(usuario.getId(), response.getId());
        assertEquals(usuario.getNomeCompleto(), response.getNomeCompleto());
        assertEquals(usuario.getCpf(), response.getCpf());
        assertEquals(usuario.getEmail(), response.getEmail());
        assertEquals(usuario.getTelefone(), response.getTelefone());
    }

    @Test
    void updateWithSuccessTest() {
        when(usuarioDAO.findById(Long.valueOf(anyInt()))).thenReturn(optionalUsuario);
        when(usuarioDAO.save(Mockito.any())).thenReturn(usuario);

        Usuario response = usuarioService.atualizarUsuario(Long.valueOf(ID), usuario);

        assertEquals(usuario.getId(), response.getId());
        assertEquals(usuario.getNomeCompleto(), response.getNomeCompleto());
        assertEquals(usuario.getCpf(), response.getCpf());
        assertEquals(usuario.getEmail(), response.getEmail());
        assertEquals(usuario.getTelefone(), response.getTelefone());
    }

    @Test
    void updateWithObjectNotFoundErrorTest() {
        try {
            usuarioService.atualizarUsuario(Long.valueOf(ID), usuario);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void deleteByIdSuccessTest() {
        doNothing().when(usuarioDAO).deleteById(Long.valueOf(anyInt()));
        usuarioService.deletarUsuario(Long.valueOf(ID));

    }

    private void iniciaOptionalUsuario() {
        optionalUsuario = Optional.of(new Usuario());

        optionalUsuario.get().setId(Long.valueOf(ID));
        optionalUsuario.get().setNomeCompleto(NOMECOMPLETO);
        optionalUsuario.get().setCpf(CPF);
        optionalUsuario.get().setEmail(EMAIL);
        optionalUsuario.get().setTelefone(TELEFONE);
    }

    private void iniciaUsuarioDTO() {
        usuario = new Usuario();
        usuario.setId(Long.valueOf(ID));
        usuario.setNomeCompleto(NOMECOMPLETO);
        usuario.setCpf(CPF);
        usuario.setEmail(EMAIL);
        usuario.setTelefone(TELEFONE);
    }

    private void iniciaUsuario() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO = new UsuarioDTO(usuario);
    }


}
