package org.delivery.db.store;

import java.util.List;
import java.util.Optional;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepositroy extends JpaRepository<StoreEntity, Long> {

	//유효한 스토어 (id & status)
	//SELECT* FROM store WHERE id = ? AND status = ? ORDER BY id DESC LIMIT 1
	Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);

	// 유효한 스토어 리스트
	// select * from store where status = ? order by id desc
	List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus status);

	// 유효한 특정 카테고리의 스토어 리스트 (좋아요가 높은 순서)
	List<StoreEntity> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus status, StoreCategory storeCategory);

}
