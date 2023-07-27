package com.example.spring.datajpa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
@Table(name = "Transaction_Details")
@Data
@AllArgsConstructor
public class Transaction {

	 @Id	
	 @GeneratedValue
	 @Column(name = "transaction_id")
	private UUID transactionId;
	
	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@ManyToOne
    @JoinColumn(name = "recipient_wallet_id")
    private Wallet recipientWallet;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "transaction_amount")
	private BigDecimal transaction_amount;
	
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;
	
	@Column(name = "transaction_time")
	private LocalDateTime transaction_time;
	

	public Transaction() {
		super();
	}

	public Transaction(Wallet wallet, Wallet recipientWallet, String status, BigDecimal amount, LocalDateTime date,
			LocalDateTime time) {
		super();
		this.wallet = wallet;
		this.recipientWallet = recipientWallet;
		this.status = status;
		this.transaction_amount = amount;
		this.transactionDate = date;
		this.transaction_time = time;
	}
public UUID getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(UUID id) {
		this.transactionId = id;
	}

	public Wallet getRecipientWallet() {
		return recipientWallet;
	}

	public void setRecipientWallet(Wallet recipientWallet) {
		this.recipientWallet = recipientWallet;
	}
	
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return transaction_amount;
	}

	public void setAmount(BigDecimal amount) {
		this.transaction_amount = amount;
	}

	public LocalDateTime getDate() {
		return transactionDate;
	}

	public void setDate(LocalDateTime localDateTime) {
		this.transactionDate = localDateTime;
	}

	public LocalDateTime getTime() {
		return transaction_time;
	}

	public void setTime(LocalDateTime time) {
		this.transaction_time = time;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", wallet=" + wallet + ", recipientWallet="
				+ recipientWallet + ", status=" + status + ", transaction_amount=" + transaction_amount
				+ ", transactionDate=" + transactionDate + ", transaction_time=" + transaction_time + "]";
	}
	
}
