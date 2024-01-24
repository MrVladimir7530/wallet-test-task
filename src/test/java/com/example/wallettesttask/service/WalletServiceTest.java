package com.example.wallettesttask.service;

import com.example.wallettesttask.dto.OperationType;
import com.example.wallettesttask.dto.WalletDto;
import com.example.wallettesttask.entity.Wallet;
import com.example.wallettesttask.exception.WalletDoesNotExist;
import com.example.wallettesttask.exception.WalletNotEnoughMoney;
import com.example.wallettesttask.exception.WalletNotTrue;
import com.example.wallettesttask.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


public class WalletServiceTest {
    private WalletService walletService;
    private WalletRepository walletRepository;

    @BeforeEach
    public void init() {
        walletRepository = Mockito.mock(WalletRepository.class);
        walletService = new WalletService(walletRepository);
    }

    @Test
    public void getAccountTest() {
        Wallet wallet = getWallet();

        when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet));
        Wallet account = walletService.getAccount(1L);

        assertEquals(wallet, account);
    }

    @Test
    public void getAccountWalletDoesNotExistTest() {
        assertThrows(WalletDoesNotExist.class, () -> walletService.getAccount(1L));
    }

    @Test
    public void changeAccountSumTest() {
        walletService.changeAccount(getWalletDto(OperationType.DEPOSIT));
    }

    @Test
    public void changeAccountDifTest() {
        Wallet wallet = getWallet();
        when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet));
        walletService.changeAccount(getWalletDto(OperationType.WITHDRAW));
    }

    @Test
    public void changeAccountWalletNotEnoughMoney() {
        Wallet wallet = getWallet();
        when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet));

        WalletDto walletDto = getWalletDto(OperationType.WITHDRAW);
        walletDto.setAmount(new BigDecimal(10));

        assertThrows(WalletNotEnoughMoney.class, () -> walletService.changeAccount(walletDto));
    }

    @Test
    public void changeAccountWalletWalletDoesNotExist() {
        WalletDto walletDto = getWalletDto(OperationType.WITHDRAW);
        assertThrows(WalletDoesNotExist.class, () -> walletService.changeAccount(walletDto));
    }

    @Test
    public void changeAccountWalletNotTrue() {
        WalletDto walletDto = getWalletDto(null);
        assertThrows(WalletNotTrue.class, () -> walletService.changeAccount(walletDto));

    }

    private WalletDto getWalletDto(OperationType operationType) {
        WalletDto walletDto = new WalletDto();

        walletDto.setUuid(1L);
        walletDto.setAmount(new BigDecimal(1));
        walletDto.setOperationType(operationType);

        return walletDto;
    }

    private Wallet getWallet() {
        Wallet wallet = new Wallet();

        wallet.setUuid(1L);
        wallet.setAccount(new BigDecimal(1));

        return wallet;
    }
}
