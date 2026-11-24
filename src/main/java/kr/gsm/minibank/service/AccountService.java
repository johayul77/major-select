
package kr.gsm.minibank.service;

import org.springframework.transaction.annotation.Transactional;
import kr.gsm.minibank.domain.Account;
import kr.gsm.minibank.domain.Customer;
import kr.gsm.minibank.dto.AccountCreateRequestDTO;
import kr.gsm.minibank.dto.AccountResponseDTO;
import kr.gsm.minibank.dto.TransferRequestDTO;
import kr.gsm.minibank.exception.AccountNotFoundException;
import kr.gsm.minibank.exception.CustomerNotFoundException;
import kr.gsm.minibank.repository.AccountRepository;
import kr.gsm.minibank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    // 계좌 개설
    @Transactional
    public AccountResponseDTO createAccount(AccountCreateRequestDTO request) {

        log.info("계좌 개설 요청 - customerId={}", request.getCustomerId());

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new CustomerNotFoundException(request.getCustomerId()));

        // 계좌번호 생성
        String accountNumber = generateAccountNumber();

        // 계좌 생성
        Account account = new Account(accountNumber, request.getInitialBalance());

        customer.addAccount(account);

        // 데이터베이스에 저장
        Account saved = accountRepository.save(account);

        return AccountResponseDTO.from(saved);
    }

    // 계좌 이체
    @Transactional
    public void transfer(TransferRequestDTO request) {

        // 출금 계좌 조회
        Account fromAccount = accountRepository
                .findByAccountNumber(request.getFromAccountNumber())
                .orElseThrow(() ->
                        new IllegalArgumentException("출금 계좌를 찾을 수 없습니다."));

        // 입금 계좌 조회
        Account toAccount = accountRepository
                .findByAccountNumber(request.getToAccountNumber())
                .orElseThrow(() ->
                        new IllegalArgumentException("입금 계좌를 찾을 수 없습니다."));

        // 동일 계좌 이체 방지
        if (fromAccount.getAccountNumber()
                .equals(toAccount.getAccountNumber())) {
            throw new IllegalArgumentException("동일한 계좌로 이체할 수 없습니다.");
        }

        BigDecimal amount = request.getAmount();

        // 출금
        fromAccount.withdraw(amount);

        // 입금
        toAccount.deposit(amount);

        log.info("계좌 이체 완료: {} -> {}, 금액: {}",
                request.getFromAccountNumber(),
                request.getToAccountNumber(),
                amount);
    }

    // 전체 계좌 조회
    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAllAccounts() {

        return accountRepository.findAllWithCustomer()

                .stream()
                .map(AccountResponseDTO::from)
                .toList();
    }

    // 특정 계좌 조회
    @Transactional(readOnly = true)
    public AccountResponseDTO getAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        return AccountResponseDTO.from(account);
    }

    // 계좌번호 생성
    public String generateAccountNumber() {

        return "110-"
                + (1000 + new Random().nextInt(9000))
                + "-"
                + (1000 + new Random().nextInt(9000));
    }
}
