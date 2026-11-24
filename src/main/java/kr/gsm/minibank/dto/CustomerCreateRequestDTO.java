package kr.gsm.minibank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class CustomerCreateRequestDTO {
    @NotBlank(message = "고객 이름은 필수입니다.")
    private String name;

    private String phone;
}