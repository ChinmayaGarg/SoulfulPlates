import 'package:flutter/cupertino.dart';

import '../../../controller/base_controller.dart';
import '../../../model/store_details/store_detail_model.dart';
import '../../../utils/utils.dart';

class StoreDetailsController extends BaseController {
  bool isEditable = false;
  StoreDetails? storeDetails = Utils.storeDetails;

  @override
  void onInit() {
    super.onInit();
  }

  TextEditingController emailEditingController =
      TextEditingController(text: Utils.storeDetails?.email);
  TextEditingController mobileEditingController =
      TextEditingController(text: Utils.storeDetails?.mobile);
  TextEditingController firstNameEditingController =
      TextEditingController(text: Utils.storeDetails?.firstName);
  TextEditingController streetEditingController =
      TextEditingController(text: Utils.storeDetails?.street);
  TextEditingController cityEditingController =
      TextEditingController(text: Utils.storeDetails?.city);
  TextEditingController stateEditingController =
      TextEditingController(text: Utils.storeDetails?.state);
  TextEditingController postalCodeEditingController =
      TextEditingController(text: Utils.storeDetails?.postalCode);

  FocusNode emailFocusNode = FocusNode();
  FocusNode mobileFocusNode = FocusNode();
  FocusNode firstNameFocusNode = FocusNode();
  FocusNode streetFocusNode = FocusNode();
  FocusNode cityFocusNode = FocusNode();
  FocusNode stateFocusNode = FocusNode();
  FocusNode postalCodeFocusNode = FocusNode();

  // Function to update data
  void updateData() {
    StoreDetails updatedStoreDetails = StoreDetails(
      email: emailEditingController.text,
      mobile: mobileEditingController.text,
      firstName: firstNameEditingController.text,
      street: streetEditingController.text,
      city: cityEditingController.text,
      state: stateEditingController.text,
      postalCode: postalCodeEditingController.text,
    );

    Utils.storeDetails = updatedStoreDetails;
    storeDetails = updatedStoreDetails;
  }
}
