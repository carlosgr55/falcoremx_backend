package falcoremx.com.FalcoreMX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import falcoremx.com.FalcoreMX.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    @Query("SELECT po FROM PurchaseOrder po JOIN User u ON po.user = u.username WHERE u.empresa.id = ?1")
    List<PurchaseOrder> findOrdersByEmpresaId(Integer empresaId);

    List<PurchaseOrder> findByUser(String username);

    @Query("SELECT po FROM PurchaseOrder po JOIN User u ON po.user = u.username WHERE u.empresa.id = ?1 AND po.status = 'PENDIENTE'")
    List<PurchaseOrder> findPendingOrdersByEmpresaId(Integer empresaId);

    @Query("SELECT DISTINCT po FROM PurchaseOrder po " +
            "JOIN FETCH po.items poi " +
            "JOIN poi.product p " +
            "WHERE p.empresaEntity.id = ?1")
    List<PurchaseOrder> findOrdersWithItemsForEmpresa(Integer empresaId);
}
