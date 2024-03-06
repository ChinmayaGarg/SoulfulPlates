package com.Group11.soulfulplates.payload.response;

import com.Group11.soulfulplates.models.Store;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private final List<String> roles;
  private String firstname;
  private String contactNumber;
  private boolean notificationFlag;
  private Long sellerId;
  private String sellerName;
  private String sellerEmail;
  private String sellerContactNumber;


  // Constructor without Seller
  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, String contactNumber, String firstname, boolean notificationFlag) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.firstname = firstname;
    this.notificationFlag = notificationFlag;
    this.contactNumber = contactNumber;
  }


  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, String contactNumber, String firstname, boolean notificationFlag, Long sellerId, String sellerName, String sellerEmail, String sellerContactNumber) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.firstname = firstname;
    this.notificationFlag = notificationFlag;
    this.contactNumber = contactNumber;
    this.sellerId = sellerId;
    this.sellerName = sellerName;
    this.sellerEmail = sellerEmail;
    this.sellerContactNumber = sellerContactNumber;
  }
}
