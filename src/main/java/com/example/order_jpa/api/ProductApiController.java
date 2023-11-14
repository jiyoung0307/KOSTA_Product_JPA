package com.example.order_jpa.api;

import com.example.order_jpa.apiDto.ProductAPICreateDto;
import com.example.order_jpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;
//    1. 타입 오류(400 error) 2. 비즈니스 룰(500 error)
@PostMapping("/add")
public Object addProduct(@Validated @RequestBody ProductAPICreateDto productAPICreateDto,
                         BindingResult bindingResult) {
    System.out.println("api product controller 입니다. 입력된 값은 " + productAPICreateDto.getPrice());

    // 특정 필드 오류가 아닌 전제 예외 처리
    if (productAPICreateDto.getPrice() != null && productAPICreateDto.getQuantity() != null) {
        int result = productAPICreateDto.getPrice() * productAPICreateDto.getQuantity();
        if (result == 0) {
            bindingResult.reject("global_quantity", new Object[]{0, result}, null);
        }
    }

    boolean b1 = bindingResult.hasErrors();
    boolean b2 = bindingResult.hasFieldErrors();
    boolean b3 = bindingResult.hasGlobalErrors();
    log.info("bindingResult {} : ", bindingResult);
    System.out.println("b1 :" + b1);
    System.out.println("b2 :" + b2);
    System.out.println("b3 :" + b3);
    if (bindingResult.hasErrors()) {
        //model.addAttribute("product", new Product());
        return bindingResult;
    }
    productService.addProductApi(productAPICreateDto);
    return productAPICreateDto;
}
}