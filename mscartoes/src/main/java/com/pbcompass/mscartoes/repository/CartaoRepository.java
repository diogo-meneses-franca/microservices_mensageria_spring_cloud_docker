package com.pbcompass.mscartoes.repository;

import com.pbcompass.mscartoes.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    @Query("select c from Cartao c where c.renda <= ?1")
    List<Cartao> findByRendaLessThenEqual(BigDecimal renda);
}
