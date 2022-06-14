package com.goortis.pedidos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import com.goortis.pedidos.domain.Arquivo;
import com.goortis.pedidos.domain.Pedido;
import com.goortis.pedidos.domain.PedidoBuilder;
import com.goortis.pedidos.repository.ArquivoRepository;
import com.goortis.pedidos.repository.PedidoRepository;
import com.goortis.pedidos.services.ArquivoService;
import com.goortis.pedidos.services.PedidoService;
import com.goortis.pedidos.services.exceptions.ArquivoComMaisDeDezPedidosException;
import com.goortis.pedidos.services.exceptions.PedidoNaoEncontradoException;
import com.goortis.pedidos.services.exceptions.PedidoSemItemsException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PedidosApplication.class)
@WebAppConfiguration
public class PedidoApplicationTests {

    @InjectMocks
    private PedidoService pedidoService;

    @InjectMocks
    private ArquivoService arquivoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ArquivoRepository arquvioRepository;

    @Before
    public void setup() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void teste_calcula_valor_total_sem_desconto() {
	Pedido pedidoVenda = new PedidoBuilder().comCliente("Gabriel Um").comItem("Item 1", 2, "30")
		.comItem("Item 1", 3, "20").construir();
	assertEquals(new Double(120), pedidoVenda.getTotal());
    }

    @Test
    public void teste_calcula_valor_com_5_por_cento_desconto() {
	Pedido pedidoVenda = new PedidoBuilder().comCliente("Gabriel Dois").comItem("Item 5", 7, "10")
		.comItem("Item 4", 8, "30").construir();
	assertEquals(new Double(294.50), pedidoVenda.getTotal());
    }

    @Test
    public void teste_calcula_valor_com_10_por_cento_desconto() {
	Pedido pedidoVenda = new PedidoBuilder().comCliente("Gabriel Tres").comItem("Item 8", 11, "10")
		.comItem("Item 7", 12, "30").construir();
	assertEquals(new Double(423), pedidoVenda.getTotal());
    }

    @Test
    public void teste_quantidade_igual_a_zero() {
	Pedido pedidoVenda = new PedidoBuilder().comCliente("Gabriel Tres").comItem("Item 8", 0, "10").construir();
	assertEquals(new Integer(1), pedidoVenda.getItemsPedidos().get(0).getQuantidade());
    }

    @Test
    public void teste_sem_data_de_cadastro() {
	Pedido pedidoVenda = new PedidoBuilder().comCliente("Gabriel Tres").comItem("Item 8", 0, "10").construir();
	assertEquals(new Date(), pedidoVenda.getDataCadastro());
    }

    @Test(expected = ArquivoComMaisDeDezPedidosException.class)
    public void teste_arquivo_com_mais_de_10_pedidos_exception() {
	List<Pedido> pedidos = CriarOnzePedidos();
	Arquivo arquivo = new Arquivo();
	arquivo.setPedidos(pedidos);
	arquivo.setNome("Arquivo001");
	arquivoService.salvar(arquivo);
    }

    @Test(expected = PedidoSemItemsException.class)
    public void teste_pedido_sem_items_exception() {
	Pedido pedido1;
	pedido1 = new Pedido();
	pedido1.setCodPedido(1001L);
	pedido1.setItemsPedidos(new ArrayList());
	pedidoService.salvar(pedido1);

    }

    @Test(expected = PedidoNaoEncontradoException.class)
    public void teste_pedido_nao_encontrado_exception() {
	Pedido pedido1 = pedidoService.buscar(999L);

    }

    private List<Pedido> CriarOnzePedidos() {
	List<Pedido> pedidos = new ArrayList<Pedido>();
	Pedido pedido1, pedido2, pedido3, pedido4, pedido5, pedido6, pedido7, pedido8, pedido9, pedido10, pedido11;
	pedido1 = new PedidoBuilder().comCliente("Cliente 1").comItem("Item 1", 1, "10").construir();
	pedido2 = new PedidoBuilder().comCliente("Cliente 2").comItem("Item 2", 2, "20").construir();
	pedido3 = new PedidoBuilder().comCliente("Cliente 3").comItem("Item 3", 3, "30").construir();
	pedido4 = new PedidoBuilder().comCliente("Cliente 4").comItem("Item 4", 4, "40").construir();
	pedido5 = new PedidoBuilder().comCliente("Cliente 5").comItem("Item 5", 5, "50").construir();
	pedido6 = new PedidoBuilder().comCliente("Cliente 6").comItem("Item 6", 6, "60").construir();
	pedido7 = new PedidoBuilder().comCliente("Cliente 7").comItem("Item 7", 7, "70").construir();
	pedido8 = new PedidoBuilder().comCliente("Cliente 8").comItem("Item 8", 8, "80").construir();
	pedido9 = new PedidoBuilder().comCliente("Cliente 9").comItem("Item 9", 9, "90").construir();
	pedido10 = new PedidoBuilder().comCliente("Cliente 10").comItem("Item 10", 10, "100").construir();
	pedido11 = new PedidoBuilder().comCliente("Cliente 11").comItem("Item 11", 11, "110").construir();
	pedidos.add(pedido1);
	pedidos.add(pedido2);
	pedidos.add(pedido3);
	pedidos.add(pedido4);
	pedidos.add(pedido5);
	pedidos.add(pedido6);
	pedidos.add(pedido7);
	pedidos.add(pedido8);
	pedidos.add(pedido9);
	pedidos.add(pedido10);
	pedidos.add(pedido11);
	return pedidos;
    }

}
