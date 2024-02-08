package backend.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductPriceActiveTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProductEntity id_product;
	private PriceActiveTypeEntity id_price_active_type;
	private BigDecimal price_active;
}
