package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.repository.SellerRepository;
import com.Group11.soulfulplates.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
//public class SellerServiceImpl implements SellerService {
//
//    private final SellerRepository sellerRepository;
//
//    @Autowired
//    public SellerServiceImpl(SellerRepository sellerRepository) {
//        this.sellerRepository = sellerRepository;
//    }
//
//    @Override
//    public boolean existsById(Long id) {
//        return sellerRepository.existsById(id);
//    }
//}

    import com.Group11.soulfulplates.models.Seller;
    import java.util.List;
    import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    //    @Autowired
//    public SellerServiceImpl(SellerRepository sellerRepository) {
//        this.sellerRepository = sellerRepository;
//    }

    @Override
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller updateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return sellerRepository.existsById(id);
    }
}
