import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/app_text_styles.dart';
import 'package:soulful_plates/constants/app_theme.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_colors.dart';
import '../../widgets/base_common_widget.dart';
import 'payout_seller_controller.dart';

class PayoutSellerScreen extends GetView<PayoutSellerController>
    with BaseCommonWidget {
  PayoutSellerScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Payout Seller"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (PayoutSellerController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        getPayoutCard(),
        4.rVerticalSizedBox(),
        getPayoutCard(),
        4.rVerticalSizedBox(),
        getPayoutCard(),
        4.rVerticalSizedBox(),
        getPayoutCard(),
        4.rVerticalSizedBox(),
        getPayoutCard(),
      ],
    ).paddingHorizontal8();
  }

  Widget getPayoutCard() {
    return Container(
            decoration: AppTheme.boxItemDecorationCard,
            child: Row(
              children: [
                Expanded(
                    child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "Amount: \$120.15",
                      style: AppTextStyles.textStyleBlack16With700,
                    ),
                    Text(
                      "21/02/2024",
                      style: AppTextStyles.textStyleBlackTwo12With400,
                    ),
                  ],
                )),
                Container(
                  decoration: BoxDecoration(
                      color: AppColor.successGreen.withOpacity(.1),
                      borderRadius: BorderRadius.circular(16)),
                  child: Text(
                    "Paid",
                    style: AppTextStyles.textStyleGreen14With400,
                  ).paddingUpSide816(),
                )
              ],
            ).paddingUpSide812())
        .paddingUpSide812();
  }
}
