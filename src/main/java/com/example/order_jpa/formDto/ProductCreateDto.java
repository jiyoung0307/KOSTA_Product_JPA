package com.example.order_jpa.formDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ProductCreateDto {
    @NotNull (message = "product name은 null이 입력되면 안됩니다.")
    @NotBlank (message = "product name은 null이 반드시 입력해야합니다.")
    @NotEmpty (message = "product name은 null이 반드시 입력해야합니다.")
    private String name;

    @NotNull
    @Range(min=100, max=1_000_000)
    private Integer price;      /** null이될 수도 있고 숫자가 될 수도 있으므로 Integer*/

    @NotNull
    @Max(9999)
    private Integer quantity;   /** null 체크가 가능 */
}
