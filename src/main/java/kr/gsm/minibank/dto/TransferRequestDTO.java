package kr.gsm.minibank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransferRequestDTO {
   @NotBlank(message = "출금 계좌번호는 필수입니다.")
    private String fromAccountNumber;

   @NotBlank(message = "입금 계좌번호는 필수입니다.")
    private String toAccountNumber;

   @NotNull(message = "이체 금액은 필수입니다.")
    @Positive(message = "이체 금액은 0원보다 커야 합니다.")
    private BigDecimal amount;

   public String getFromAccountNumber() {return fromAccountNumber;}
    public void setFromAccountNumber(String fromAccountNumber) {this.fromAccountNumber = fromAccountNumber;}

    public String getToAccountNumber() {return toAccountNumber;}
    public void setToAccountNumber(String toAccountNumber) {this.toAccountNumber = toAccountNumber;}

    public BigDecimal getAmount() {return amount;}
    public void setAmount(BigDecimal amount) {this.amount = amount;}
}

