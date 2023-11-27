package com.moulamanager.api.controllers;

import com.moulamanager.api.dto.cart.request.UpdateCartTotalPriceRequestDTO;
import com.moulamanager.api.dto.cart.result.CartResultDTO;
import com.moulamanager.api.models.CartModel;
import com.moulamanager.api.services.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartResultDTO>> getAllCarts() {
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResultDTO> getCartById(@PathVariable long id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CartResultDTO> createCart(@PathVariable long userId) {
        return ResponseEntity.ok(cartService.save(userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CartResultDTO> updateCart(@RequestBody CartModel cart, @PathVariable long id) {
        cart.setId(id);
        return ResponseEntity.ok(cartService.update(cart));
    }

    @PatchMapping("/{cartId}/total-price")
    public ResponseEntity<CartResultDTO> updateCartTotalPrice(@RequestBody UpdateCartTotalPriceRequestDTO cart, @PathVariable long cartId) {
        return ResponseEntity.ok(cartService.updateCartTotalPrice(cartId, cart.getTotalPrice()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}