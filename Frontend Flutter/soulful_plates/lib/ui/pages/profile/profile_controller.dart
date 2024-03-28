import 'package:flutter/cupertino.dart';
import 'package:soulful_plates/app_singleton.dart';
import 'package:soulful_plates/model/profile/user_profile.dart';

import '../../../controller/base_controller.dart';
import '../../../utils/shared_prefs.dart';

class ProfileController extends BaseController {
  UserProfile? userProfile;

  bool isEditable = false;

  @override
  void onInit() {
    super.onInit();
    userProfile = AppSingleton.loggedInUserProfile;
    emailEditingController.text = userProfile?.email ?? '';
    mobileEditingController.text = userProfile?.contactNumber ?? '';
    firstNameEditingController.text = userProfile?.username ?? '';
  }

  TextEditingController emailEditingController = TextEditingController();
  TextEditingController mobileEditingController = TextEditingController();
  TextEditingController firstNameEditingController = TextEditingController();

  FocusNode emailFocusNode = FocusNode();
  FocusNode mobileFocusNode = FocusNode();
  FocusNode firstNameFocusNode = FocusNode();

  onSave() async {
    UserProfile userModel = UserProfile(
        username: firstNameEditingController.text,
        email: emailEditingController.text,
        contactNumber: mobileEditingController.text);

    await UserPreference.setValue(
        key: SharedPrefKey.userProfileData.name, value: userModel.toJson());
    // await UserPreference.setValue(
    //     key: SharedPrefKey.token.name, value: userModel.token);
    AppSingleton.loggedInUserProfile = userModel;

    userProfile = userModel;
    isEditable = !isEditable;

    update();
  }
}
