import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/routing/route_names.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/enums/view_state.dart';
import '../../../constants/size_config.dart';
import '../../../utils/extensions.dart';
import '../../widgets/base_common_widget.dart';
import 'cart_payment_controller.dart';

class CartPaymentScreen extends GetView<CartPaymentController>
    with BaseCommonWidget {
  CartPaymentScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Payment"),
        ),
        backgroundColor: AppColor.whiteColor,
        floatingActionButton: FloatingActionButton(
            tooltip: "Go to Order Success",
            onPressed: () {
              Get.toNamed(orderSuccessViewRoute);
            },
            child: const Icon(
              Icons.check_circle,
              size: 24,
              color: AppColor.whiteColor,
            )),
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (CartPaymentController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        Expanded(
          child: Stack(children: [
            Text("Response data ${controller.dataModel?.toString() ?? ''}"),
            controller.state == ViewStateEnum.busy
                ? const Center(child: CircularProgressIndicator())
                : AppSizedBox.sizedBox0
          ]).paddingSymmetricSide(vertical: 8, horizontal: 16),
        ),
        12.rVerticalSizedBox(),
      ],
    );
  }
}
