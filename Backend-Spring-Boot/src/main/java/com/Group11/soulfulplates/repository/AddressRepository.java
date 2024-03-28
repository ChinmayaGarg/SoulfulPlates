package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Address;
import com.Group11.soulfulplates.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);

    @Query(nativeQuery = true, value = "SELECT s.*, a.latitude AS lat, a.longitude AS lon FROM Stores s JOIN Addresses a ON a.user_id = s.store_id")
    List<Map<String, Object>> findAllStoresLatLon();
}
