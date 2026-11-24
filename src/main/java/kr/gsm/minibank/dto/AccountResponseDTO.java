package kr.gsm.minibank.dto;

import kr.gsm.minibank.domain.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponseDTO {

    private Long id; // 계좌 일련번호
    private String accountNumber; // 계좌번호
    private String ownerName; // 예금주 이름
    private BigDecimal balance; // 잔액
    private LocalDateTime openedAt; // 계좌 개설일

    // Account(Entity) -> AccountResponseDTO
    public static AccountResponseDTO from(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.id = account.getId();
        dto.accountNumber = account.getAccountNumber();

        // 고객 정보가 있을 경우 예금주 이름 가져오기
        dto.ownerName = account.getCustomer().getName();
        dto.balance = account.getBalance();
        dto.openedAt = account.getOpenedAt();
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(LocalDateTime openedAt) {
        this.openedAt = openedAt;
    }
}