package kr.gsm.minibank.service;

import kr.gsm.minibank.domain.Customer;
import kr.gsm.minibank.dto.CustomerCreateRequestDTO;
import kr.gsm.minibank.dto.CustomerResponseDTO;
import kr.gsm.minibank.dto.CustomerUpdateRequestDTO;
import kr.gsm.minibank.exception.CustomerNotFoundException;
import kr.gsm.minibank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 고객 등록
    @Transactional
    public Long createCustomer(CustomerCreateRequestDTO request) {
        Customer customer = new Customer();

        // 요청받은 이름과 전화번호를 고객 객체에 저장
        customer.updateInfo(request.getName(), request.getPhone());

        return customerRepository.save(customer).getId();
    }

    // 특정 고객 조회
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return CustomerResponseDTO.from(customer);
    }

    // 전체 고객 조회
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAllCustomer() {
        return customerRepository.findAll().stream()
                .map(CustomerResponseDTO::from)
                .toList();
    }

    // 고객 정보 수정
    @Transactional
    public void updateCustomer(Long id, CustomerUpdateRequestDTO request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customer.updateInfo(request.getName(), request.getPhone());
    }

    // 고객 삭제
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        if (!customer.getPhone().isEmpty()) {
            throw new IllegalArgumentException("보유 계좌가 있는 고객은 삭제할 수 없습니다.");
        }

        customerRepository.delete(customer);
    }
}