package com.example.wallettesttask.dto;

import lombok.Data;


import java.math.BigDecimal;

@Data
public class NewWalletDto {

    private Long uuid;
    private BigDecimal account;
}
