package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.*;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.payload.response.OrderDetailsResponse;
import com.Group11.soulfulplates.payload.response.OrdersResponse;
import com.Group11.soulfulplates.repository.*;
import com.Group11.soulfulplates.services.OrderService;
import com.Group11.soulfulplates.utils.CartItemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    @Autowired
    private final MenuItemRepository menuItemRepository; // Assume this exists

        @Autowired
    public OrderServiceImpl(UserRepository userRepository, StoreRepository storeRepository, OrderRepository orderRepository, CartItemRepository cartItemRepository, MenuItemRepository menuItemRepository) {
            this.userRepository = userRepository;
            this.storeRepository = storeRepository;
            this.orderRepository = orderRepository;
            this.cartItemRepository = cartItemRepository;
            this.menuItemRepository = menuItemRepository;
    }


    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        // Find user by orderId
        Optional<User> user = userRepository.findById(request.getUserId());
        // Find store by storeId
        Store store = storeRepository.getReferenceById(request.getUserId());

        Order order = new Order();
        if(user.isPresent()){
            order.setUser(user.get());
        }
        order.setStore(store);
        order.setInstructions(request.getInstructions());
        order.setStatus("Pending");
        order.setRating(null);
        order.setUpdatedAt(new Date());
        order.setCreatedAt(new Date());

        order = orderRepository.save(order);

        // Logic to create and save CartItem entities
        for (CreateOrderRequest.SelectedItem item : request.getSelectedItems()) {
            CartItem cartItem = new CartItem();
            cartItem.setOrder(order);
            cartItem.setItemId(item.getMenuItemId());
            cartItem.setName(item.getItemName());
            cartItem.setQuantity(item.getQuantity());
            cartItem.setPrice(item.getPrice());
            // Save each CartItem
            cartItemRepository.save(cartItem);
        }

        CreateOrderResponse.Data temp = new CreateOrderResponse.Data(order.getOrderId());

        return new CreateOrderResponse(1, temp, "Order Created.");
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        if(!orderRepository.existsById(orderId)){
            return null;
        }
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public OrderDetailsResponse getOrderDetails(Long userId, Long orderId) throws Exception{
        // Find the order by id and check if it belongs to the user
        Order order = orderRepository.findByOrderIdAndUserId(orderId, userId)
                .orElseThrow(() -> new RuntimeException("Order not found or does not belong to the user"));



        // Convert the order entity to OrderDetailsData
        OrderDetailsResponse.OrderDetails data = mapOrderToOrderDetailsData(order);

        // Construct the response object
        return new OrderDetailsResponse(1, "Success", data);
    }

    private OrderDetailsResponse.OrderDetails mapOrderToOrderDetailsData(Order order) throws Exception{
        OrderDetailsResponse.OrderDetails orderDetails = new OrderDetailsResponse.OrderDetails();

        orderDetails.setOrderId(order.getOrderId());
        orderDetails.setOrderStatus(order.getStatus());
        orderDetails.setCreatedDate(order.getCreatedAt());
//                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        orderDetails.setUserId(order.getUser().getId());
        orderDetails.setStoreId(order.getStore().getStoreId());

        if(order.getRating()!= null){
            orderDetails.setRating(order.getRating().getRating());
            orderDetails.setFeedback(order.getRating().getFeedback());
        } else {
            orderDetails.setRating(null);
            orderDetails.setFeedback(null);
        }

        orderDetails.setPaymentStatus(order.getStatus());

        List<CartItem> cartItems = cartItemRepository.findByOrderOrderId(order.getOrderId());
        List<Long> itemIds = null;
        if(cartItems.size() > 0){
            itemIds = CartItemUtils.extractItemIds(cartItems);
        }

        if(itemIds == null){
            throw new Exception("Menu Items not found");
        }

        List<MenuItem> menuItems = menuItemRepository.findAllById(itemIds);

        orderDetails.setItems(menuItems.stream()
                .map(this::mapMenuItemToDTO)
                .collect(Collectors.toList()));

        return orderDetails;
    }

    private OrderDetailsResponse.OrderDetails.MenuItemDTO mapMenuItemToDTO(MenuItem menuItem) {
        OrderDetailsResponse.OrderDetails.MenuItemDTO menuItemDTO = new OrderDetailsResponse.OrderDetails.MenuItemDTO();
        menuItemDTO.setItemId(menuItem.getMenuItemId());
        menuItemDTO.setStoreId(menuItem.getStoreId());
        menuItemDTO.setItemName(menuItem.getItemName());
        menuItemDTO.setItemImage(menuItem.getItemImage());
        menuItemDTO.setItemPrice(menuItem.getItemPrice());
        menuItemDTO.setType(menuItem.getType());
        menuItemDTO.setCategoryId(menuItem.getCategoryId());
//        menuItemDTO.setCategory(menuItem.getCategory().getName());
        menuItemDTO.setSubCategoryId(menuItem.getSubCategoryId());
//        menuItemDTO.setSubCategory(menuItem.getSubCategory().getName());
        menuItemDTO.setServingType(menuItem.getServingType());
        menuItemDTO.setPortion(menuItem.getPortion());
        menuItemDTO.setInStock(menuItem.getInStock());
        menuItemDTO.setIsRecommended(menuItem.getIsRecommended());
        menuItemDTO.setDescription(menuItem.getDescription());

        return menuItemDTO;
    }

    public OrdersResponse getOrdersForUser(Long userId, String status, Integer limit, Integer offset) throws Exception {
        // Create a PageRequest object for pagination and sorting
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        // Fetch the orders using the repository
        Page<Order> ordersPage = orderRepository.findByUserIdAndStatusOrderByCreatedAtDesc(userId, status, pageRequest);

        // Convert the Page<Order> to List<OrderData>
        List<OrdersResponse.OrderData> orderDataList = ordersPage.getContent().stream()
                .map(this::convertToOrderData)
                .collect(Collectors.toList());

        // Return the response
        return new OrdersResponse(1, "Success", orderDataList);
    }

    private OrdersResponse.OrderData convertToOrderData(Order order) {
        OrdersResponse.OrderData orderData = new OrdersResponse.OrderData();
        orderData.setOrderId(order.getOrderId());
        orderData.setOrderStatus(order.getStatus());
        orderData.setCreatedDate(order.getCreatedAt());
        orderData.setUserId(order.getUser().getId());
        orderData.setStoreId(order.getStore().getStoreId());
        if(order.getRating()!= null) {
            orderData.setRating(order.getRating().getRating());
            orderData.setFeedback(order.getRating().getFeedback());
        }
        orderData.setPaymentStatus(order.getStatus());

        List<CartItem> cartItems = cartItemRepository.findByOrderOrderId(order.getOrderId());
        List<Long> itemIds = null;
        if(cartItems.size() > 0){
            itemIds = CartItemUtils.extractItemIds(cartItems);
        }

        if(itemIds != null){
            List<MenuItem> menuItems = menuItemRepository.findAllById(itemIds);

            orderData.setItems(menuItems.stream()
                    .map(this::convertToItemData)
                    .collect(Collectors.toList()));

        }

        return orderData;
    }

    private OrdersResponse.OrderData.ItemData convertToItemData(MenuItem menuItem) {
        OrdersResponse.OrderData.ItemData itemData = new OrdersResponse.OrderData.ItemData();
        itemData.setItemId(menuItem.getMenuItemId());
        itemData.setStoreId(menuItem.getStoreId());
        itemData.setItemName(menuItem.getItemName());
        itemData.setItemImage(menuItem.getItemImage());
        itemData.setItemPrice(String.valueOf(menuItem.getItemPrice()));
        itemData.setType(menuItem.getType());

        itemData.setCategoryId(menuItem.getCategoryId());
//        itemData.setCategory(menuItem.getCategory());
        itemData.setSubCategoryId(menuItem.getSubCategoryId());
//        itemData.setSubCategory(menuItem.getSubCategory());

        itemData.setServingType(menuItem.getServingType());
        itemData.setPortion(menuItem.getPortion());
        itemData.setInStock(menuItem.getInStock());
        itemData.setIsRecommended(menuItem.getIsRecommended());
        itemData.setDescription(menuItem.getDescription());

        return itemData;
    }


}
