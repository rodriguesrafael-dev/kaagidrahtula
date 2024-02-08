package backend.model.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductActiveEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private ProductActiveEntity id_product;
	private boolean active;
}
