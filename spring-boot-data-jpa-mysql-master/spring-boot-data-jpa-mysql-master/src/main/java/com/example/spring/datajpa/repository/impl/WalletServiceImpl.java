package com.example.spring.datajpa.repository.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.IWalletService;
import com.example.spring.datajpa.model.Currency;
import com.example.spring.datajpa.model.User;
import com.example.spring.datajpa.model.Wallet;
import com.example.spring.datajpa.model.Transaction;
import com.example.spring.datajpa.repository.CurrencyRepository;
import com.example.spring.datajpa.repository.TransactionRepository;
import com.example.spring.datajpa.repository.UserServiceRepository;
import com.example.spring.datajpa.repository.WalletRepository;

@Service
public class WalletServiceImpl implements IWalletService {

	@Autowired
	WalletRepository walletRepository;

	@Autowired
	UserServiceRepository userRepository;
	    
	@Autowired
	CurrencyRepository currencyRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	@Override
	public Wallet addWallet(Long userId, Long currencyId, BigDecimal balance) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
		
		Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new EntityNotFoundException("Currency not found"));
		
		Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setCurrency(currency);
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
	}
	
	@Autowired
    public WalletServiceImpl(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }
	
	@Transactional
    public void transferFunds(Long senderWalletId, Long recipientWalletId, BigDecimal amount) throws Exception {
        Wallet senderWallet = walletRepository.findById(senderWalletId).orElseThrow(() -> new EntityNotFoundException("Sender Wallet Id not found"));
        Wallet recipientWallet = walletRepository.findById(recipientWalletId).orElseThrow(() -> new EntityNotFoundException("Recipient Wallet Id not found"));
       
        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(senderWalletId);
        }

        senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
        recipientWallet.setBalance(recipientWallet.getBalance().add(amount));
        UUID transactionId = UUID.randomUUID();
        walletRepository.save(senderWallet);
        walletRepository.save(recipientWallet);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setWallet(senderWallet);
        transaction.setAmount(amount.negate());
        transaction.setRecipientWallet(recipientWallet);
        transaction.setStatus("Success");
        transaction.setDate(LocalDateTime.now());
        transaction.setTime(LocalDateTime.now());
        System.out.println(transaction.getTransactionId());
        transactionRepository.save(transaction);
    }
}
