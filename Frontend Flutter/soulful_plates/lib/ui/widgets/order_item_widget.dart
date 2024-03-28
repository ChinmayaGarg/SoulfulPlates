import 'package:flutter/material.dart';
import 'package:soulful_plates/Utils/Extensions.dart';
import 'package:soulful_plates/constants/app_sized_box.dart';
import 'package:soulful_plates/constants/size_config.dart';

import '../../constants/app_colors.dart';
import '../../constants/app_text_styles.dart';
import '../../constants/app_theme.dart';
import '../../model/order_detail_model.dart';
import '../../utils/utils.dart';
import '../pages/order_detail/order_detail_screen.dart';

class OrderItemWidget extends StatelessWidget {
  OrderDetailModel orderDetailModel;
  bool isSeller = false;

  Function(OrderStatus? status) orderStatusChange;

  OrderItemWidget(
      {Key? key,
      required this.orderDetailModel,
      required this.isSeller,
      required this.orderStatusChange})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(12),
      child: Container(
        decoration: AppTheme.boxDecorationCard,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Container(
                  decoration: AppTheme.getStatusBackgroundColor(
                      orderDetailModel.getOrderStatusType()),
                  child: Text(
                    orderDetailModel.getOrderStatusType().name,
                    style: AppTheme.getStatusColor(
                        orderDetailModel.getOrderStatusType()),
                  ).paddingUpSide816(),
                ),
                Column(
                  children: [
                    Row(
                      children: [
                        Text(
                          '${Utils.getRandomTimeLeft()} mins left',
                          style: AppTextStyles.textStyleBlack16With400,
                        ),
                        4.rHorizontalSizedBox(),
                        Icon(
                          Icons.hourglass_bottom,
                          color: AppColor.black2TextColor,
                          size: 24.rSize(),
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
                      style: AppTextStyles.textStyleBlack14With700,
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
                        '${orderDetailModel.orderId}',
                        style: AppTextStyles.textStyleBlack16With400,
                      ),
                    ),
                  ],
                ),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Address',
                      style: AppTextStyles.textStyleBlack14With700,
                    ),
                    Text(
                      '123 Main St, City, Country',
                      style: AppTextStyles.textStyleBlack16With400,
                    ),
                  ],
                ),
              ],
            ),
            18.rVerticalSizedBox(),
            isSeller
                ? Container(
                    child: Row(
                      children: [
                        Text(
                          "Choose Order Status",
                          style: AppTextStyles.textStyleBlack14With400,
                        ),
                        const Spacer(),
                        PopupMenuButton<OrderStatus>(
                          initialValue: orderDetailModel.getOrderStatusType(),
                          onSelected: (OrderStatus newValue) {
                            orderStatusChange(newValue);
                          },
                          itemBuilder: (BuildContext context) {
                            return List.generate(OrderStatus.values.length,
                                (index) {
                              return PopupMenuItem<OrderStatus>(
                                value: OrderStatus.values[index],
                                child: Row(
                                  children: [
                                    Text(OrderStatus.values[index].name)
                                  ],
                                ),
                              );
                            });
                          },
                          child: Container(
                            decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(8),
                                color: AppColor.black5TextColor),
                            child: Text(
                              orderDetailModel.getOrderStatusType().name,
                              textAlign: TextAlign.end,
                            ).paddingUpSide816(),
                          ),
                        ),
                      ],
                    ),
                  )
                : AppSizedBox.sizedBox0,
            18.rVerticalSizedBox(),
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Expanded(
                    child: ElevatedButton.icon(
                        onPressed: () {},
                        style: ElevatedButton.styleFrom(
                            backgroundColor: AppColor.greenColorCode),
                        icon: const Icon(Icons.call),
                        label: const Text("Call Driver"))),
                12.rHorizontalSizedBox(),
                Expanded(
                    child: ElevatedButton.icon(
                        onPressed: () {},
                        style: ElevatedButton.styleFrom(
                            backgroundColor: AppColor.greenColorCode),
                        icon: const Icon(Icons.my_location),
                        label: const Text(
                          'Track Order',
                        ))),
              ],
            ),
          ],
        ).paddingAll16(),
      ).paddingHorizontal12(),
    );
  }
}
