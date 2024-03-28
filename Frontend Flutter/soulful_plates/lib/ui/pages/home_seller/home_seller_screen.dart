import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/app_colors.dart';
import 'package:soulful_plates/constants/app_text_styles.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_icons.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/app_theme.dart';
import '../../../constants/enums/view_state.dart';
import '../../../routing/route_names.dart';
import '../../widgets/base_common_widget.dart';
import '../../widgets/order_item_widget.dart';
import '../order_detail/order_detail_screen.dart';
import 'home_seller_controller.dart';

class HomeSellerScreen extends GetView<HomeSellerController>
    with BaseCommonWidget {
  const HomeSellerScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColor.whiteColor,
      body: SafeArea(
        child: GetBuilder(
          init: controller,
          initState: (state) async {},
          builder: (HomeSellerController model) {
            return getBody(context);
          },
        ),
      ),
    );
  }

  Widget getBody(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Image.asset(AppIcons.appIcon, width: 36.rWidth()),
              8.rHorizontalSizedBox(),
              Text(
                'Welcome to Soulful Plates!',
                style: AppTextStyles.textStyleBlack16With700,
              ),
            ],
          ).paddingAll12(),
          12.rVerticalSizedBox(),
          CardOne(),
          20.rVerticalSizedBox(),
          Row(
            children: [
              const Expanded(
                child: Divider(
                  color: Colors.black,
                  thickness: 1,
                ),
              ),
              10.rHorizontalSizedBox(),
              Text(
                'ORDERS',
                style: AppTextStyles.textStyleBlack22With700,
              ),
              10.rHorizontalSizedBox(),
              const Expanded(
                child: Divider(
                  color: Colors.black,
                  thickness: 1,
                ),
              ),
            ],
          ).paddingHorizontal8(),
          20.rVerticalSizedBox(),
          Stack(children: [
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
                          physics: const NeverScrollableScrollPhysics(),
                          itemCount: controller.dataList.length + 1,
                          itemBuilder: (context, index) {
                            if (index < controller.dataList.length) {
                              return InkWell(
                                onTap: () async {
                                  Get.toNamed(orderDetailViewRoute,
                                      arguments: controller.dataList[index]);
                                },
                                child: OrderItemWidget(
                                    orderDetailModel:
                                        controller.dataList[index],
                                    isSeller: true,
                                    orderStatusChange: (OrderStatus? status) {
                                      print("This is order status ${status}");
                                      // controller.changeOrderStatus(status);
                                    }).paddingVertical8(),
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
          ])
        ],
      ).paddingAll16(),
    );
  }
}

class CardOne extends StatelessWidget {
  const CardOne({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      // decoration: BoxDecoration(
      //   borderRadius: BorderRadius.circular(12),
      //   boxShadow: const [
      //     BoxShadow(
      //       color: AppColor.greenColor,
      //       blurRadius: 1,
      //       spreadRadius: 0.3,
      //       offset: Offset(0, 1.0),
      //     )
      //   ],
      //   gradient: const LinearGradient(
      //     colors: [AppColor.greenStart, AppColor.greenEnd],
      //     begin: Alignment.bottomLeft,
      //     end: Alignment.topRight,
      //   ),
      // ),
      //
      decoration: BoxDecoration(
          gradient: const LinearGradient(
              colors: [AppColor.greenStart, AppColor.greenEnd],
              begin: FractionalOffset(0.0, 0.0),
              end: FractionalOffset(1.0, 1.0),
              stops: [0.0, 1.0],
              tileMode: TileMode.clamp),
          borderRadius: BorderRadius.circular(12.rSize())),
      child: Flex(
        direction: Axis.vertical,
        children: [
          Column(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Text(
                    'Earnings Statistics',
                    style: AppTextStyles.textStyleWhite18With600,
                  ),
                ],
              ),
              36.rVerticalSizedBox(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Row(
                        children: [
                          Column(
                            children: [
                              Text('Amount',
                                  style: AppTextStyles.textStyleWhite14With500),
                            ],
                          )
                        ],
                      ),
                      Row(
                        children: [
                          Column(
                            children: [
                              Text('\$1000.00 CAD',
                                  style: AppTextStyles.textStyleWhite22With700),
                            ],
                          )
                        ],
                      )
                    ],
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.end,
                    children: [
                      Row(
                        children: [
                          Column(
                            children: [
                              Text(
                                'Orders',
                                style: AppTextStyles.textStyleWhite14With500,
                              ),
                            ],
                          )
                        ],
                      ),
                      Row(
                        children: [
                          Column(
                            children: [
                              Text('#54',
                                  style: AppTextStyles.textStyleWhite22With700),
                            ],
                          )
                        ],
                      )
                    ],
                  )
                ],
              ),
            ],
          )
        ],
      ).paddingAll16(),
    ).paddingHorizontal8();
  }
}

