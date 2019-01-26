package com.ai.test.vo;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    private FundAccount fundAccount;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public FundAccount getFundAccount() {
        return fundAccount;
    }
    public void setFundAccount(FundAccount fundAccount) {
        this.fundAccount = fundAccount;
    }
    
    
}
