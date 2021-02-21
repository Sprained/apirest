package com.produtos.apirest.repository;

import com.produtos.apirest.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(nativeQuery = true, value = "SELECT nome, quantidade, valor FROM tb_produto WHERE id = ?1")
    com.produtos.apirest.Interfaces.Produto findById(long id);

    Produto deleteById(long id);

    boolean existsById(long id);
}