// class CardTwo extends StatelessWidget {
//   int index = 0;
//   CardTwo({Key? key, required this.index}) : super(key: key);
//
//   @override
//   Widget build(BuildContext context) {
//     return Material(
//       borderRadius: BorderRadius.circular(12),
//       child: Container(
//         decoration: AppTheme.boxDecorationCard,
//         child: Column(
//           crossAxisAlignment: CrossAxisAlignment.start,
//           children: <Widget>[
//             Row(
//               mainAxisAlignment: MainAxisAlignment.spaceBetween,
//               children: [
//                 Container(
//                   decoration:
//                       AppTheme.getStatusBackgroundColor(OrderStatus.Completed),
//                   child: Text(
//                     index % 2 == 0 ? 'Out for Delivery' : 'Food Preparing',
//                     style: AppTheme.getStatusColor(OrderStatus.Completed),
//                   ).paddingUpSide816(),
//                 ),
//                 Column(
//                   children: [
//                     Row(
//                       children: [
//                         Text(
//                           '5 mins left',
//                           style: AppTextStyles.textStyleBlack16With400,
//                         ),
//                         4.rHorizontalSizedBox(),
//                         Icon(
//                           Icons.hourglass_bottom,
//                           color: AppColor.black2TextColor,
//                           size: 24.rSize(),
//                         ),
//                       ],
//                     )
//                   ],
//                 )
//               ],
//             ),
//             18.rVerticalSizedBox(),
//             Row(
//               mainAxisAlignment: MainAxisAlignment.spaceBetween,
//               children: [
//                 Column(
//                   crossAxisAlignment: CrossAxisAlignment.start,
//                   children: [
//                     Text(
//                       'Order ID',
//                       style: TextStyle(fontWeight: FontWeight.bold),
//                     ),
//                     GestureDetector(
//                       onTap: () {
//                         Navigator.push(
//                           context,
//                           MaterialPageRoute(
//                             builder: (context) => OrderDetailScreen(),
//                           ),
//                         );
//                       },
//                       child: Text(
//                         '2141',
//                         style: TextStyle(
//                           color: Colors.black,
//                           fontSize: 16,
//                         ),
//                       ),
//                     ),
//                   ],
//                 ),
//                 Column(
//                   crossAxisAlignment: CrossAxisAlignment.start,
//                   children: const [
//                     Text(
//                       'Address',
//                       style: TextStyle(fontWeight: FontWeight.bold),
//                     ),
//                     Text(
//                       '123 Main St, City, Country',
//                       style: TextStyle(fontSize: 16),
//                     ),
//                   ],
//                 ),
//               ],
//             ),
//             18.rVerticalSizedBox(),
//             Row(
//               mainAxisAlignment: MainAxisAlignment.start,
//               children: [
//                 Expanded(
//                     child: ElevatedButton.icon(
//                         onPressed: () {},
//                         style: ElevatedButton.styleFrom(
//                             backgroundColor: AppColor.greenColorCode),
//                         icon: Icon(Icons.call),
//                         label: Text("Call Driver"))),
//                 12.rHorizontalSizedBox(),
//                 Expanded(
//                     child: ElevatedButton.icon(
//                         onPressed: () {},
//                         style: ElevatedButton.styleFrom(
//                             backgroundColor: AppColor.greenColorCode),
//                         icon: Icon(Icons.my_location),
//                         label: Text(
//                           'Track Order',
//                         ))),
//               ],
//             ),
//           ],
//         ).paddingAll16(),
//       ).paddingHorizontal12(),
//     );
//   }
// }

