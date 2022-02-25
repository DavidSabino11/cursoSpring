package com.davidsabino.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsabino.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
