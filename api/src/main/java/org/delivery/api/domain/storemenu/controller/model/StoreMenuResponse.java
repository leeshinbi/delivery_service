package org.delivery.api.domain.storemenu.controller.model;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreMenuResponse { // 메뉴 생성 완료 시 데이터

	private Long id; //등록한 메뉴의 id

	private Long storeId; //연결된 상점의 id

	private String name;

	private BigDecimal amount;

	private StoreMenuStatus status;

	private String thumbnailUrl;

	private int likeCount;

	private int sequence;


}
