package kr.gsm.minibank.controller;

import jakarta.validation.Valid;
import kr.gsm.minibank.dto.CustomerCreateRequestDTO;
import kr.gsm.minibank.dto.CustomerResponseDTO;
import kr.gsm.minibank.dto.CustomerUpdateRequestDTO;
import kr.gsm.minibank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    //POST : JSON(name, phone)
    // http://localhost:8081/api/customers
    @PostMapping
    public ResponseEntity<Map<String,Long>> create(
            @Valid @RequestBody CustomerCreateRequestDTO request){
        Long id = customerService.createCustomer(request);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(Map.of("customer_id",id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @Valid @RequestBody
    CustomerUpdateRequestDTO request){
        customerService.updateCustomer(id,request);
        return ResponseEntity.ok().build();
    }

    //DELETE : http://localhost:8081/api/customers/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
