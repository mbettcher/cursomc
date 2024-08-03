package br.com.cdm.cursomc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cdm.cursomc.domain.Categoria;
import br.com.cdm.cursomc.domain.Cidade;
import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.domain.Endereco;
import br.com.cdm.cursomc.domain.Estado;
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
import br.com.cdm.cursomc.repositories.PagamentoRepository;
import br.com.cdm.cursomc.repositories.PedidoRepository;
import br.com.cdm.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*Instancia duas categorias*/
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		/*Instancia três produtos*/
		Produto p1 = new Produto(null, "Computador", BigDecimal.valueOf(2000.00));
		Produto p2 = new Produto(null, "Impressora", BigDecimal.valueOf(800.00));
		Produto p3 = new Produto(null, "Mouse", BigDecimal.valueOf(80.00));
		
		/*Fazerndo as associações - Adiciona os produtos à categorias*/
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		/*Fazerndo as associações - Adiciona as categorias aos produtos*/
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		/*Salvando categorias e produtos*/
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
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
	}

}
