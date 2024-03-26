package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.payload.request.PaymentFilterRequestBuyer;
import com.Group11.soulfulplates.payload.request.PaymentFilterRequestSeller;
import com.Group11.soulfulplates.payload.request.UpdatePaymentStatusRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.payload.response.PaymentFilterResponse;
import com.Group11.soulfulplates.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPaymentAndTransaction_Success() throws Exception {
        // Given
        CreatePaymentRequest request = new CreatePaymentRequest();
        Map<String, Object> response = new HashMap<>();
        when(paymentService.createPaymentAndTransaction(request)).thenReturn(response);

        // When
        ResponseEntity<?> responseEntity = paymentController.createPaymentAndTransaction(request);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(paymentService, times(1)).createPaymentAndTransaction(request);
    }

    @Test
    void createPaymentAndTransaction_Failure() throws Exception {
        // Given
        CreatePaymentRequest request = new CreatePaymentRequest();
        when(paymentService.createPaymentAndTransaction(request)).thenThrow(new RuntimeException("Error creating payment"));

        // When
        ResponseEntity<?> responseEntity = paymentController.createPaymentAndTransaction(request);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        verify(paymentService, times(1)).createPaymentAndTransaction(request);
    }

    @Test
    void updatePaymentStatus_Success() throws Exception {
        // Given
        UpdatePaymentStatusRequest request = new UpdatePaymentStatusRequest();

        // When
        ResponseEntity<?> responseEntity = paymentController.updatePaymentStatus(request);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(paymentService, times(1)).updatePaymentStatus(request.getPaymentId(), request.getTransactionId(), request.getStatus());
    }

    @Test
    void updatePaymentStatus_Failure() throws Exception {
        // Given
        UpdatePaymentStatusRequest request = new UpdatePaymentStatusRequest();
        request.setPaymentId(1L);
        request.setTransactionId(1L);
        request.setStatus("Failed");

        doThrow(new RuntimeException("Error updating payment status")).when(paymentService)
                .updatePaymentStatus(anyLong(), anyLong(), anyString());

        // When
        ResponseEntity<?> responseEntity = paymentController.updatePaymentStatus(request);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        verify(paymentService, times(1)).updatePaymentStatus(request.getPaymentId(),
                request.getTransactionId(), request.getStatus());
    }

    @Test
    void testFilterSellerPayments_Success() throws Exception {
        // Given
        PaymentFilterRequestSeller request = new PaymentFilterRequestSeller();
        List<PaymentFilterResponse> payments = new ArrayList<>();
        when(paymentService.filterPayments(anyLong(), anyString(), anyInt(), anyInt()))
                .thenReturn(payments);

        // When
        ResponseEntity<?> responseEntity = paymentController.filterSellerPayments(request);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((MessageResponse) responseEntity.getBody()).getCode());
        assertEquals("Success", ((MessageResponse) responseEntity.getBody()).getDescription());
        assertEquals(payments, ((MessageResponse) responseEntity.getBody()).getData());
    }

//    @Test
//    public void testFilterSellerPayments_Exception() {
//        PaymentFilterRequestSeller request = new PaymentFilterRequestSeller();
//        request.setStoreId(1L);
//        request.setStatus("success");
//        request.setLimit(10);
//        request.setOffset(0);
//
//        when(paymentService.filterPayments(anyLong(), anyString(), anyInt(), anyInt())).thenThrow(new RuntimeException("Test Exception"));
//
//        ResponseEntity<?> responseEntity = paymentController.filterSellerPayments(request);
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals(-1, ((MessageResponse) responseEntity.getBody()).getCode());
//        assertEquals("Failure", ((MessageResponse) responseEntity.getBody()).getDescription());
//        assertEquals(null, ((MessageResponse) responseEntity.getBody()).getData());
//
//        verify(paymentService, times(1)).filterPayments(anyLong(), anyString(), anyInt(), anyInt());
//    }

    @Test
    public void testFilterPayments_Success() {
        PaymentFilterRequestBuyer request = new PaymentFilterRequestBuyer();
        request.setUserId(1L);
        request.setStatus("success");
        request.setLimit(10);
        request.setOffset(0);

        List<PaymentFilterResponse> mockResponse = new ArrayList<>();
        PaymentFilterResponse responseItem = new PaymentFilterResponse();

        responseItem.setUserId(1L);
        responseItem.setStatus("success");
        mockResponse.add(responseItem);

        when(paymentService.filterPayments(anyLong(), anyString(), anyInt(), anyInt())).thenReturn(mockResponse);

        ResponseEntity<?> responseEntity = paymentController.filterPayments(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((MessageResponse) responseEntity.getBody()).getCode());
        assertEquals("Success", ((MessageResponse) responseEntity.getBody()).getDescription());
        assertEquals(mockResponse, ((MessageResponse) responseEntity.getBody()).getData());

        verify(paymentService, times(1)).filterPayments(anyLong(), anyString(), anyInt(), anyInt());
    }
//    @Test
//    public void testFilterPayments_Exception() {
//        PaymentFilterRequestBuyer request = new PaymentFilterRequestBuyer();
//        request.setUserId(1L);
//        request.setStatus("success");
//        request.setLimit(10);
//        request.setOffset(0);
//
//        when(paymentService.filterPayments(anyLong(), anyString(), anyInt(), anyInt())).thenThrow(new RuntimeException("Test Exception"));
//
//        ResponseEntity<?> responseEntity = paymentController.filterPayments(request);
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals(-1, ((MessageResponse) responseEntity.getBody()).getCode());
//        assertEquals("Failure", ((MessageResponse) responseEntity.getBody()).getDescription());
//        assertEquals(null, ((MessageResponse) responseEntity.getBody()).getData());
//
//        verify(paymentService, times(1)).filterPayments(anyLong(), anyString(), anyInt(), anyInt());
//    }


}

