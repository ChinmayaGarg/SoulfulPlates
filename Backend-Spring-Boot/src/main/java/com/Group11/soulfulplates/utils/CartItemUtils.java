package com.Group11.soulfulplates.utils;

import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.models.MenuItem;

import java.util.List;
import java.util.stream.Collectors;

public class CartItemUtils {

    public static List<Long> extractItemIds(List<CartItem> cartItems) {
        return cartItems.stream() // Convert the list to a stream
                .map(CartItem::getItemId) // Transform each CartItem to its itemId
                .collect(Collectors.toList()); // Collect the results into a new list
    }

    public static Double getTotalForOrderId(List<CartItem> cartItems) {
        return cartItems.stream() // Convert the list to a stream
                .map(cartItem -> cartItem.getPrice() * cartItem.getQuantity()) // Calculate the total price for each CartItem
                .reduce(0.0, Double::sum); // Sum up all the calculated prices
    }
}
