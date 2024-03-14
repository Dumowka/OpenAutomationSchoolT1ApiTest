package api.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import api.models.CommonDto;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductRequestDto extends CommonDto {

    private String name;

    private String category;

    private BigDecimal price;

    private BigDecimal discount;

}
