package falcoremx.com.FalcoreMX.service;

import falcoremx.com.FalcoreMX.entity.PurchaseOrder;
import falcoremx.com.FalcoreMX.entity.PurchaseOrderItem;
import falcoremx.com.FalcoreMX.entity.User;
import falcoremx.com.FalcoreMX.repository.PurchaseOrderRepository;
import falcoremx.com.FalcoreMX.repository.PurchaseOrderItemRepository;
import falcoremx.com.FalcoreMX.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;
    private final UserRepository userRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
                                PurchaseOrderItemRepository purchaseOrderItemRepository,
                                UserRepository userRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
        this.userRepository = userRepository;
    }

    // Ver órdenes de compra de la empresa del usuario
    public List<PurchaseOrder> getOrdersByUserCompany(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Integer empresaId = user.getEmpresa().getId();
        return purchaseOrderRepository.findOrdersByEmpresaId(empresaId);
    }

    // Ver órdenes personales de un usuario
    public List<PurchaseOrder> getOrdersByUser(String username) {
        return purchaseOrderRepository.findByUser(username);
    }

    // Ver todos los productos recibidos por una empresa
    public List<PurchaseOrderItem> getProductsReceivedByCompany(Integer empresaId) {
        return purchaseOrderItemRepository.findProductsByEmpresaId(empresaId);
    }

    // Marcar orden como aceptada
    @Transactional
    public PurchaseOrder acceptOrder(Integer orderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (!"PENDIENTE".equals(order.getStatus())) {
            throw new RuntimeException("Solo se pueden aceptar órdenes pendientes");
        }

        order.setStatus("ACEPTADA");
        return purchaseOrderRepository.save(order);
    }

    // Marcar orden como rechazada
    @Transactional
    public PurchaseOrder rejectOrder(Integer orderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (!"PENDIENTE".equals(order.getStatus())) {
            throw new RuntimeException("Solo se pueden rechazar órdenes pendientes");
        }

        order.setStatus("RECHAZADA");
        return purchaseOrderRepository.save(order);
    }

    // Obtener orden por ID
    public PurchaseOrder getOrderById(Integer orderId) {
        return purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    // Ver órdenes pendientes de una empresa
    public List<PurchaseOrder> getPendingOrdersByCompany(Integer empresaId) {
        return purchaseOrderRepository.findPendingOrdersByEmpresaId(empresaId);
    }

    // Obtener órdenes con items filtrados por empresa
    public List<PurchaseOrder> getOrdersWithCompanyItems(Integer empresaId) {
        List<PurchaseOrder> allOrders = purchaseOrderRepository.findOrdersWithItemsForEmpresa(empresaId);

        // Filtrar los items de cada orden para mostrar solo los de la empresa solicitante
        for (PurchaseOrder order : allOrders) {
            List<PurchaseOrderItem> filteredItems = order.getItems().stream()
                    .filter(item -> item.getProduct().getEmpresaEntity().getId().equals(empresaId))
                    .toList();
            order.setItems(filteredItems);
        }

        return allOrders;
    }

    @Transactional
    public PurchaseOrder shipOrder(Integer orderId) {
            PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

            if (!"ACEPTADA".equals(order.getStatus())) {
                throw new RuntimeException("Solo se pueden enviar ordenes aceptadas");
            }

            order.setStatus("SHIPPED");
            return purchaseOrderRepository.save(order);

    }
}
