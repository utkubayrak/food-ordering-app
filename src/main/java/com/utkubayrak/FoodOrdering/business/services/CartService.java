package com.utkubayrak.FoodOrdering.business.services;

import com.utkubayrak.FoodOrdering.data.entities.CartEntity;
import com.utkubayrak.FoodOrdering.data.entities.CartItemEntity;
import com.utkubayrak.FoodOrdering.payload.request.AddCartItemRequest;

public interface CartService {

    public CartItemEntity addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItemEntity updateCartItemQuantity (Long cartItemId, int quantity) throws Exception;

    public CartEntity removeItemFromCart(Long cartItemId, String jwt) throws Exception;
    public Long calculateCartTotals (CartEntity cartEntity) throws Exception;

    public CartEntity findCartById(Long id) throws Exception;

    public CartEntity findCartByUserId (Long userId) throws Exception;
    public CartEntity clearCart (Long userId) throws Exception;
}
