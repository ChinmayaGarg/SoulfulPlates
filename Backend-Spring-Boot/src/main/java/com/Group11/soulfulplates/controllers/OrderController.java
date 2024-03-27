package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Order;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.request.GetOrderDetailsRequest;
import com.Group11.soulfulplates.payload.request.GetOrdersRequest;
import com.Group11.soulfulplates.payload.request.GetStoreOrders;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.payload.response.OrderDetailsResponse;
import com.Group11.soulfulplates.payload.response.OrdersResponse;
import com.Group11.soulfulplates.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_BUYER') or hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody(required = false) CreateOrderRequest request) {
        CreateOrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateOrderStatus(@RequestBody(required = false) UpdateOrderStatusRequest request) {
        Order order = orderService.updateOrderStatus(request.getOrderId(), request.getStatus());
        if (order != null) {
            return ResponseEntity.ok(new MessageResponse(1, null, "Order status updated."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(-1, null, "Error updating order status."));
        }
    }

    static class UpdateOrderStatusRequest {
        private Long orderId;
        private String status;

        // Getters and Setters
        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    @GetMapping("/getDetails")
    @PreAuthorize("hasRole('ROLE_BUYER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderDetailsResponse> getOrderDetails(@RequestBody GetOrderDetailsRequest request) {
        try {
            OrderDetailsResponse response = orderService.getOrderDetails(request.getUserId(), request.getOrderId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {

            // Define the error message and code
            String responseDescription = "Error getting order details:";
            int responseCode = -1;

            // Create a new MessageResponse object with the above details
            OrderDetailsResponse messageResponse = new OrderDetailsResponse(responseCode, responseDescription, null);
            return ResponseEntity.badRequest()
                    .body(messageResponse);
        }
    }

    @GetMapping("/getForUser")
    @PreAuthorize("hasRole('ROLE_BUYER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOrdersForUser(@RequestBody GetOrdersRequest request) {
        try {
            // Extract parameters from the request for clarity
            Long userId = request.getUserId();
            String status = request.getStatus();
            int limit = request.getLimit();
            int offset = request.getOffset();

            // Call the service method with extracted parameters
            OrdersResponse response = orderService.getOrdersForUser(userId, status, limit, offset);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Extract the error message from the exception
            String errorMessage = "Error getting order details: " + e.getMessage();

            // Instantiate the response object with details
            OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse(-1, errorMessage, null);

            // Return a bad request response entity with the custom order details response
            return ResponseEntity.badRequest().body(orderDetailsResponse);
        }
    }

    @GetMapping("/getForStore")
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity getOrdersForStore(@RequestBody GetStoreOrders request) {
        try {
            // Extract parameters from the request for clarity
            Long storeId = request.getStoreId();
            String status = request.getStatus();
            int limit = request.getLimit();
            int offset = request.getOffset();

            // Call the service method with the extracted parameters
            OrdersResponse response = orderService.getOrdersForStore(storeId, status, limit, offset);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Construct the error message
            String errorMessage = "Error getting order details: " + e.getMessage();

            // Create the response object
            OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse(-1, errorMessage, null);

            // Return a ResponseEntity indicating a bad request with the detailed response
            return ResponseEntity.badRequest().body(orderDetailsResponse);
        }
    }

}
