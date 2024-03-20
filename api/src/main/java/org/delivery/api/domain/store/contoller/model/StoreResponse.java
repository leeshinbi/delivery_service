package org.delivery.api.domain.store.contoller.model;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse { //상점이 등록 됐을 때의 데이터

	private Long id; //등록되면 id가 발행됐을 것이다.

	private String name;

	private String address;

	private StoreStatus status;

	private StoreCategory category;

	private double star;

	private String thumbnailUrl;

	private BigDecimal minimumAmount;

	private BigDecimal minimumDeliveryAmount;

	private String phoneNumber;
}
