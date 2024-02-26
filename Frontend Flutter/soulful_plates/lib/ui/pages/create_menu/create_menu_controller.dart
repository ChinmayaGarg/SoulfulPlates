import 'package:flutter/cupertino.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:get/get_navigation/get_navigation.dart';
import 'package:soulful_plates/constants/enums/view_state.dart';
import 'package:soulful_plates/model/menu/menu_item_model.dart';

import '../../../controller/base_controller.dart';
import '../../../utils/utils.dart';

/*
Category - Starter or something
SubCategory - Veg Starter or Non Veg starter
Type: Veg, Non-veg, Eggs
ItemName
ItemPrice
Description
InStock
IsRecommended
ItemImage
ServingType - number 1-2-3-4 people
Portion - 3 Pieces or 250 ml text

//
ItemName
ItemPrice
Description
Recommended
InStock
ItemImage
 */

class CreateMenuController extends BaseController {
  TextEditingController itemName = TextEditingController();
  TextEditingController itemPrice = TextEditingController();
  TextEditingController description = TextEditingController();
  TextEditingController serviceType = TextEditingController();
  TextEditingController portion = TextEditingController();
  FocusNode itemNameFocus = FocusNode();
  FocusNode itemPriceFocus = FocusNode();
  FocusNode descriptionFocus = FocusNode();
  FocusNode serviceTypeFocus = FocusNode();
  FocusNode portionFocus = FocusNode();

  bool inStock = false;
  bool isRecommended = false;

  List<String> type = ["Veg", "NonVeg", "Eggs"];
  String selectType = "Veg";

  List<String> category = ["Starter", "Main Course", "Salads"];
  String selectCategory = "Starter";

  List<String> subCategory = ["Veg Starter", "Non-Veg Starter"];
  String selectSubCategory = "Veg Starter";

  List<MenuItemModel> menuItems = [];
  onSave() async {
    setLoaderState(ViewStateEnum.busy);
    await Future.delayed(const Duration(seconds: 2));
    Utils.addCategoryAndSubCategoryWithItems(
        selectCategory,
        selectSubCategory,
        SubCategory(selectSubCategory, [
          MenuItemModel(
              itemName: itemName.text,
              itemPrice: itemPrice.text,
              itemImage: itemName.text,
              portion: portion.text,
              servingType: 1,
              isRecommended: isRecommended,
              inStock: inStock,
              description: description.text,
              category: selectCategory,
              subCategory: selectSubCategory,
              type: selectType)
        ]));
    Utils.showSuccessToast("Item added.", false);
    setLoaderState(ViewStateEnum.idle);
    Get.back();
  }
}
