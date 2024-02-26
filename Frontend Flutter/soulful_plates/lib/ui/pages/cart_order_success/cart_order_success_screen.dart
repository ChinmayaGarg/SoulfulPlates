import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/ui/widgets/base_button.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';
import 'cart_order_success_controller.dart';

class CartOrderSuccessScreen extends GetView<CartOrderSuccessController>
    with BaseCommonWidget {
  CartOrderSuccessScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Cart Order Success"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (CartOrderSuccessController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        const Text("Cart Order Success Screen"),
        12.rVerticalSizedBox(),
        BaseButton(
            text: "Go to Home",
            onSubmit: () {
              Get.offAllNamed(dashboardViewRoute);
            })
      ],
    );
  }
}
