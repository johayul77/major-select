
package kr.gsm.minibank.controller;

import jakarta.validation.Valid;
import kr.gsm.minibank.dto.AccountCreateRequestDTO;
import kr.gsm.minibank.dto.AccountResponseDTO;
import kr.gsm.minibank.dto.TransferRequestDTO;
import kr.gsm.minibank.service.AccountService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 계좌 생성
    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(
            @Valid @RequestBody AccountCreateRequestDTO request) {

        AccountResponseDTO response = accountService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 계좌 이체
    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(
            @Valid @RequestBody TransferRequestDTO request) {

        accountService.transfer(request);

        return ResponseEntity.ok().build();
    }

    // 계좌 전체 조회
    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {

        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // 계좌 개별 조회
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(accountService.getAccount(id));

    }

    // 계좌 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyAccount(@PathVariable("id") Long id) {

        System.out.println("modifyAccount: " + id);

        return ResponseEntity.noContent().build();
    }

    // 계좌 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAccount(@PathVariable("id") Long id) {

        System.out.println("removeAccount: " + id);

        return ResponseEntity.noContent().build();
    }
}
