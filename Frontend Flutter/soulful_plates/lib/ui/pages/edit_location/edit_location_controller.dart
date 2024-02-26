import 'package:flutter/cupertino.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:get/get_navigation/get_navigation.dart';

import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../model/data_model.dart';
import '../../../utils/utils.dart';

class EditLocationController extends BaseController {
  DataModel? dataModel;
// Controllers for text fields
  TextEditingController nameController = TextEditingController();
  TextEditingController latitudeController = TextEditingController();
  TextEditingController longitudeController = TextEditingController();
  TextEditingController addressController = TextEditingController();

  @override
  void onInit() {
    super.onInit();
    getDataFromAPI();
  }

  void getDataFromAPI() async {
    setLoaderState(ViewStateEnum.busy);
    Utils.addLocation(
      latitude: double.tryParse(latitudeController.text) ?? 0,
      longitude: double.tryParse(longitudeController.text) ?? 0,
      address: addressController.text,
      locationName: nameController.text,
    );
    setLoaderState(ViewStateEnum.idle);
    update();
    Get.back();
  }
}
