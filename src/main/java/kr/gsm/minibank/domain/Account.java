package kr.gsm.minibank.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// 계좌 : 번호, 계좌번호, 잔액, 생성날짜, 고객정보
@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(nullable = false)
    private LocalDateTime openedAt;

    // 이 계좌가 어떤 고객의 계좌인지 고객정보
    // Customer와 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // 낙관적 락을 위한 버전
    @Version
    private Long version;

    public Account(String accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.openedAt = LocalDateTime.now();
    }

    void assignCustomer(Customer customer) {
        this.customer = customer;
    }

    // 출금
    public void withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        this.balance = this.balance.subtract(amount);
    }

    // 입금
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}