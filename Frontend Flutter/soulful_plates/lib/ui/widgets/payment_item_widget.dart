import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/Utils/Extensions.dart';
import 'package:soulful_plates/constants/app_text_styles.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/model/payment_model.dart';

import '../../constants/app_colors.dart';
import '../../constants/app_icons.dart';
import '../../constants/app_theme.dart';
import '../../utils/utils.dart';

class PaymentItemWidget extends StatelessWidget {
  PaymentModel paymentModel;
  PaymentItemWidget({Key? key, required this.paymentModel}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(10),
      elevation: 4, // Added elevation
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10),
          border: Border.all(color: Colors.black, width: 0.5), // Add border
        ),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(10),
          ),
          elevation: 0, // Removed elevation from the Card
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                children: [
                  // First Column for Profile pic and Store Name
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Row(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          8.rHorizontalSizedBox(), // Add horizontal spacing
                          // Profile pic
                          CircleAvatar(
                            radius: 16,
                            backgroundColor: Colors.lightGreen
                                .shade200, // Change background color as needed
                            child: Image.asset(
                                AppIcons.appIcon), // Add your image asset here
                          ),
                          8.rHorizontalSizedBox(), // Add horizontal spacing
                          // Text for store name
                          Container(
                            decoration: AppTheme.getStatusBackgroundColor(
                                OrderStatus.Completed),
                            child: Text(
                              "UserID: ${paymentModel.userId} ${paymentModel.storeId}",
                              style: AppTheme.getStatusColor(
                                  OrderStatus.Completed),
                            ).paddingUpSide816(),
                          ),
                        ],
                      ),
                    ],
                  ),
                  8.rHorizontalSizedBox(), // Add horizontal spacing between columns
                  const Spacer(),
                  Container(
                    decoration: BoxDecoration(
                      color: AppColor.linksColor.withOpacity(.1),
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: Text(
                      paymentModel.paymentStatus ?? '',
                      style: TextStyle(
                          color: AppColor.linksColor, fontSize: 12.rSize()),
                    ).paddingUpSide812(),
                  ),
                ],
              ),
              20.rVerticalSizedBox(), // Add vertical spacing
              Container(
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(8),
                  color: AppColor.greenColorCode,

                  border:
                      Border.all(color: Colors.black, width: 0.5), // Add border
                ),
                child: Row(
                  mainAxisSize: MainAxisSize.max,
                  // mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    // Amount Column
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            'Order No',
                            style: AppTextStyles.textStyleBlack12With700,
                          ),
                          Text('#${paymentModel.orderId}',
                              style: AppTextStyles.textStyleBlack12With400),
                        ],
                      ),
                    ),
                    2.rHorizontalSizedBox(),
                    // Divider
                    Container(
                      width: 1,
                      height: 40,
                      color: Colors.black,
                    ),
                    4.rHorizontalSizedBox(),
                    // No Column
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            'Date',
                            style: AppTextStyles.textStyleBlack12With700,
                          ),
                          Text(
                              Utils.getStringDateFromTime(
                                  paymentModel.updatedDate ?? ''),
                              style: AppTextStyles.textStyleBlack12With400),
                        ],
                      ),
                    ),
                    4.rHorizontalSizedBox(),
                    // Divider
                    Container(
                      width: 1,
                      height: 40,
                      color: Colors.black,
                    ),
                    // Date Column
                    4.rHorizontalSizedBox(),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            'Amount',
                            style: AppTextStyles.textStyleBlack12With700,
                          ),
                          Text("\$ ${paymentModel.amount?.toDouble()}",
                              style: AppTextStyles.textStyleBlack12With400),
                        ],
                      ),
                    ),
                  ],
                ).paddingAll6(),
              ),
              4.rVerticalSizedBox() // Add vertical spacing
            ],
          ).paddingAll(10),
        ),
      ),
    );
  }
}
