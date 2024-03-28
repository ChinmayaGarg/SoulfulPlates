import 'package:flutter/material.dart';
import 'package:get/get.dart';
// import 'package:map_address_picker/map_address_picker.dart';
// import 'package:map_address_picker/models/location_result.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/ui/widgets/app_text_field.dart';
import 'package:soulful_plates/ui/widgets/base_button.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_text_styles.dart';
import '../../../utils/utils.dart';
import '../../widgets/base_common_widget.dart';
import 'edit_location_controller.dart';

class EditLocationScreen extends GetView<EditLocationController>
    with BaseCommonWidget {
  EditLocationScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Add Location"),
      ),
      backgroundColor: AppColor.whiteColor,
      body: SafeArea(
        child: GetBuilder(
          init: controller,
          initState: (state) async {},
          builder: (EditLocationController model) {
            return getBody(context);
          },
        ),
      ),
    );
  }

  Widget getBody(BuildContext context) {
    return Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            16.rHorizontalSizedBox(),
            Text(
              'Location Name',
              style: AppTextStyles.textStyleBlackTwo12With400,
            ),
            8.rHorizontalSizedBox(),
            AppTextField(
              controller: controller.nameController,
              hintText: 'Enter Location Name',
            ),
            16.rHorizontalSizedBox(),
            Row(
              children: [
                Expanded(
                  child: ElevatedButton(
                    onPressed: () async {
                      // Open the map location picker
                      // LocationResult? result = await showLocationPicker(
                      //   context,
                      //   title: '', // Pass an empty string for the API key
                      //   //apikey:'',
                      //   initialCenter: LatLng(0.0, 0.0), // Initial map center
                      //   automaticallyAnimateToCurrentLocation:
                      //       true, // Auto center to user location
                      // );
                      //
                      // if (result != null) {
                      //   // Update latitude and longitude text fields
                      //   controller.latitudeController.text =
                      //       result.latLng?.latitude.toString() ?? '';
                      //   controller.longitudeController.text =
                      //       result.latLng?.longitude.toString() ?? '';
                      // }
                    },
                    child: Text('Select Location'),
                  ),
                ),
              ],
            ).paddingAll4(),
            16.rHorizontalSizedBox(),
            BaseButton(
              onSubmit: () {
                if (controller.nameController.text.isNotEmpty &&
                    controller.latitudeController.text.isNotEmpty &&
                    controller.longitudeController.text.isNotEmpty) {
                  controller.getDataFromAPI();
                } else {
                  Utils.showSuccessToast(
                      'Please fill in all required fields.', true);
                }
              },
              text: "Add location",
            )
          ],
        ));
  }
}
