package com.utkubayrak.FoodOrdering.business.services.impl;

import com.utkubayrak.FoodOrdering.business.services.CartService;
import com.utkubayrak.FoodOrdering.business.services.FoodService;
import com.utkubayrak.FoodOrdering.business.services.UserService;
import com.utkubayrak.FoodOrdering.data.entities.CartEntity;
import com.utkubayrak.FoodOrdering.data.entities.CartItemEntity;
import com.utkubayrak.FoodOrdering.data.entities.FoodEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import com.utkubayrak.FoodOrdering.data.repository.CartItemRepository;
import com.utkubayrak.FoodOrdering.data.repository.CartRepository;
import com.utkubayrak.FoodOrdering.data.repository.FoodRepository;
import com.utkubayrak.FoodOrdering.payload.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;


    @Override
    public CartItemEntity addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        UserEntity userEntity=userService.findUserByJwtToken (jwt);
        FoodEntity foodEntity = foodService.findFoodById(req.getFoodId());
        CartEntity cartEntity=cartRepository.findByCustomerId(userEntity.getId());
        for(CartItemEntity cartItem : cartEntity.getItems()){
            if(cartItem.getFood().equals (foodEntity)){
                int newQuantity=cartItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        CartItemEntity newCartItem=new CartItemEntity();
        newCartItem.setFood(foodEntity);
        newCartItem.setCart(cartEntity);
        newCartItem.setQuantity (req.getQuantity());
        newCartItem.setIngredients (req.getIngredients());
        newCartItem.setTotalPrice (req.getQuantity()*foodEntity.getPrice());
        CartItemEntity savedCartItem =cartItemRepository.save(newCartItem);
        cartEntity.getItems().add(savedCartItem);
        return savedCartItem;
    }
    @Override
    public CartItemEntity updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        return null;
    }
    @Override
    public CartEntity removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        return null;
    }

    @Override
    public Long calculateCartTotals(CartEntity cartEntity) throws Exception {
        return null;
    }

    @Override
    public CartEntity findCartById(Long id) throws Exception {
        return null;
    }

    @Override
    public CartEntity findCartByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public CartEntity clearCart(Long userId) throws Exception {
        return null;
    }
}
