import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/ui/widgets/base_button.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/app_text_styles.dart';
import '../../../constants/enums/view_state.dart';
import '../../../constants/size_config.dart';
import '../../../utils/extensions.dart';
import '../../widgets/base_common_widget.dart';
import 'wishlist_controller.dart';

class WishlistScreen extends GetView<WishlistController> with BaseCommonWidget {
  const WishlistScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Wish list"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (WishlistController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        BaseButton(
            text: "Get Address",
            onSubmit: () {
              // controller.addMenuItem(data: {
              //   "itemName": "berger 2 9",
              //   "itemImage": "",
              //   "itemPrice": "13",
              //   "type": "Veg",
              //   "storeId": 1,
              //   "categoryId": 2,
              //   "subcategoryId": 9,
              //   "servingType": 2,
              //   "portion": "Medium",
              //   "inStock": true,
              //   "isRecommended": false,
              //   "description": "It contains mayo and margarita cheese sauce."
              // });

              // controller.addCategory(
              //     data: {"categoryName": "Category 2", "storeId": 1});

              // controller.addSubCategory(data: {
              //   "categoryId": 2,
              //   "subCategoryName": "Subcategory 123",
              //   "storeId": 1
              // });

              // controller.editSubCategory(data: {
              //   "categoryId": 2,
              //   "subCategoryName": "Subcategory 123",
              //   "storeId": 1
              // });

              // controller.addAddress(data: {
              //   "street": "123 Main St",
              //   "city": "New York",
              //   "state": "NY",
              //   "postalCode": "10001",
              //   "country": "USA",
              //   "lat": "40.7128",
              //   "longitude": "-74.0060",
              //   "label": "Home"
              // });

              // controller.updateAddress(data: {
              //   "id": "2",
              //   "street": "123 ",
              //   "city": "New York",
              //   "state": "NY",
              //   "postalCode": "10001",
              //   "country": "USA",
              //   "lat": "40.7128",
              //   "longitude": "-74.0060",
              //   "label": "Home"
              // });

              // controller.createOrder(data: {
              //   "userId": 1,
              //   "storeId": 1,
              //   "instructions": "Leave at door",
              //   "selectedItems": [
              //     {
              //       "menuItemId": 1,
              //       "itemName": "Banana shake",
              //       "quantity": 2,
              //       "price": 10.25
              //     }
              //   ]
              // });

              // controller.updateOrderStatus(
              //     data: {"orderId": 1, "status": "COMPLETED"});

              // controller.getOrderDetails(data: {"userId": 1, "orderId": 1});

              // controller.getOrdersForUser(data: {
              //   "userId": 1,
              //   "status": "Pending",
              //   "limit": 20,
              //   "offset": 0
              // });

              // controller.getOrdersForStore(data: {
              //   "storeId": 1,
              //   "status": "Pending",
              //   "limit": 20,
              //   "offset": 0
              // });

              // controller.createPayment(data: {
              //   "userId": 1,
              //   "storeId": 1,
              //   "amount": 25.25,
              //   "orderId": 1,
              //   "cardNumber": "1234-4567-4561",
              //   "cardExpiry": "01/25",
              //   "cvv": "456"
              // });

              // controller.updatePaymentStatus(data: {
              //   "paymentId": 1,
              //   "transactionId": 1,
              //   "status": "Completed"
              // });

              // controller.createRating(data: {
              //   "userId": 1,
              //   "storeId": 1,
              //   "orderId": 1,
              //   "rating": 5,
              //   "feedback": "Excellent service"
              // });

              // controller.createRating(data: {
              //   "userId": 1,
              //   "storeId": 1,
              //   "orderId": 1,
              //   "rating": 5,
              //   "feedback": "Excellent service"
              // });

              // controller.getAverageRating();
              //
              // controller.updateTransactionStatus(
              //     data: {"transactionId": 1, "status": "completed"});

              // controller.getPaymentHistoryBuyer(data: {
              //   "userId": 1,
              //   "limit": 20,
              //   "offset": 0,
              //   "status": "Pending"
              // });

              // controller.getPaymentHistorySeller(data: {
              //   "storeId": 1,
              //   "limit": 20,
              //   "offset": 0,
              //   "status": "Pending"
              // });

              // controller.deleteAddress(data: {"id": "2"});

              // controller.getAddress();

              // controller.getMenuItems();

              // controller.updateNotificationStatus();

              // controller.getNearByStores();
            }),
        12.rVerticalSizedBox(),
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
                        child: ListView.separated(
                          padding: EdgeInsets.zero,
                          shrinkWrap: true,
                          physics: const AlwaysScrollableScrollPhysics(),
                          itemCount: controller.dataList.length + 1,
                          separatorBuilder: (context, index) {
                            return 2.rVerticalGreySizedBox();
                          },
                          itemBuilder: (context, index) {
                            if (index < controller.dataList.length) {
                              return InkWell(
                                onTap: () async {
                                  //todo tap on the item
                                },
                                child: //todo change widget with item widget
                                    Text("Item number $index")
                                        .paddingAllDefault(),
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
          ]).paddingSymmetricSide(vertical: 8, horizontal: 16),
        ),
        12.rVerticalSizedBox(),
      ],
    );
  }
}
