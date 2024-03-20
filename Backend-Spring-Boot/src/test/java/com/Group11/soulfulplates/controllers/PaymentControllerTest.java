//package com.Group11.soulfulplates.controllers;
//
//import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
//import com.Group11.soulfulplates.payload.request.UpdatePaymentStatusRequest;
//import com.Group11.soulfulplates.services.PaymentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class PaymentControllerTest {
//
//    @InjectMocks
//    private PaymentController paymentController;
//
//    @Mock
//    private PaymentService paymentService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createPaymentAndTransaction_Success() throws Exception {
//        // Given
//        CreatePaymentRequest request = new CreatePaymentRequest();
//        Map<String, Object> response = new HashMap<>();
//        when(paymentService.createPaymentAndTransaction(request)).thenReturn(response);
//
//        // When
//        ResponseEntity<?> responseEntity = paymentController.createPaymentAndTransaction(request);
//
//        // Then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        verify(paymentService, times(1)).createPaymentAndTransaction(request);
//    }
//
//    @Test
//    void createPaymentAndTransaction_Failure() throws Exception {
//        // Given
//        CreatePaymentRequest request = new CreatePaymentRequest();
//        when(paymentService.createPaymentAndTransaction(request)).thenThrow(new RuntimeException("Error creating payment"));
//
//        // When
//        ResponseEntity<?> responseEntity = paymentController.createPaymentAndTransaction(request);
//
//        // Then
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        verify(paymentService, times(1)).createPaymentAndTransaction(request);
//    }
//
//    @Test
//    void updatePaymentStatus_Success() throws Exception {
//        // Given
//        UpdatePaymentStatusRequest request = new UpdatePaymentStatusRequest();
//
//        // When
//        ResponseEntity<?> responseEntity = paymentController.updatePaymentStatus(request);
//
//        // Then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        verify(paymentService, times(1)).updatePaymentStatus(request.getPaymentId(), request.getTransactionId(), request.getStatus());
//    }
//
//    @Test
//    void updatePaymentStatus_Failure() throws Exception {
//        // Given
//        UpdatePaymentStatusRequest request = new UpdatePaymentStatusRequest();
//        request.setPaymentId(1L);
//        request.setTransactionId(1L);
//        request.setStatus("Failed");
//
//        doThrow(new RuntimeException("Error updating payment status")).when(paymentService)
//                .updatePaymentStatus(anyLong(), anyLong(), anyString());
//
//        // When
//        ResponseEntity<?> responseEntity = paymentController.updatePaymentStatus(request);
//
//        // Then
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        verify(paymentService, times(1)).updatePaymentStatus(request.getPaymentId(),
//                request.getTransactionId(), request.getStatus());
//    }
//
//    @Test
//    void getBuyerPaymentHistory_Success() throws Exception {
//        // Given
//        Long userId = 1L;
//        int limit = 20;
//        int offset = 0;
//        String status = "completed";
//
//        List<Map<String, Object>> payments = new ArrayList<>();
//        // Add some sample payments to the list
//
//        when(paymentService.getBuyerPaymentHistory(userId, limit, offset, status))
//                .thenReturn(payments);
//
//        // When
//        ResponseEntity<?> responseEntity = paymentController.getBuyerPaymentHistory(userId, limit, offset, status);
//
//        // Then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(1, ((Map<String, Object>) responseEntity.getBody()).get("code"));
//        assertEquals("Success", ((Map<String, Object>) responseEntity.getBody()).get("description"));
//        assertEquals(payments, ((Map<String, Object>) responseEntity.getBody()).get("data"));
//    }
//
//    @Test
//    void getBuyerPaymentHistory_Exception_ReturnsBadRequest() throws Exception {
//        // Given
//        Long userId = 1L;
//        int limit = 20;
//        int offset = 0;
//        String status = "completed";
//
//        when(paymentService.getBuyerPaymentHistory(userId, limit, offset, status))
//                .thenThrow(new RuntimeException("Internal server error"));
//
//        // When
//        ResponseEntity<?> responseEntity = paymentController.getBuyerPaymentHistory(userId, limit, offset, status);
//
//        // Then
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(-1, ((Map<String, Object>) responseEntity.getBody()).get("code"));
//        assertEquals("Error: Internal server error", ((Map<String, Object>) responseEntity.getBody()).get("description"));
//    }
//
//    @Test
//    void getSellerPaymentHistory_Success() throws Exception {
//        // Given
//        Long storeId = 1L;
//        int limit = 20;
//        int offset = 0;
//        String status = "completed";
//
//        List<Map<String, Object>> payments = new ArrayList<>();
//        when(paymentService.getSellerPaymentHistory(storeId, limit, offset, status))
//                .thenReturn(payments);
//
//        // When
//        ResponseEntity<?> responseEntity = paymentController.getSellerPaymentHistory(storeId, limit, offset, status);
//
//        // Then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(1, ((Map<String, Object>) responseEntity.getBody()).get("code"));
//        assertEquals("Success", ((Map<String, Object>) responseEntity.getBody()).get("description"));
//        assertEquals(payments, ((Map<String, Object>) responseEntity.getBody()).get("data"));
//    }
//
//    @Test
//    void getSellerPaymentHistory_Exception_ReturnsBadRequest() throws Exception {
//        // Given
//        Long storeId = 1L;
//        int limit = 20;
//        int offset = 0;
//        String status = "completed";
//
//        when(paymentService.getSellerPaymentHistory(storeId, limit, offset, status))
//                .thenThrow(new RuntimeException("Internal server error"));
//
//        // When
//        ResponseEntity<?> responseEntity = paymentController.getSellerPaymentHistory(storeId, limit, offset, status);
//
//        // Then
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(-1, ((Map<String, Object>) responseEntity.getBody()).get("code"));
//        assertEquals("Error: Internal server error", ((Map<String, Object>) responseEntity.getBody()).get("description"));
//    }
//}
//
