package com.example.wallettesttask.controller;

import com.example.wallettesttask.dto.WalletDto;
import com.example.wallettesttask.entity.Wallet;
import com.example.wallettesttask.exception.WalletDoesNotExist;
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
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @PostMapping("/wallets")
    public ResponseEntity<String> changeAccount(@RequestBody WalletDto walletDto) {
        walletService.changeAccount(walletDto);
        try {
            return ResponseEntity.ok().body("OK");
        } catch (WalletDoesNotExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
