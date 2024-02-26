package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    public Address createAddress(Address address);

    public Address getAddressById(Long id);

    public List<Address> getAllAddresses();

    public Address updateAddress(Address address);

    public void deleteAddress(Long id);
}
