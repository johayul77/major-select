package kr.gsm.minibank.repository;

import kr.gsm.minibank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}