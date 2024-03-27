import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/app_text_styles.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/enums/view_state.dart';
import '../../../constants/size_config.dart';
import '../../../utils/extensions.dart';
import '../../widgets/base_common_widget.dart';
import '../../widgets/payment_item_widget.dart';
import 'transaction_seller_controller.dart';

class TransactionSellerScreen extends GetView<TransactionSellerController>
    with BaseCommonWidget {
  TransactionSellerScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Transaction Seller"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (TransactionSellerController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const CardOne(),
        12.rVerticalSizedBox(),
        Expanded(
          child: Container(
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(8.0),
                color: AppColor.black5TextColor),
            child: TextField(
              decoration: InputDecoration(
                hintText: 'Search with Order No',
                prefixIcon: const Icon(Icons.search),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(8.0),
                  borderSide: const BorderSide(
                    color: Colors.black, // specify border color here
                  ),
                ),
                focusedBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(8.0),
                  borderSide: const BorderSide(
                    color: Colors.black, // specify focused border color here
                  ),
                ),
              ),
            ),
          ),
        ).paddingAll4(),
        8.rVerticalSizedBox(),
        Expanded(
          child: Stack(children: [
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
                                child: PaymentItemWidget(
                                        paymentModel:
                                            controller.dataList[index])
                                    .paddingVertical8(),
                              );
                            } else if (controller.moreLoading ==
                                ViewStateEnum.busy) {
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
          ]).paddingAllDefault(),
        )
      ],
    ).paddingAll16();
  }
}

class CardOne extends StatelessWidget {
  const CardOne({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      constraints: BoxConstraints(maxWidth: 500),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(8),
        child: Container(
          decoration: BoxDecoration(
              gradient: const LinearGradient(
                  colors: [AppColor.greenStart, AppColor.greenEnd],
                  begin: FractionalOffset(0.0, 0.0),
                  end: FractionalOffset(1.0, 1.0),
                  stops: [0.0, 1.0],
                  tileMode: TileMode.clamp),
              borderRadius: BorderRadius.circular(12.rSize())),

          // decoration: BoxDecoration(
          //   borderRadius: BorderRadius.circular(8),
          //   color: AppColor.emeraldGreen, // Change to your desired color
          // ),
          child: Flex(
            direction: Axis.vertical,
            children: [
              Column(
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            '12',
                            style: AppTextStyles.textStyleWhite22With700,
                          ),
                          Text(
                            'Paid',
                            style: AppTextStyles.textStyleWhite14With400,
                          ),
                        ],
                      ),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            '05',
                            style: AppTextStyles.textStyleWhite22With700,
                          ),
                          Text(
                            'Unpaid',
                            style: AppTextStyles.textStyleWhite14With400,
                          ),
                        ],
                      ),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            '02',
                            style: AppTextStyles.textStyleWhite22With700,
                          ),
                          Text(
                            'Overdue',
                            style: AppTextStyles.textStyleWhite14With400,
                          ),
                        ],
                      ),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            '03',
                            style: AppTextStyles.textStyleWhite22With700,
                          ),
                          Text(
                            'Draft',
                            style: AppTextStyles.textStyleWhite14With400,
                          ),
                        ],
                      ),
                    ],
                  ),
                ],
              ),
            ],
          ).paddingAll16(),
        ).paddingHorizontal4(),
      ),
    );
  }
}
