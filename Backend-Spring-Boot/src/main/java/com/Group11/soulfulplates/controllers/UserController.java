package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Address;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.AddressRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.services.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import static java.lang.Math.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @PutMapping("/toggle-notification/{userId}")
    public ResponseEntity<MessageResponse> toggleNotificationFlag(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));


        // Toggle the value of notificationFlag
        user.setNotificationFlag(!user.isNotificationFlag());

        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(1, "Notification flag toggled successfully!", null));
    }

    @PostMapping("/addresses/{userId}")
    public ResponseEntity<MessageResponse> createAddressForUser(@PathVariable Long userId, @RequestBody Address address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        address.setUser(user);
        addressRepository.save(address);

        return ResponseEntity.ok(new MessageResponse(1, "Address saved successfully!", null));
    }


    @GetMapping("/addresses/{userId}")
    public ResponseEntity<MessageResponse> getAllAddressesForUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Retrieve all addresses associated with the given user ID
        List<Address> addresses = addressRepository.findByUser(user);

        // Remove the user information from each address object
        addresses.forEach(address -> address.setUser(null));

        // Create a response message with the addresses list directly under the 'data' field
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode responseData = objectMapper.createArrayNode();
        for (Address address : addresses) {
            // Convert the address to a JSON node
            ObjectNode addressNode = objectMapper.convertValue(address, ObjectNode.class);
            // Remove the "user" field from the address node
            addressNode.remove("user");
            // Add the address node to the response data
            responseData.add(addressNode);
        }

        return ResponseEntity.ok(new MessageResponse(1, "Addresses fetched successfully!", responseData));
    }



    // Update an existing address for a user
    @PutMapping("/addresses/{userId}/{addressId}")
    public ResponseEntity<MessageResponse> updateAddressForUser(@PathVariable Long userId, @PathVariable Long addressId, @RequestBody Address addressDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

        address.setStreet(addressDetails.getStreet());
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setCountry(addressDetails.getCountry());
        address.setLatitude(addressDetails.getLatitude());
        address.setLongitude(addressDetails.getLongitude());
        address.setLabel(addressDetails.getLabel());

        addressRepository.save(address);

        return ResponseEntity.ok(new MessageResponse(1, "Address updated successfully!", null));
    }

    // Delete an address for a user
    @DeleteMapping("/addresses/{userId}/{addressId}")
    public ResponseEntity<MessageResponse> deleteAddressForUser(@PathVariable Long userId, @PathVariable Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

        addressRepository.delete(address);

        return ResponseEntity.ok(new MessageResponse(1, "Address deleted successfully!", null));
    }

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/image/{userId}")
    public ResponseEntity<MessageResponse> updateUserImage(@PathVariable Long userId,
                                                           @RequestParam("file") MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));


        // Check if the uploaded file is not empty
        if (file.isEmpty()) {
            return ResponseEntity.ok(new MessageResponse(-1, "Failed to store empty file.", null));
        }

        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);
        String fileNameWithoutExtension = StringUtils.stripFilenameExtension(originalFilename);
        String fileName = StringUtils.cleanPath(fileNameWithoutExtension + "_" + System.currentTimeMillis() + "." + fileExtension);

        try {

            Path uploadsDir = Paths.get(uploadPath);

            if (!Files.exists(uploadsDir)) {
                Files.createDirectories(uploadsDir);
            }
            Path filePath = uploadsDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            // Update the user's profile image URL
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();
            user.setProfileImageUrl(fileUrl);

            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse(1, "User image updated successfully!", user.getProfileImageUrl()));
        } catch (IOException e) {
            return ResponseEntity.ok(new MessageResponse(-1, "Failed to store file " + fileName + ". Please try again!", null));
        }
    }

    @GetMapping("/latlong/{addressId}/{maxDistance}")
    public ResponseEntity<MessageResponse> getUserAndNearestStore(
            @PathVariable Long addressId,
            @PathVariable Double maxDistance) {

        try {
            // Fetch the address by addressId
            Address userAddress = addressRepository.findById(addressId)
                    .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

            // Check if the fetched address belongs to the given user
            if (userAddress == null) {
                throw new RuntimeException("Address does not belong to the user with id: " + addressId);
            }

            // Get the latitude and longitude of the user's address
            Double userLatitude = userAddress.getLatitude();
            Double userLongitude = userAddress.getLongitude();

            // Get all stores' latitude and longitude
            List<Map<String, Object>> storesLatLon = addressService.getAllStoresLatLon();

            // Find the nearest store within the specified maximum distance
            Map<String, Object> nearestStore = null;
            double minDistance = Double.MAX_VALUE;

            for (Map<String, Object> store : storesLatLon) {
                Double storeLatitude = (Double) store.get("lat");
                Double storeLongitude = (Double) store.get("lon");

                // Calculate distance using Haversine formula
                double distance = calculateDistance(userLatitude, userLongitude, storeLatitude, storeLongitude);

                // Check if the current store is within the specified maximum distance of the user's address
                if (distance <= maxDistance && distance < minDistance) {
                    minDistance = distance;
                    nearestStore = store;
                }
            }

            if (nearestStore == null) {
                throw new RuntimeException("No store found within " + maxDistance + " km of the user's address");
            }

            return ResponseEntity.ok(new MessageResponse(1, "Nearest store within " + maxDistance + " km found", nearestStore));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse(-1, e.getMessage(), null));
        }

    }

    // Method to calculate distance using Haversine formula
    private Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double sinLatDistanceOver2 = sin(latDistance / 2);
        double cosLat1 = cos(toRadians(lat1));
        double cosLat2 = cos(toRadians(lat2));
        double sinLonDistanceOver2 = sin(lonDistance / 2);
        double a = sinLatDistanceOver2 * sinLatDistanceOver2
                + cosLat1 * cosLat2 * sinLonDistanceOver2 * sinLonDistanceOver2;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
