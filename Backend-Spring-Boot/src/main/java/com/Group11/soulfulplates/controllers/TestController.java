package com.Group11.SoulfulPlates.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/buyer")
  @PreAuthorize("hasRole('ROLE_BUYER')")
  public String userAccess() {
    return "Buyer Content.";
  }

  @GetMapping("/seller")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public String moderatorAccess() {
    return "Seller Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
