package kr.gsm.minibank.repository;

import kr.gsm.minibank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //crud -> save(), findAll(), findById(), delete()...
    //단간의 계좌번호를 조회하는 메서드
    //Query Method(쿼리메서드)

    Optional<Account> findByAccountNumber(String accountNumber);
    // 100Page -> join fetch
    // JPQL : (객체 지향 쿼리)
    @Query("SELECT a FROM Account a JOIN FETCH a.customer")
    List<Account> findAllWithCustomer();
    //QueryDSL ?
}