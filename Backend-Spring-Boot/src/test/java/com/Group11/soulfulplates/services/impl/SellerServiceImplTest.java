//package com.Group11.soulfulplates.services.impl;
//
//import com.Group11.soulfulplates.models.Seller;
//import com.Group11.soulfulplates.models.Store;
//import com.Group11.soulfulplates.repository.SellerRepository;
//import com.Group11.soulfulplates.repository.StoreRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//public class SellerServiceImplTest {
//    @InjectMocks
//    private StoreServiceImpl sellerService;
//
//    @Mock
//    private StoreRepository storeRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreateSeller_SuccessfullyCreated() {
//        // Given
//        Store seller = new Store();
//
//        when(storeRepository.save(seller)).thenReturn(seller);
//
//        // When
//        Store createdSeller = sellerService.createStore(seller);
//
//        // Then
//        assertEquals(seller, createdSeller);
//        verify(sellerRepository, times(1)).save(seller);
//    }
//
//    @Test
//    void testGetSellerById_ReturnsSeller() {
//        // Given
//        Long id = 1L;
//        Seller seller = new Seller();
//        seller.setSellerId(id);
//
//        when(sellerRepository.findById(id)).thenReturn(Optional.of(seller));
//
//        // When
//        Optional<Seller> result = sellerService.getSellerById(id);
//
//        // Then
//        assertTrue(result.isPresent());
//        assertEquals(seller, result.get());
//        verify(sellerRepository, times(1)).findById(id);
//    }
//
//    @Test
//    void testGetAllSellers_ReturnsAllSellers() {
//        // Given
//        List<Seller> sellers = new ArrayList<>();
//        sellers.add(new Seller());
//        sellers.add(new Seller());
//
//        when(sellerRepository.findAll()).thenReturn(sellers);
//
//        // When
//        List<Seller> result = sellerService.getAllSellers();
//
//        // Then
//        assertEquals(sellers, result);
//        verify(sellerRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testUpdateSeller_SuccessfullyUpdated() {
//        // Given
//        Seller seller = new Seller();
//
//        when(sellerRepository.save(seller)).thenReturn(seller);
//
//        // When
//        Seller updatedSeller = sellerService.updateSeller(seller);
//
//        // Then
//        assertEquals(seller, updatedSeller);
//        verify(sellerRepository, times(1)).save(seller);
//    }
//
//    @Test
//    void testDeleteSeller_SuccessfullyDeleted() {
//        // Given
//        Long id = 1L;
//
//        // When
//        sellerService.deleteSeller(id);
//
//        // Then
//        verify(sellerRepository, times(1)).deleteById(id);
//    }
//}
