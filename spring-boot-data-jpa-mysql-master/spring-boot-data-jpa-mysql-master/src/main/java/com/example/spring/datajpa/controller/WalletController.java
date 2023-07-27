package com.example.spring.datajpa.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.IWalletService;
import com.example.spring.datajpa.model.Wallet;
import com.example.spring.datajpa.repository.impl.WalletServiceImpl;

@RestController
@RequestMapping("/api/users/registration")
public class WalletController {

	@Autowired
	IWalletService iWalletService;
	
	@Autowired
	WalletServiceImpl walletServiceImpl;
	
	@Autowired
    public WalletController(WalletServiceImpl walletServiceImpl) {
        this.walletServiceImpl = walletServiceImpl;
    }
	
	@PostMapping("/createWallet")
    public ResponseEntity<Wallet> addWallet(@RequestParam Long userId,
                                             @RequestParam Long currencyId,
                                             @RequestParam BigDecimal balance) {
        Wallet wallet = iWalletService.addWallet(userId, currencyId, balance);
        return ResponseEntity.ok(wallet);
    }
	
	@PostMapping("/wallet/transfer/{senderWalletId}/transfer/{recipientWalletId}")
    public void transferFunds(@PathVariable Long senderWalletId, @PathVariable Long recipientWalletId, @RequestParam BigDecimal amount) throws Exception {
		walletServiceImpl.transferFunds(senderWalletId, recipientWalletId, amount);
    }
}
