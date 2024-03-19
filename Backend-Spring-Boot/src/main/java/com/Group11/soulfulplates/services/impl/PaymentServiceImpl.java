package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.models.Payment;
import com.Group11.soulfulplates.models.Transaction;
import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.payload.request.PaymentFilterRequest;
import com.Group11.soulfulplates.payload.response.PaymentFilterResponse;
import com.Group11.soulfulplates.repository.*;
import com.Group11.soulfulplates.services.PaymentService;
import com.Group11.soulfulplates.utils.CartItemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;


    @Override
    @Transactional
    public Map<String, Object> createPaymentAndTransaction(CreatePaymentRequest request) throws Exception {
        Transaction transaction = new Transaction();
        if(request.getUserId() != null && request.getUserId() > 0){
            transaction.setUser(userRepository.findById(request.getUserId()).orElseThrow(() -> new Exception("User not found")));
        } else {
            throw new Exception("Invalid User Id");
        }
        transaction.setCardNumber(request.getCardNumber());
        transaction.setCardExpiry(request.getCardExpiry()); // Adjust for your model
        transaction.setCvv(request.getCvv());
        transaction.setStatus("Processing");
        transaction.setCreatedAt(new Date());
        transaction.setUpdatedAt(new Date());

        if(request.getOrderId() == null && request.getOrderId() <= 0) {
            throw new Exception("Invalid Order Id");
        }

        Transaction savedTransaction = transactionRepository.save(transaction);

        if(savedTransaction.getTransactionId()!=null){

            List<CartItem> cartItems = cartItemRepository.findByOrderOrderId(request.getOrderId());
            List<Long> itemIds = null;
            Double totalAmount = null;
            if(cartItems.size() > 0){
                totalAmount = CartItemUtils.getTotalForOrderId(cartItems);
                System.out.println(totalAmount);
                if(totalAmount > request.getAmount().doubleValue()){
                    savedTransaction.setStatus("Failed");
                    transactionRepository.save(savedTransaction);
                    throw new Exception("Payment Failed: Payment Amount is lesser than expected");
                }
            }

            Payment payment = new Payment();
            payment.setTransaction(savedTransaction);
            payment.setOrder(orderRepository.findById(request.getOrderId()).orElseThrow(() -> new Exception("Order not found")));
            payment.setAmount(request.getAmount());
            payment.setStatus("Pending");
            payment.setCreatedAt(new Date());
            payment.setUpdatedAt(new Date());
            if(request.getStoreId() != null && request.getStoreId() > 0) {
                payment.setStore(storeRepository.findById(request.getStoreId()).orElseThrow(() -> new Exception("Store not found")));
            } else {
                throw new Exception("Invalid Store Id");
            }
            Payment savedPayment = paymentRepository.save(payment);
            Map<String, Object> response = new HashMap<>();
            response.put("Transaction_id", savedTransaction.getTransactionId());
            response.put("payment_id", savedPayment.getPaymentId());
            return response;
        } else {
            throw new Exception("Invalid Transaction Id");
        }
    }

    @Override
    public void updatePaymentStatus(Long paymentId, Long transactionId, String status) throws Exception {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new Exception("Payment not found"));

        // Optionally verify the transactionId matches if needed
        if (!payment.getTransaction().getTransactionId().equals(transactionId)) {
            throw new Exception("Transaction ID does not match the payment record");
        }

        payment.setStatus(status);
        payment.setUpdatedAt(new Date());
        paymentRepository.save(payment);
    }

    @Override
    public List<PaymentFilterResponse> filterPayments(Long userId, String status, Integer limit, Integer offset) {
        System.out.println(limit);
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Payment> payments = paymentRepository.findByTransactionUserIdAndStatusOrderByCreatedAtDesc(
                userId,
                status,
                pageRequest);

        return payments.stream().map(payment -> {
            Transaction transaction = payment.getTransaction();
            return new PaymentFilterResponse(
                    transaction.getUser().getId(),
                    payment.getStore().getStoreId(),
                    payment.getAmount(),
                    payment.getOrder().getOrderId(),
                    "12**-****-**61",
                    transaction.getCardExpiry(),
                    "***",
                    payment.getStatus(),
                    payment.getPaymentId(),
                    transaction.getTransactionId(),
                    transaction.getStatus(),
                    transaction.getCreatedAt(),
                    transaction.getUpdatedAt()
            );
        }).collect(Collectors.toList());
    }
}
