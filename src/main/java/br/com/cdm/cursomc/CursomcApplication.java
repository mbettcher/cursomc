package br.com.cdm.cursomc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cdm.cursomc.domain.Categoria;
import br.com.cdm.cursomc.domain.Cidade;
import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.domain.Endereco;
import br.com.cdm.cursomc.domain.Estado;
import br.com.cdm.cursomc.domain.ItemPedido;
import br.com.cdm.cursomc.domain.Pagamento;
import br.com.cdm.cursomc.domain.PagamentoComBoleto;
import br.com.cdm.cursomc.domain.PagamentoComCartao;
import br.com.cdm.cursomc.domain.Pedido;
import br.com.cdm.cursomc.domain.Produto;
import br.com.cdm.cursomc.domain.enums.EstadoPagamento;
import br.com.cdm.cursomc.domain.enums.TipoCliente;
import br.com.cdm.cursomc.repositories.CategoriaRepository;
import br.com.cdm.cursomc.repositories.CidadeRepository;
import br.com.cdm.cursomc.repositories.ClienteRepository;
import br.com.cdm.cursomc.repositories.EnderecoRepository;
import br.com.cdm.cursomc.repositories.EstadoRepository;
import br.com.cdm.cursomc.repositories.ItemPedidoRepository;
import br.com.cdm.cursomc.repositories.PagamentoRepository;
import br.com.cdm.cursomc.repositories.PedidoRepository;
import br.com.cdm.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	private CategoriaRepository categoriaRepository;

	private ProdutoRepository produtoRepository;

	private EstadoRepository estadoRepository;

	private CidadeRepository cidadeRepository;

	private ClienteRepository clienteRepository;

	private EnderecoRepository enderecoRepository;

	private PedidoRepository pedidoRepository;

	private PagamentoRepository pagamentoRepository;
	
	private ItemPedidoRepository itemPedidoRepository;
	
	public CursomcApplication(
			CategoriaRepository categoriaRepository,
			ProdutoRepository produtoRepository,
			EstadoRepository estadoRepository,
			CidadeRepository cidadeRepository,
			ClienteRepository clienteRepository,
			EnderecoRepository enderecoRepository,
			PedidoRepository pedidoRepository,
			PagamentoRepository pagamentoRepository,
			ItemPedidoRepository itemPedidoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.estadoRepository = estadoRepository;
		this.cidadeRepository = cidadeRepository;
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.pedidoRepository = pedidoRepository;
		this.pagamentoRepository = pagamentoRepository;
		this.itemPedidoRepository = itemPedidoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*Instancia duas categorias*/
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		/*Instancia três produtos*/
		Produto p1 = new Produto(null, "Computador", BigDecimal.valueOf(2000.00));
		Produto p2 = new Produto(null, "Impressora", BigDecimal.valueOf(800.00));
		Produto p3 = new Produto(null, "Mouse", BigDecimal.valueOf(80.00));
		Produto p4 = new Produto(null, "Mesa de Escritório", BigDecimal.valueOf(300.00));
		Produto p5 = new Produto(null, "Toalha", BigDecimal.valueOf(50.00));
		Produto p6 = new Produto(null, "Colcha", BigDecimal.valueOf(200.00));
		Produto p7 = new Produto(null, "TV True Color", BigDecimal.valueOf(1200.00));
		Produto p8 = new Produto(null, "Roçadeira", BigDecimal.valueOf(800.00));
		Produto p9 = new Produto(null, "Abajour", BigDecimal.valueOf(100.00));
		Produto p10 = new Produto(null, "Pendente", BigDecimal.valueOf(180.00));
		Produto p11 = new Produto(null, "Shampoo", BigDecimal.valueOf(90.00));
		
		/*Fazerndo as associações - Adiciona os produtos à categorias*/
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		/*Fazerndo as associações - Adiciona as categorias aos produtos*/
		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		/*Salvando categorias e produtos*/
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		/* --------------------------------------------------------------------------------------- */
		
		/*Instancia dois Estados*/
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		/*Instancia três cidades*/
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		/*Fazendo a associação entre Estado e suas cidades*/
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		/*Salvando Estados e Cidades*/
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		/* --------------------------------------------------------------------------------------- */
		/*Instancia um cliente*/
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		/*Fazendo a associação entre telefones e cliente*/
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		/*Instancia dois enderecos do cliente*/
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		/* Fazendo a associação entre cliente e endereco */
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		/* Salvando os Clientes e Enderecos*/
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		/* --------------------------------------------------------------------------------------- */
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		/* Intancia dois pedidos */
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		/* Instancia pagamentos */
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1); //Setando o pagamento pedido 1
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, 
				ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2); //Setando o pagamento pedido 2
		
		/* Fazendo a associação entre cliente e pedidos */
		cli1.getPedido().addAll(Arrays.asList(ped1, ped2));
		
		/* Salvando os Cliente, Pedidos e Pagamentos */
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		/* --------------------------------------------------------------------------------------- */
		
		/* Intancia três item pedido */
		ItemPedido ip1 = new ItemPedido(ped1, p1, BigDecimal.valueOf(0.00), 1, BigDecimal.valueOf(2000.00));
		ItemPedido ip2 = new ItemPedido(ped1, p3, BigDecimal.valueOf(0.00), 2, BigDecimal.valueOf(80.00));
		ItemPedido ip3 = new ItemPedido(ped2, p2, BigDecimal.valueOf(100.00), 1, BigDecimal.valueOf(800.00));
		
		/* Fazendo a associação de Pedidos aos ítens */
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		/* Fazendo a associação de Produtos aos ítens */
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		/* Salvando os itens de pedido */
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
	}

}
