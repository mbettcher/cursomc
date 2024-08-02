package br.com.cdm.cursomc;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cdm.cursomc.domain.Categoria;
import br.com.cdm.cursomc.domain.Produto;
import br.com.cdm.cursomc.repositories.CategoriaRepository;
import br.com.cdm.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

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
	}

}
