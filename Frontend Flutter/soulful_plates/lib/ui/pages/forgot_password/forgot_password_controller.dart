import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/Utils/Extensions.dart';

import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../routing/route_names.dart';
import '../../../utils/utils.dart';

class ForgotPasswordController extends BaseController {
  var formKey = GlobalKey<FormState>();
  var formKeyOTP = GlobalKey<FormState>();
  TextEditingController emailController = TextEditingController();
  TextEditingController verificationCodeController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController confirmPasswordController = TextEditingController();

  FocusNode emailFocusNode = FocusNode();
  FocusNode verificationCodeFocusNode = FocusNode();
  FocusNode passwordFocusNode = FocusNode();
  FocusNode confirmPasswordFocusNode = FocusNode();
  bool isPasswordHide = true;
  bool isConfirmPasswordHide = true;

  bool isUppar = false,
      isLower = false,
      isSpecialCharacter = false,
      isNumber = false;

  ForgotPasswordStatus? forgotPasswordStatus =
      ForgotPasswordStatus.enterUsername;
  @override
  void onInit() {
    super.onInit();
    if (Get.arguments != null && Get.arguments.runtimeType == String) {
      emailController.text = Get.arguments;
    }
  }

  void navigateToNext() {
    forgotPasswordStatus = ForgotPasswordStatus.resetPassword;
    update();
  }

  void validateAndResetPassword() async {
    if (!verificationCodeController.text.isNotNullOrEmpty) {
      Utils.showSuccessToast(
          "Please enter the 4 digit code sent to your email.", false);
      return;
    }
    setLoaderState(ViewStateEnum.busy);
    await Future.delayed(const Duration(seconds: 3));
    Utils.showSuccessToast("Password changed successfully !", false);
    setLoaderState(ViewStateEnum.idle);
    Get.offAllNamed(loginViewRoute);
    // Logger.info("Check the username is there >>>> ${emailController.text}");
    // var forgotPasswordCallback = await CognitoService.forgotPassword(
    //   verificationCode: verificationCodeController.text.trim(),
    //   newPassword: passwordController.text.trim(),
    //   userName: emailController.text.trim(),
    // );
    // if (forgotPasswordCallback.runtimeType == bool) {
    //   setLoaderState(ViewStateEnum.idle);
    //   Get.offAllNamed(loginViewRoute);
    //   Utils.showSuccessToast("Password changed successfully !", false);
    // } else {
    //   setLoaderState(ViewStateEnum.idle);
    //   Utils.showSuccessToast(forgotPasswordCallback.toString(), true);
    // }
  }
}

enum ForgotPasswordStatus { enterUsername, resetPassword }
