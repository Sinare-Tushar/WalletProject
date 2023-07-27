package com.example.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.example.spring.datajpa.model.Currency;

@EnableJpaRepositories
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByName(String name);
    
}