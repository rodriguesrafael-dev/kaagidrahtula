package backend.model.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductPriceBuyEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProductEntity id_product;
	private PriceBuyEntity id_price_buy;
}
