package com.davidsabino.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.davidsabino.cursomc.domain.Categoria;
import com.davidsabino.cursomc.domain.Cidade;
import com.davidsabino.cursomc.domain.Cliente;
import com.davidsabino.cursomc.domain.Endereco;
import com.davidsabino.cursomc.domain.Estado;
import com.davidsabino.cursomc.domain.Pagamento;
import com.davidsabino.cursomc.domain.PagamentoComBoleto;
import com.davidsabino.cursomc.domain.PagamentoComCartao;
import com.davidsabino.cursomc.domain.Pedido;
import com.davidsabino.cursomc.domain.Produto;
import com.davidsabino.cursomc.domain.enums.EstadoPagamento;
import com.davidsabino.cursomc.domain.enums.TipoCliente;
import com.davidsabino.cursomc.repositories.CategoriaRepository;
import com.davidsabino.cursomc.repositories.EstadoRepository;
import com.davidsabino.cursomc.repositories.PagamentoRepository;
import com.davidsabino.cursomc.repositories.PedidoRepository;
import com.davidsabino.cursomc.repositories.ProdutoRepository;
import com.davidsabino.cursomc.repositories.CidadeRepository;
import com.davidsabino.cursomc.repositories.ClienteRepository;
import com.davidsabino.cursomc.repositories.EnderecoRepository;

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
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "David Sabino", "david.sabino17@gmail.com", "45344412827", TipoCliente.PESSOAFISICA);
		
		
		Endereco e1 = new Endereco(null, "Rua Amabile Della Torre", "154", "Apto 303", "Jardim", "45481548", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Mattos", "187", "Casa 155", "Vila Rosina", "798787", cli1, c2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/07/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		
		//fazendo as classes se relacionarem
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		cli1.getTelefones().addAll(Arrays.asList("22975845", "45425151"));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	    produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	    estadoRepository.saveAll(Arrays.asList(est1, est2));
	    cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	    clienteRepository.saveAll(Arrays.asList(cli1));
	    enderecoRepository.saveAll(Arrays.asList(e1, e2));
	    pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	    pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));   
	}
}
