package com.example.wallettesttask.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Wallet {
    @Id
    private Long uuid;
    private BigDecimal account;
}
