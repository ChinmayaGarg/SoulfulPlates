import 'package:flutter/cupertino.dart';

import '../../../app_singleton.dart';
import '../../../controller/base_controller.dart';
import '../../../model/profile/user_profile.dart';

class RestaurantDetailController extends BaseController {
  UserProfile? userProfile;

  bool isEditable = true;

  @override
  void onInit() {
    super.onInit();
    userProfile = AppSingleton.loggedInUserProfile;
  }

  TextEditingController emailEditingController = TextEditingController();
  TextEditingController mobileEditingController = TextEditingController();
  TextEditingController firstNameEditingController = TextEditingController();
  TextEditingController streetEditingController = TextEditingController();
  TextEditingController cityEditingController = TextEditingController();
  TextEditingController stateEditingController = TextEditingController();
  TextEditingController postalCodeEditingController = TextEditingController();

  FocusNode emailFocusNode = FocusNode();
  FocusNode mobileFocusNode = FocusNode();
  FocusNode firstNameFocusNode = FocusNode();
  FocusNode streetFocusNode = FocusNode();
  FocusNode cityFocusNode = FocusNode();
  FocusNode stateFocusNode = FocusNode();
  FocusNode postalCodeFocusNode = FocusNode();
}
