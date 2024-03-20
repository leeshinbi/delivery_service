package org.delivery.api.domain.storemenu.controller.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreMenuRegisterRequest { //메뉴 생성 시 제출 데이터

	@NotNull
	private Long storeId; // 어떤 상점에다 등록할 지 결정하는 id

	@NotBlank
	private String name; // 상품 이름

	@NotNull
	private BigDecimal amount; //상품 가격

	@NotBlank
	private String thumbnailUrl; // 상품 이미지

}
