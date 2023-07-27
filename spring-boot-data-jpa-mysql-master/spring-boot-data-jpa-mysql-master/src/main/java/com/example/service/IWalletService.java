package com.example.service;

import java.math.BigDecimal;
import com.example.spring.datajpa.model.Wallet;

public interface IWalletService {

	Wallet addWallet(Long userId, Long currencyId, BigDecimal balance);
}
