package kr.gsm.minibank.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(Long id){
        super("계좌를 찾을 수 없습니다. id=" + id);
    }

    public AccountNotFoundException(String accountNumber){
        super("계좌를 찾을 수 없습니다. 계좌번호=" +  accountNumber);
    }
}
