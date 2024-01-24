package com.example.wallettesttask.repository;

import com.example.wallettesttask.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Modifying
    @Query(value = "update wallet " +
            "set account = account + ?1 " +
            "where uuid = ?2 ", nativeQuery = true)
    void changeSum(BigDecimal account, Long uuid);
    @Modifying
    @Query(value = "update wallet " +
            "set account = account - ?1 " +
            "where uuid = ?2 ", nativeQuery = true)
    void changeDif(BigDecimal account, Long uuid);
}
