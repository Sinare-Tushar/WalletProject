package com.example.spring.datajpa.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Wallet")
@AllArgsConstructor
public class Wallet {
    @Id
    @GenericGenerator(name = "WALLET_SEQUENCE", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "WALLET_SEQUENCE")
	private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    
    
    @Column(name = "balance")
	private BigDecimal balance;

	public Wallet() {
		super();
	}

	public Wallet(User user, Currency currency, BigDecimal balance) {
		this.user = user;
		this.currency = currency;
		this.balance = balance;
	}

    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Currency getCurrency() {
		return currency;
	}


	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", user=" + user + ", currency=" + currency + ", balance=" + balance + "]";
	}
}

