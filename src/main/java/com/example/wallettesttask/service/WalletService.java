package com.example.wallettesttask.service;

import com.example.wallettesttask.dto.NewWalletDto;
import com.example.wallettesttask.dto.OperationType;
import com.example.wallettesttask.dto.WalletDto;
import com.example.wallettesttask.entity.Wallet;
import com.example.wallettesttask.exception.WalletDoesNotExist;
import com.example.wallettesttask.exception.WalletNotEnoughMoney;
import com.example.wallettesttask.exception.WalletNotTrue;
import com.example.wallettesttask.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Wallet getAccount(Long uuid) {
        return walletRepository.findById(uuid)
                .orElseThrow(()-> new WalletDoesNotExist("Данный кошелек не существует"));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void changeAccount(WalletDto walletDto) {
        OperationType operationType = walletDto.getOperationType();
        if (operationType == OperationType.DEPOSIT) {
            walletRepository.changeSum(walletDto.getAmount(), walletDto.getUuid());
            return;
        }
        else if (operationType == OperationType.WITHDRAW) {
            Wallet wallet = getAccount(walletDto.getUuid());
            int result = wallet.getAccount().compareTo(walletDto.getAmount());
            if (result == -1) {
                throw new WalletNotEnoughMoney("Недостаточно денег");
            }
             walletRepository.changeDif(walletDto.getAmount(), walletDto.getUuid());
             return;
        }
        throw new WalletNotTrue("Неверная отправка JSON");
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Wallet add(NewWalletDto newWalletDto) {
        Wallet wallet = new Wallet();
        wallet.setAccount(newWalletDto.getAccount());
        wallet.setUuid(newWalletDto.getUuid());

        return walletRepository.save(wallet);
    }

    public void remove(Long uuid) {
        walletRepository.deleteById(uuid);
    }
}
