package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {
    boolean existsById(Long id);

    public Seller createSeller(Seller seller);

    public Optional<Seller> getSellerById(Long id);

    public List<Seller> getAllSellers();

    public Seller updateSeller(Seller seller);

    public void deleteSeller(Long id);
}
