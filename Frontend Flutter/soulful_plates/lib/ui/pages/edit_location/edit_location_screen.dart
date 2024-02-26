import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/ui/widgets/app_text_field.dart';
import 'package:soulful_plates/ui/widgets/base_button.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_text_styles.dart';
import '../../../utils/extensions.dart';
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
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            16.rVerticalSizedBox(),
            Text(
              'Location Name',
              style: AppTextStyles.textStyleBlackTwo12With400,
            ),
            8.rVerticalSizedBox(),
            AppTextField(
              controller: controller.nameController,
              hintText: 'Location Name',
              // decoration: const InputDecoration(labelText: 'Location Name'),
            ),
            16.rVerticalSizedBox(),
            Text(
              'Latitude',
              style: AppTextStyles.textStyleBlackTwo12With400,
            ),
            8.rVerticalSizedBox(),
            AppTextField(
              controller: controller.latitudeController,
              hintText: 'Latitude',
              keyboardType: TextInputType.number,
            ),
            16.rVerticalSizedBox(),
            Text(
              'Longitude',
              style: AppTextStyles.textStyleBlackTwo12With400,
            ),
            8.rVerticalSizedBox(),
            AppTextField(
              controller: controller.longitudeController,
              hintText: 'Longitude',
              keyboardType: TextInputType.number,
            ),
            16.rVerticalSizedBox(),
            Text(
              'Address',
              style: AppTextStyles.textStyleBlackTwo12With400,
            ),
            8.rVerticalSizedBox(),
            AppTextField(
              controller: controller.addressController,
              hintText: 'Address',
            ),
            16.rVerticalSizedBox(),
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
        )
      ],
    ).paddingAll16();
  }
}
