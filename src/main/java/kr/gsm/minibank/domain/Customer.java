package kr.gsm.minibank.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// 고객 - 번호, 이름, 전화번호
@Entity
@Table(name = "customer")
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20)
    private String phone;

    // 고객 1명 : 여러 개의 계좌
    // Account 테이블의 customer 필드와 연결
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // 계좌 추가
    public void addAccount(Account account) {
        accounts.add(account);
        account.assignCustomer(this);
    }

    // 고객 정보 수정
    public void updateInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}