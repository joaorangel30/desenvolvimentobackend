package com.api.produtos.repository;

import com.api.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE "
            + "(:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND "
            + "(:categoria IS NULL OR p.categoria = :categoria) AND "
            + "(:precoMax IS NULL OR p.preco <= :precoMax)")
    List<Produto> buscarComFiltros(
            @Param("nome") String nome,
            @Param("categoria") String categoria,
            @Param("precoMax") Double precoMax);
}
