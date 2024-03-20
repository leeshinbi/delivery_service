package org.delivery.db.storemenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "store_menu")
public class StoreMenuEntity extends BaseEntity {

	@Column(nullable = false)
	private Long storeId; // 스토어와 연결되는 Id

	@Column(length = 100, nullable = false)
	private String name;

	@Column(precision = 11, scale = 4, nullable = false)
	private BigDecimal amount; //메뉴 가격

	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private StoreMenuStatus status;

	@Column(length = 200, nullable = false)
	private String thumbnailUrl;

	private int likeCount; // default 값이 0이라 int로 설정 (좋아요 수)

	private int sequence; // 정렬 순서

}
