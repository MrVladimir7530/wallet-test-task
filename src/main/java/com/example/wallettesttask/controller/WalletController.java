package com.example.wallettesttask.controller;

import com.example.wallettesttask.dto.NewWalletDto;
import com.example.wallettesttask.dto.WalletDto;
import com.example.wallettesttask.entity.Wallet;
import com.example.wallettesttask.exception.WalletDoesNotExist;
import com.example.wallettesttask.exception.WalletNotEnoughMoney;
import com.example.wallettesttask.exception.WalletNotTrue;
import com.example.wallettesttask.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @GetMapping("wallet/{WALLET_UUID}")
    public ResponseEntity<?> getCheck(@PathVariable Long WALLET_UUID) {
        try {
            Wallet wallet = walletService.getAccount(WALLET_UUID);
            return ResponseEntity.ok().body(wallet);
        } catch (WalletDoesNotExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/wallets")
    public ResponseEntity<String> changeAccount(@RequestBody WalletDto walletDto) {
        try {
            walletService.changeAccount(walletDto);
            return ResponseEntity.ok().body("OK");
        } catch (WalletDoesNotExist | WalletNotEnoughMoney | WalletNotTrue e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> addWallet(@RequestBody NewWalletDto newWalletDto) {
        Wallet wallet = walletService.add(newWalletDto);
        return ResponseEntity.ok(wallet);
    }

    @DeleteMapping("wallet/{uuid}")
    public ResponseEntity<Void> removeWallet(@PathVariable Long uuid) {
        walletService.remove(uuid);
        return ResponseEntity.ok().build();
    }

}
