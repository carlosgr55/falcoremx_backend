package falcoremx.com.FalcoreMX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import falcoremx.com.FalcoreMX.entity.PurchaseOrderItem;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Integer> {
}