package org.delivery.api.domain.store.contoller.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.store.enums.StoreCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest { //상점 등록할 때 작성 하는 데이터

	@NotBlank
	private String name;

	@NotBlank   // "" , " " , null
	private String address;

	@NotNull
	private StoreCategory storeCategory;

	@NotBlank
	private String thumbnailUrl;

	@NotNull
	private BigDecimal minimumAmount;

	@NotNull
	private BigDecimal minimumDeliveryAmount;

	@NotBlank
	private String phoneNumber;


}
