package falcoremx.com.FalcoreMX.controller;

import falcoremx.com.FalcoreMX.entity.PurchaseOrder;
import falcoremx.com.FalcoreMX.entity.PurchaseOrderItem;
import falcoremx.com.FalcoreMX.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/falcoremx/orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/company/{username}")
    public List<PurchaseOrder> getCompanyOrders(@PathVariable String username) {
        return purchaseOrderService.getOrdersByUserCompany(username);
    }

    @GetMapping("/user/{username}")
    public List<PurchaseOrder> getUserOrders(@PathVariable String username) {
        return purchaseOrderService.getOrdersByUser(username);
    }

    @GetMapping("/company/{empresaId}/products")
    public List<PurchaseOrder> getCompanyProducts(@PathVariable Integer empresaId) {
        return purchaseOrderService.getOrdersWithCompanyItems(empresaId);
    }

    @PutMapping("/{orderId}/accept")
    public PurchaseOrder acceptOrder(@PathVariable Integer orderId) {
        return purchaseOrderService.acceptOrder(orderId);
    }

    @PutMapping("/{orderId}/reject")
    public PurchaseOrder rejectOrder(@PathVariable Integer orderId) {
        return purchaseOrderService.rejectOrder(orderId);
    }

    @PutMapping("/{orderId}/shipped")
    public PurchaseOrder shipOrder(@PathVariable Integer orderId) {
        return purchaseOrderService.shipOrder(orderId);
    }

    @GetMapping("/company/{empresaId}/pending")
    public List<PurchaseOrder> getPendingOrders(@PathVariable Integer empresaId) {
        return purchaseOrderService.getPendingOrdersByCompany(empresaId);
    }

    @GetMapping("/{orderId}")
    public PurchaseOrder getOrderById(@PathVariable Integer orderId) {
        return purchaseOrderService.getOrderById(orderId);
    }

    @GetMapping("/company/{empresaId}/orders-filtered")
    public List<PurchaseOrder> getCompanyOrdersFiltered(@PathVariable Integer empresaId) {
        return purchaseOrderService.getOrdersWithCompanyItems(empresaId);
    }

}
