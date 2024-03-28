import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/Utils/Extensions.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/app_text_styles.dart';
import '../../../constants/enums/view_state.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';
import '../../widgets/order_item_widget.dart';
import 'order_history_seller_controller.dart';

class OrderHistorySellerScreen extends GetView<OrderHistorySellerController>
    with BaseCommonWidget {
  OrderHistorySellerScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Order History"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (OrderHistorySellerController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Stack(children: [
      controller.dataList.isNotEmpty
          ? RefreshIndicator(
              onRefresh: () async {
                controller.resetPagination();
              },
              child: NotificationListener<ScrollNotification>(
                  onNotification: (scrollNotification) {
                    if (scrollNotification.metrics.pixels >=
                            scrollNotification.metrics.maxScrollExtent &&
                        !controller.hasReachedMax &&
                        !controller.isLoading()) {
                      controller.pageNo = (controller.pageNo + 1);
                      controller.loadMore();
                    }
                    return false;
                  },
                  child: ListView.builder(
                    padding: EdgeInsets.zero,
                    shrinkWrap: true,
                    physics: const AlwaysScrollableScrollPhysics(),
                    itemCount: controller.dataList.length + 1,
                    itemBuilder: (context, index) {
                      if (index < controller.dataList.length) {
                        return InkWell(
                          onTap: () async {
                            //todo tap on the item
                          },
                          child: OrderItemWidget(
                                  orderDetailModel: controller.dataList[index])
                              .paddingVertical8(),
                        );
                      } else if (controller.moreLoading == ViewStateEnum.busy) {
                        return controller.loadMoreLoader(
                            color: AppColor.blackColor);
                      } else {
                        return AppSizedBox.sizedBox0;
                      }
                    },
                  )),
            )
          : Center(
              child: GestureDetector(
                behavior: HitTestBehavior.opaque,
                onTap: () {
                  controller.resetPagination();
                },
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Icon(
                      Icons.refresh_outlined,
                      size: 24.rSize(),
                      color: AppColor.primaryColor,
                    ),
                    Text(
                      'No data available!',
                      style: AppTextStyles.textStyleBlack16With400,
                    ),
                  ],
                ).paddingAll12(),
              ),
            ),
      controller.state == ViewStateEnum.busy
          ? const Center(child: CircularProgressIndicator())
          : AppSizedBox.sizedBox0
    ]).paddingAllDefault();
  }
}
