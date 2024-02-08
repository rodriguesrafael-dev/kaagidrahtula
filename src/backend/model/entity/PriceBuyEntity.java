package backend.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceBuyEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private BigDecimal price_buy;
}
