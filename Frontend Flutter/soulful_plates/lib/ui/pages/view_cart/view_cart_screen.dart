import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/ui/pages/view_cart/view_cart_controller.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';

class ViewCartScreen extends GetView<ViewCartController> with BaseCommonWidget {
  ViewCartScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("View Cart"),
        ),
        backgroundColor: AppColor.whiteColor,
        floatingActionButton: FloatingActionButton(
            tooltip: "Go to Payment",
            onPressed: () {
              Get.toNamed(cartPaymentViewRoute);
            },
            child: const Icon(
              Icons.payments_outlined,
              size: 24,
              color: AppColor.whiteColor,
            )),
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (ViewCartController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        12.rVerticalSizedBox(),
        // Expanded(
        //   child: ListView.builder(
        //     itemCount: controller.dataList.length,
        //     shrinkWrap: true,
        //     itemBuilder: (context, index) {
        //       return CartItemWidget(cartItem: controller.dataList[index]);
        //     },
        //   ),
        // ),
      ],
    );
  }
}
