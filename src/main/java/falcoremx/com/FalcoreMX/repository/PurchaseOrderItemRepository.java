package falcoremx.com.FalcoreMX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import falcoremx.com.FalcoreMX.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Integer> {
    @Query("SELECT poi FROM PurchaseOrderItem poi JOIN poi.purchaseOrder po JOIN User u ON po.user = u.username WHERE u.empresa.id = ?1")
    List<PurchaseOrderItem> findProductsByEmpresaId(Integer empresaId);

}