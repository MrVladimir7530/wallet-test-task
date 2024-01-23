package com.example.wallettesttask.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletDto {
    private Long uuid;
    private OperationType operationType;
    private BigDecimal amount;
}
