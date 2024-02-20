import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/model/profile/user_profile.dart';
import 'package:soulful_plates/utils/shared_prefs.dart';

import '../../../app_singleton.dart';
import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../network/network_interfaces/i_dio_singleton.dart';
import '../../../network/network_utils/api_call.dart';
import '../../../routing/route_names.dart';
import '../../../utils/extensions.dart';
import '../../../utils/utils.dart';

class LoginController extends BaseController {
  bool obscureText = true;

  TextEditingController emailEditingController =
      TextEditingController(text: 'test13341@gmail.com');
  TextEditingController passwordEditingController =
      TextEditingController(text: 'Test@1234');

  FocusNode emailFocusNode = FocusNode();
  FocusNode passwordFocusNode = FocusNode();

  final formKey = GlobalKey<FormState>();

  @override
  void onInit() {
    super.onInit();
  }

  void login() async {
    try {
      setLoaderState(ViewStateEnum.busy);
      final UserProfile? userModel = await ApiCall().call<UserProfile>(
          method: RequestMethod.get,
          endPoint: "/api/login",
          obj: UserProfile(),
          apiCallType: ApiCallType.simple,
          queryParameters: {
            "email": emailEditingController.text.trim(),
            "password": passwordEditingController.text.trim()
          });
      if (userModel != null) {
        await UserPreference.setValue(
            key: SharedPrefKey.userProfileData.name, value: userModel.toJson());
        // await UserPreference.setValue(
        //     key: SharedPrefKey.token.name, value: userModel.token);
        AppSingleton.loggedInUserProfile = userModel;
        setLoaderState(ViewStateEnum.idle);
        Utils.showSuccessToast("Logged in successfully.", false);
        onWidgetDidBuild(callback: () {
          Get.offAllNamed(dashboardViewRoute);
        });
      } else {
        setLoaderState(ViewStateEnum.idle);
        Utils.showSuccessToast(
            "Issue while logging in. Please try again later.", true);
      }
    } catch (e) {
      setLoaderState(ViewStateEnum.idle);
      print('This is error $e');
      Utils.showSuccessToast(e.toString(), true);
    }
  }
}