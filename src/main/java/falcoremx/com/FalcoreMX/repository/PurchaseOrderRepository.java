package falcoremx.com.FalcoreMX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import falcoremx.com.FalcoreMX.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}