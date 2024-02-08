package backend.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceOfferEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private BigDecimal price_offer;
	private LocalDate date_begin;
	private LocalDate date_end;
}
