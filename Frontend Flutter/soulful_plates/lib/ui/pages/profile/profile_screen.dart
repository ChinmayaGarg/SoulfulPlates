import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';
import 'profile_controller.dart';

class ProfileScreen extends GetView<ProfileController> with BaseCommonWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Profile"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (ProfileController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        const Text("Profile Screen"),
        12.rVerticalSizedBox(),
      ],
    );
  }
}