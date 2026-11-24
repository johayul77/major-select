package kr.gsm.minibank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class AccountCreateRequestDTO {

    @NotNull(message = "고객 ID는 필수입니다.")
    private Long customerId;

    @NotNull
    @PositiveOrZero(message = "초기 입금액은 0 이상이어야 합니다.")
    private BigDecimal initialBalance;

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

}
