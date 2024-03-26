import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/app_text_styles.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/ui/pages/home/home_screen.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_colors.dart';
import '../../../utils/utils.dart';
import '../../widgets/base_common_widget.dart';
import 'restaurant_detail_controller.dart';

class RestaurantDetailScreen extends GetView<RestaurantDetailController>
    with BaseCommonWidget {
  RestaurantDetailScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          // title: const Text("Restaurant Detail"),
          actions: [
            InkWell(
              onTap: () {
                controller.isEditable = !controller.isEditable;
                controller.update();
              },
              child: const Icon(
                Icons.edit,
                color: AppColor.blackColor,
                size: 24,
              ).paddingHorizontal16(),
            ).visibleWhen(isVisible: !controller.isEditable)
          ],
        ),
        backgroundColor: AppColor.whiteColor,
        floatingActionButton: FloatingActionButton(
            tooltip: "Go to cart",
            onPressed: () {
              Get.toNamed(viewCartViewRoute);
            },
            child: const Icon(
              Icons.shopping_cart,
              size: 24,
              color: AppColor.whiteColor,
            )),
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (RestaurantDetailController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          HomeScreen.getRestaurantCard(),
          Text(
            "Lunch",
            style: AppTextStyles.textStyleBlack18With700,
          ).paddingHorizontal16(),
          8.rVerticalSizedBox(),
          Text(
            "Quick bites",
            style: AppTextStyles.textStyleBlack16With700,
          ).paddingHorizontal16(),
          8.rVerticalSizedBox(),
          getItemCard(),
          1.rVerticalGreySizedBox(),
          getItemCard(),
          1.rVerticalGreySizedBox(),
          getItemCard(),
          1.rVerticalGreySizedBox(),
          getItemCard(),
          1.rVerticalGreySizedBox(),
          getItemCard(),
          1.rVerticalGreySizedBox(),
          getItemCard(),
          1.rVerticalGreySizedBox(),
          getItemCard(),
        ],
      ),
    ).paddingAllDefault();
  }

  Widget getItemCard() {
    return GestureDetector(
        onTap: () {
          _showItemDetailBottomSheet();
        },
        child: Container(
          child: Row(
            mainAxisSize: MainAxisSize.max,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              8.rHorizontalSizedBox(),
              Expanded(
                  child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                mainAxisSize: MainAxisSize.min,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Image.asset(
                        Utils().getFoodTypeIcon('Veg'),
                        width: 18.rSize(),
                        height: 18.rSize(),
                      ),
                      4.rHorizontalSizedBox(),
                      Text(
                        "Eggs",
                        style: AppTextStyles.textStyleBlackTwo14With600,
                      ),
                      4.rHorizontalSizedBox(),
                    ],
                  ),
                  2.rVerticalSizedBox(),
                  Text(
                    "Punjabi Chhole with Bhature",
                    maxLines: 2,
                    style: AppTextStyles.textStyleBlack16With700,
                  ),
                  2.rVerticalSizedBox(),
                  Text(
                    "\$ 12.45",
                    style: AppTextStyles.textStyleBlackTwo14With600,
                  ),
                  4.rVerticalSizedBox(),
                  Container(
                    decoration: BoxDecoration(
                        border: Border.all(
                          width: 1.25,
                          color: AppColor.black2TextColor.withOpacity(0.75),
                        ),
                        borderRadius: BorderRadius.circular(40)),
                    child: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        const Icon(
                          Icons.info_outline,
                          size: 18,
                          color: AppColor.black2TextColor,
                        ),
                        4.rHorizontalSizedBox(),
                        Text(
                          "Item Info",
                          style: AppTextStyles.textStyleBlackTwo14With600,
                        ),
                        4.rHorizontalSizedBox(),
                      ],
                    ).paddingAll4(),
                  ),
                  4.rVerticalSizedBox(),
                ],
              ).paddingAllDefault()),
              ClipRRect(
                borderRadius: const BorderRadius.all(
                  Radius.circular(8.0),
                ),
                child: CachedNetworkImage(
                  imageUrl: "https://static.toiimg.com/photo/62601713.cms",
                  fit: BoxFit.fill,
                  color: AppColor.blackTextColor.withOpacity(0.1),
                  colorBlendMode: BlendMode.color,
                  width: 100,
                  height: 100,
                ),
              ).paddingUpSide412(),
            ],
          ),
        ).paddingUpSide812());
  }

  void _showItemDetailBottomSheet() {
    Get.bottomSheet(
      Container(
        height: Get.height / 2, // Cover half of the screen
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.vertical(
            top: Radius.circular(20),
          ),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // Replace the placeholder content with the image
            Image.network(
              "https://static.toiimg.com/photo/62601713.cms",
              width: double.infinity,
              height: Get.height / 4,
              fit: BoxFit.cover,
            ),
            // Add any additional content you want in the bottom sheet
            Text(
              "Item Detail",
              style: AppTextStyles.textStyleBlack18With700,
            ),
            // Add more content here if needed
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(GetMaterialApp(
    home: RestaurantDetailScreen(),
  ));
}
