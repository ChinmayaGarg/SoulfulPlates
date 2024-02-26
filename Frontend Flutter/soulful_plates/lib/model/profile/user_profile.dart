import 'dart:convert';

import 'package:get/get.dart';
import 'package:soulful_plates/Utils/Extensions.dart';
import 'package:soulful_plates/network/network_interfaces/generic_model.dart';

class UserProfile extends GenericModel {
  UserProfile({
    String? id,
    String? username,
    String? email,
    String? phoneNumber,
    String? image,
  }) {
    _id = id;
    _username = username;
    _email = email;
    _phoneNumber = phoneNumber;
    _image = image;
  }

  UserProfile.fromJson(dynamic json) {
    _id = json['id'];
    _username = json['username'];
    _email = json['email'];
    _phoneNumber = json['contact_number'];
    _image = json['image'];
  }

  static UserProfile? fromRawJson(String str) {
    if (str.isNotEmpty) {
      return UserProfile.fromJson(jsonDecode(str));
    } else {
      return null;
    }
  }

  String toRawJson() => jsonEncode(toJson());

  String? _id;
  String? _username;
  String? _email;
  String? _phoneNumber;
  String? _image;
  UserProfile copyWith({
    String? id,
    String? username,
    String? email,
    String? phoneNumber,
    String? image,
  }) =>
      UserProfile(
        id: id ?? _id,
        username: username ?? _username,
        email: email ?? _email,
        phoneNumber: phoneNumber ?? _phoneNumber,
        image: image ?? _image,
      );
  String? get id => _id;
  String? get username => _username;
  String? get email => _email;
  String? get phoneNumber => _phoneNumber;
  String? get image => _image;

  String shortName() {
    if (username.isNotNullOrEmpty) {
      return username!.substring(0, 1).capitalizeFirst ?? '';
    }
    return '-';
  }

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = _id;
    map['username'] = _username;
    map['email'] = _email;
    map['contact_number'] = _phoneNumber;
    map['image'] = _image;
    return map;
  }

  @override
  String toString() {
    return 'UserProfile{_id: $_id, _username: $_username, _email: $_email, _phoneNumber: $_phoneNumber, _image: $_image}';
  }

  @override
  from(json) {
    return UserProfile.fromJson(json);
  }
}
