package falcoremx.com.FalcoreMX.service;

import falcoremx.com.FalcoreMX.entity.Cart;
import falcoremx.com.FalcoreMX.entity.CartItem;
import falcoremx.com.FalcoreMX.entity.Product;
import falcoremx.com.FalcoreMX.repository.CartItemRepository;
import falcoremx.com.FalcoreMX.repository.CartRepository;
import falcoremx.com.FalcoreMX.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    // Obtener o crear carrito activo
    @Transactional
    public Cart obtenerCarritoActivo(String username) {
        return cartRepository.findByUserAndActivo(username, true)
                .orElseGet(() -> {
                    Cart nuevo = new Cart();
                    nuevo.setUser(username);
                    nuevo.setActivo(true);
                    return cartRepository.save(nuevo);
                });
    }

    // Agregar producto al carrito
    @Transactional
    public CartItem agregarProducto(String username, Integer productId, Integer cantidad) {
        Cart cart = obtenerCarritoActivo(username);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verificar si ya está en el carrito
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                item.setCantidad(item.getCantidad() + cantidad);
                item.setSubtotal(item.getCantidad() * item.getPrecioUnitario());
                return cartItemRepository.save(item);
            }
        }

        // Si no existe, crear nuevo item
        CartItem nuevoItem = new CartItem();
        nuevoItem.setCart(cart);
        nuevoItem.setProduct(product);
        nuevoItem.setCantidad(cantidad);
        nuevoItem.setPrecioUnitario(Double.parseDouble(product.getPrecioUnitario().toPlainString()));
        nuevoItem.setSubtotal(Double.parseDouble(product.getPrecioUnitario().toPlainString()) * cantidad);
        return cartItemRepository.save(nuevoItem);
    }

    // Listar productos del carrito
    public List<CartItem> listarItems(String username) {
        Cart cart = obtenerCarritoActivo(username);
        return cartItemRepository.findByCartId(cart.getId());
    }

    // Eliminar un producto del carrito
    @Transactional
    public void eliminarItem(Integer itemId) {
        cartItemRepository.deleteById(itemId);
    }

    // Vaciar carrito
    @Transactional
    public void vaciarCarrito(String username) {
        Cart cart = obtenerCarritoActivo(username);
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        cartItemRepository.deleteAll(items);
    }

    // Cerrar carrito (simula "finalizar compra")
    @Transactional
    public void cerrarCarrito(String username) {
        Cart cart = obtenerCarritoActivo(username);
        cart.setActivo(false);
        cartRepository.save(cart);
    }

    @Transactional
    public CartItem addOrUpdateCartItem(String username, Integer productId, int cantidad) {
        // Buscar el carrito activo del usuario
        Cart cart = cartRepository.findByUserAndActivoTrue(username)
                .orElseGet(() -> {
                    Cart nuevo = new Cart();
                    nuevo.setUser(username);
                    nuevo.setActivo(true);
                    return cartRepository.save(nuevo);
                });

        // Verificar si el producto ya está en el carrito
        Optional<CartItem> existingItemOpt = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        if (existingItemOpt.isPresent()) {
            // Ya existe → actualizar cantidad
            CartItem existingItem = existingItemOpt.get();
            existingItem.setCantidad(existingItem.getCantidad() + cantidad);
            return cartItemRepository.save(existingItem);
        } else {
            // Nuevo producto → crear registro
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setCantidad(cantidad);
            newItem.setPrecioUnitario(product.getPrecioUnitario().doubleValue());
            return cartItemRepository.save(newItem);
        }
    }

}