class CardThree extends StatelessWidget {
  const CardThree({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(12),
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
            side: BorderSide(
              color: Colors.green.shade800,
              width: 1,
            ),
          ),
          elevation: 2,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    children: [
                      Container(
                        padding: EdgeInsets.symmetric(
                          vertical: 8,
                          horizontal: 16,
                        ),
                        decoration: BoxDecoration(
                          color: Colors.purple.shade500,
                          borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(12),
                            bottomRight: Radius.circular(12),
                          ),
                        ),
                        child: Text(
                          'Out for Delivery',
                          style: TextStyle(color: Colors.white),
                        ),
                      )
                    ],
                  ),
                  Column(
                    children: [
                      Row(
                        children: [
                          Column(
                            children: const [
                              Text(
                                '10 mins left',
                                style: TextStyle(fontSize: 16),
                              ),
                            ],
                          ),
                          8.rHorizontalSizedBox(),
                          Column(
                            children: const [
                              Icon(
                                Icons.hourglass_bottom,
                                color: Colors.grey,
                              ),
                            ],
                          ),
                        ],
                      )
                    ],
                  )
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Order ID',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      GestureDetector(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => OrderDetailScreen(),
                            ),
                          );
                        },
                        child: Text(
                          '2231',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                    ],
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: const [
                      Text(
                        'Address',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        '121 Yale St, City, Country',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                children: [
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement call driver functionality
                      },
                      icon: Icon(Icons.phone),
                      label: Text(
                        'Call Driver',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 2,
                      ),
                    ),
                  ),
                  12.rHorizontalSizedBox(),
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement track driver functionality
                      },
                      icon: Icon(Icons.my_location),
                      label: Text(
                        'Track Order',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 3,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ).paddingAll16(),
        ),
      ),
    );
  }
}

class CardFour extends StatelessWidget {
  const CardFour({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(12),
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
            side: BorderSide(
              color: Colors.green.shade800,
              width: 1,
            ),
          ),
          elevation: 2,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    children: [
                      Container(
                        padding: EdgeInsets.symmetric(
                          vertical: 8,
                          horizontal: 16,
                        ),
                        decoration: BoxDecoration(
                          color: Colors.purple.shade500,
                          borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(12),
                            bottomRight: Radius.circular(12),
                          ),
                        ),
                        child: Text(
                          'Out for Delivery',
                          style: TextStyle(color: Colors.white),
                        ),
                      )
                    ],
                  ),
                  Column(
                    children: [
                      Row(
                        children: [
                          Column(
                            children: const [
                              Text(
                                '10 mins left',
                                style: TextStyle(fontSize: 16),
                              ),
                            ],
                          ),
                          8.rHorizontalSizedBox(),
                          Column(
                            children: const [
                              Icon(
                                Icons.hourglass_bottom,
                                color: Colors.grey,
                              ),
                            ],
                          ),
                        ],
                      )
                    ],
                  )
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Order ID',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      GestureDetector(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => OrderDetailScreen(),
                            ),
                          );
                        },
                        child: Text(
                          '2231',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                    ],
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: const [
                      Text(
                        'Address',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        '121 Yale St, City, Country',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                children: [
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement call driver functionality
                      },
                      icon: Icon(Icons.phone),
                      label: Text(
                        'Call Driver',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 2,
                      ),
                    ),
                  ),
                  12.rHorizontalSizedBox(),
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement track driver functionality
                      },
                      icon: Icon(Icons.my_location),
                      label: Text(
                        'Track Order',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 3,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ).paddingAll16(),
        ),
      ),
    );
  }
}
