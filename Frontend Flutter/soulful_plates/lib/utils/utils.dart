import 'dart:async';

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/app_icons.dart';

import '../constants/app_colors.dart';
import '../constants/language/language_constants.dart';
import '../constants/size_config.dart';
import '../model/location/location_model.dart';
import '../model/menu/menu_item_model.dart';
import '../model/store_details/store_detail_model.dart';
import '../ui/widgets/base_loading_widget.dart';
import 'extensions.dart';

class Utils {
  static FilteringTextInputFormatter numberFormatter =
      FilteringTextInputFormatter.allow(RegExp("[0-9]"));
  static FilteringTextInputFormatter textFormatter =
      FilteringTextInputFormatter.allow(RegExp("[0-9]"));
  static FilteringTextInputFormatter emailRules =
      FilteringTextInputFormatter.allow(RegExp(r'[+~/>*<!#$%^&()=?/;:,€]'));

  static int timeDuration = 300;
  static Duration duration300 = Duration(milliseconds: timeDuration);

  static Future<bool> checkInternetConnectionAndShowMessage() async {
    ConnectivityResult connectivityResult =
        await Connectivity().checkConnectivity();
    if (connectivityResult == ConnectivityResult.none) {
      showSuccessToast(LanguageConst.internetNotAvailable, true);
      return false;
    } else {
      return true;
    }
  }

  static String padString(String input, int targetLength, String padChar) {
    if (input.length >= targetLength) {
      return input;
    } else {
      int padLength = targetLength - input.length;
      String padding = padChar * padLength;
      return input + padding;
    }
  }

  static showSuccessToast(String message, bool isError) {
    Fluttertoast.showToast(
      gravity: ToastGravity.BOTTOM,
      backgroundColor: isError ? AppColor.redColor : AppColor.blackTextColor,
      textColor: AppColor.whiteTextColor,
      msg: message.capitalizeFirst ?? message,
    );
  }

  static BoxBorder customBorderWidget() {
    return Border.all(
      color: AppColor.primaryColor,
      width: 1.5,
    );
  }

  static Widget emptyRefreshListWidget(
      {required String listEmptyMessage,
      Color? color,
      bool isLoading = false,
      required Function() onTap}) {
    return Container(
      width: double.infinity,
      alignment: Alignment.center,
      child: isLoading
          ? const Center(child: BaseLoadingWidget()).paddingAll16()
          : GestureDetector(
              onTap: onTap,
              child: SizedBox(
                width: double.infinity,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Icon(
                      Icons.refresh,
                      color: AppColor.blackColor,
                      size: 24,
                    ),
                    8.rVerticalSizedBox(),
                    Text(
                      listEmptyMessage,
                      textAlign: TextAlign.center,
                    ),
                  ],
                ),
              ),
            ).paddingAll16(),
    );
  }

  static List<LocationModel> locationList = [
    LocationModel(
      latitude: 44.6488,
      longitude: -63.5752,
      address: "123 Waterfront Dr, Halifax",
      locationName: "Anjali Home",
    ),
    LocationModel(
      latitude: 44.6714,
      longitude: -63.5772,
      address: "456 Spring Garden Rd, Halifax",
      locationName: "Dalhousie library",
    ),
    LocationModel(
      latitude: 44.6351,
      longitude: -63.5753,
      address: "789 Citadel Hill, Halifax",
      locationName: "Nikul Office",
    ),
  ];

  static addLocation({
    required double latitude,
    required double longitude,
    required String address,
    required String locationName,
  }) {
    locationList.add(
      LocationModel(
        latitude: latitude,
        longitude: longitude,
        address: address,
        locationName: locationName,
      ),
    );
  }

  static StoreDetails? storeDetails = StoreDetails(
    email: 'md2Retro@gmail.com',
    mobile: '4564561230',
    firstName: 'Md\'s Retro',
    street: '2320 Brunswick St',
    city: 'Halifax',
    state: 'Nova Scotia',
    postalCode: 'B3K2Z2',
  );

  String getFoodTypeIcon(String type) {
    if (type == 'veg') {
      return AppIcons.vegIcon;
    }
    if (type == 'nonveg') {
      return AppIcons.nonVegIcon;
    }
    return AppIcons.eggIcon;
  }

  static List<MenuCategory> menuCategory = [];

// Function to add category, subcategory, and menu items to the list if they don't exist
  static void addCategoryAndSubCategoryWithItems(
    String categoryName,
    String subCategoryName,
    SubCategory newSubCategory,
  ) {
    // Check if the category exists
    MenuCategory? category = menuCategory.firstWhereOrNull(
      (element) => element.name == categoryName,
    );

    // If the category doesn't exist, add it
    if (category == null) {
      category = MenuCategory(categoryName, []);
      menuCategory.add(category);
    }

    // Check if the subcategory exists
    var subCategory = category.subcategories.firstWhereOrNull(
      (element) => element.name == subCategoryName,
    );

    // If the subcategory doesn't exist, add it
    if (subCategory == null) {
      subCategory = SubCategory(subCategoryName, []);
      category.addSubCategory(subCategory);
    }

    // You can add logic here to check if menu items exist before adding
    // For simplicity, assuming all menu items are unique within a subcategory
    subCategory.items.addAll(newSubCategory.items);
  }

  static addMenuItems() {
    // Creating menu items
    MenuItemModel pizzaItem = MenuItemModel(
      itemName: "Pizza",
      itemImage: "",
      itemPrice: "13",
      type: "Veg",
      category: "Fast Food",
      subCategory: "Quick Bites",
      servingType: 2,
      portion: "Medium",
      inStock: true,
      isRecommended: false,
      description: "It contains mayo and margarita cheese sauce.",
    );

    MenuItemModel burgerItem = MenuItemModel(
      itemName: "Burger",
      itemImage: "",
      itemPrice: "8",
      type: "Eggs",
      category: "Fast Food",
      subCategory: "Quick Bites",
      servingType: 1,
      portion: "Regular",
      inStock: true,
      isRecommended: false,
      description: "It contains onions, lettuce, and tomatoes with veg patty.",
    );

    MenuItemModel friesItem = MenuItemModel(
      itemName: "French Fries",
      itemImage: "",
      itemPrice: "4",
      type: "Non-Veg",
      category: "Starter",
      subCategory: "Fried",
      servingType: 1,
      portion: "Regular",
      inStock: true,
      isRecommended: false,
      description: "It contains fresh potatoes with peri-peri sprinkle.",
    );

    // Creating subcategories with menu items
    SubCategory quickBites =
        SubCategory("Quick Bites", [pizzaItem, burgerItem]);
    SubCategory friedItems = SubCategory("Fried", [friesItem]);

    // Adding subcategories with items to categories
    addCategoryAndSubCategoryWithItems("Fast Food", "Quick Bites", quickBites);
    addCategoryAndSubCategoryWithItems("Starter", "Fried", friedItems);
    print("This is the respon se ${menuCategory}");
  }
}
