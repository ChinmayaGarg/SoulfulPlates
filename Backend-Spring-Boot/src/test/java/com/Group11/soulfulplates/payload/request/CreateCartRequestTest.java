//package com.Group11.soulfulplates.payload.request;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//public class CreateCartRequestTest {
//
//    @Test
//    public void testEmptyConstructorSetsDefaultValues() {
//        CreateCartRequest request = new CreateCartRequest();
//
//        assertNull(request.getUserId());
//        assertNull(request.getServiceProviderId());
//    }
//
//    @Test
//    public void testParameterizedConstructorSetsFieldValuesCorrectly() {
//        Long expectedUserId = 1L;
//        Long expectedServiceProviderId = 2L;
//
//        CreateCartRequest request = new CreateCartRequest(expectedUserId, expectedServiceProviderId);
//
//        assertEquals(expectedUserId, request.getUserId());
//        assertEquals(expectedServiceProviderId, request.getServiceProviderId());
//    }
//
//    @Test
//    public void testSetterMethodsUpdateFieldsCorrectly() {
//        CreateCartRequest request = new CreateCartRequest();
//
//        Long newUserId = 10L;
//        Long newServiceProviderId = 20L;
//
//        request.setUserId(newUserId);
//        request.setServiceProviderId(newServiceProviderId);
//
//        assertEquals(newUserId, request.getUserId());
//        assertEquals(newServiceProviderId, request.getServiceProviderId());
//    }
//}
