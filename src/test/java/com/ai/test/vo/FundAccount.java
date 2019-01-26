package com.ai.test.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FundAccount implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private BigDecimal balance;
    private Long position;
    
    private List<Stock> stockList;
    
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public Long getPosition() {
        return position;
    }
    public void setPosition(Long position) {
        this.position = position;
    }
    public List<Stock> getStockList() {
        return stockList;
    }
    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }
    
}
