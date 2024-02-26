import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/ui/widgets/base_button.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';
import 'home_controller.dart';

class HomeScreen extends GetView<HomeController> with BaseCommonWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Home"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (HomeController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        const Text("Home Screen"),
        16.rVerticalSizedBox(),
        BaseButton(
            text: "Go to restaurant details",
            onSubmit: () {
              Get.toNamed(restaurantDetailViewRoute);
            }).paddingHorizontal16(),
        16.rVerticalSizedBox(),
        12.rVerticalSizedBox(),
      ],
    );
  }
}
