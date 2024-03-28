package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByUser_Id(Long id);

}
