import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_colors.dart';
import '../../widgets/base_common_widget.dart';
import 'restaurant_detail_controller.dart';

class RestaurantDetailScreen extends GetView<RestaurantDetailController>
    with BaseCommonWidget {
  RestaurantDetailScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Restaurant Detail"),
          actions: [
            InkWell(
              onTap: () {
                controller.isEditable = !controller.isEditable;
                controller.update();
              },
              child: const Icon(
                Icons.edit,
                color: AppColor.blackColor,
                size: 24,
              ).paddingHorizontal16(),
            ).visibleWhen(isVisible: !controller.isEditable)
          ],
        ),
        backgroundColor: AppColor.whiteColor,
        floatingActionButton: FloatingActionButton(
            tooltip: "Go to cart",
            onPressed: () {
              Get.toNamed(viewCartViewRoute);
            },
            child: const Icon(
              Icons.shopping_cart,
              size: 24,
              color: AppColor.whiteColor,
            )),
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (RestaurantDetailController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          16.rVerticalSizedBox(),
          16.rVerticalSizedBox(),
        ],
      ).paddingHorizontal24(),
    );
  }
}
