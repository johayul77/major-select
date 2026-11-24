package kr.gsm.minibank.dto;

import kr.gsm.minibank.domain.Customer;
import lombok.Data;

@Data
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private int accountCount;

    // Entity -> Dto
    public static CustomerResponseDTO from(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.id = customer.getId();
        dto.name = customer.getName();
        dto.phone = customer.getPhone();
        dto.accountCount=customer.getAccounts().size();
        return dto;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public int getAccountCount() {return accountCount;}
    public void setAccountCount(int accountCount) {this.accountCount = accountCount;}
}


