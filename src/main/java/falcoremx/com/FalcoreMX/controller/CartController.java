package falcoremx.com.FalcoreMX.controller;

import falcoremx.com.FalcoreMX.entity.CartItem;
import falcoremx.com.FalcoreMX.service.CartService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/falcoremx/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartItem agregarProducto(@RequestBody Map<String, Object> body) {
        String username = (String) body.get("username");
        Integer productId = (Integer) body.get("productId");
        Integer cantidad = (Integer) body.getOrDefault("cantidad", 1);

        // Llama al nuevo método que actualiza si ya existe
        return cartService.addOrUpdateCartItem(username, productId, cantidad);
    }


    @GetMapping("/{username}")
    public List<CartItem> listar(@PathVariable String username) {
        return cartService.listarItems(username);
    }

    @DeleteMapping("/item/{itemId}")
    public void eliminarItem(@PathVariable Integer itemId) {
        cartService.eliminarItem(itemId);
    }

    @DeleteMapping("/clear/{username}")
    public void vaciarCarrito(@PathVariable String username) {
        cartService.vaciarCarrito(username);
    }

    @PostMapping("/checkout/{username}")
    public void finalizarCompra(@PathVariable String username) {
        cartService.cerrarCarrito(username);
    }
}
