import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/model/profile/user_profile.dart';
import 'package:soulful_plates/network/network_interfaces/end_points.dart';
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
      TextEditingController(text: '');
  TextEditingController passwordEditingController =
      TextEditingController(text: '');

  FocusNode emailFocusNode = FocusNode();
  FocusNode passwordFocusNode = FocusNode();

  final formKey = GlobalKey<FormState>();

  void login() async {
    try {
      setLoaderState(ViewStateEnum.busy);
      final UserProfile? userModel = await ApiCall().call<UserProfile>(
          method: RequestMethod.post,
          endPoint: EndPoints.login,
          obj: UserProfile(),
          apiCallType: ApiCallType.simple,
          parameters: {
            "username": emailEditingController.text.trim(),
            "password": passwordEditingController.text.trim()
          });
      if (userModel != null) {
        await UserPreference.setValue(
            key: SharedPrefKey.userProfileData.name,
            value: userModel.toRawJson());
        AppSingleton.loggedInUserProfile = userModel;
        print(
            "This is AppSingleton.loggedInUserProfile ${AppSingleton.loggedInUserProfile}");
        print(
            "This is AppSingleton.loggedInUserProfile ${userModel.toRawJson()}");
        print(
            "This is AppSingleton.loggedInUserProfile ${UserPreference.getValue(key: SharedPrefKey.userProfileData.name)}");

        setLoaderState(ViewStateEnum.idle);
        Utils.showSuccessToast("Logged in successfully.", false);
        await UserPreference.setValue(
            key: SharedPrefKey.isLogin.name, value: true);
        if (!AppSingleton.isBuyer() && userModel.sellerName.isNullOrEmpty) {
          onWidgetDidBuild(callback: () {
            Get.offAllNamed(storeDetailsViewRoute);
          });
        } else {
          onWidgetDidBuild(callback: () {
            Get.offAllNamed(dashboardViewRoute);
          });
        }
      } else {
        setLoaderState(ViewStateEnum.idle);
        Utils.showSuccessToast(
            "Issue while logging in. Please try again later.", true);
      }
    } catch (e) {
      setLoaderState(ViewStateEnum.idle);
      debugPrint('This is error $e');
      Utils.showSuccessToast(e.toString(), true);
    }
  }
}
