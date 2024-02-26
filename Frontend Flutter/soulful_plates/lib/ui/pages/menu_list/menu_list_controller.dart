import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../model/menu/menu_item_model.dart';
import '../../../utils/pagination_utils.dart';
import '../../../utils/utils.dart';

class MenuListController extends BaseController
    with PaginationUtils<MenuCategory> {
  @override
  void onInit() {
    super.onInit();
    initPagination();
  }

  updateLoader(ViewStateEnum state) {
    if (pageNo >= 1) {
      moreLoading = state;
    } else {
      setLoaderState(state);
    }
  }

  void getDataFromAPI() async {
    setLoaderState(ViewStateEnum.busy);
    await Future.delayed(const Duration(seconds: 1));
    // List<MenuItemModel> menuItems = [];
    // menuItems.add(MenuItemModel(
    //     itemName: "Pizza",
    //     itemImage: "",
    //     itemPrice: "13",
    //     type: "Veg",
    //     category: "Fast Food",
    //     subCategory: "Quick Bites",
    //     servingType: 2,
    //     portion: "Medium",
    //     inStock: true,
    //     isRecommended: false,
    //     description: "It contains mayo and margarita cheese sauce."));
    // menuItems.add(MenuItemModel(
    //     itemName: "Burger",
    //     itemImage: "",
    //     itemPrice: "8",
    //     type: "eggs",
    //     category: "Fast Food",
    //     subCategory: "Quick Bites",
    //     servingType: 1,
    //     portion: "Regular",
    //     inStock: true,
    //     isRecommended: false,
    //     description:
    //         "It contains onions, lettuce and tomatoes with veg patty."));
    // menuItems.add(MenuItemModel(
    //     itemName: "French Fries",
    //     itemImage: "",
    //     itemPrice: "4",
    //     type: "nonveg",
    //     category: "Starter",
    //     subCategory: "Fried",
    //     servingType: 1,
    //     portion: "Regular",
    //     inStock: true,
    //     isRecommended: false,
    //     description: "It contains fresh potatoes with peri-peri sprinkle."));
    //
    // for (var item in menuItems) {
    //   // Find the category
    //   var category =
    //       dataList.firstWhere((c) => c.name == item.category, orElse: () {
    //     var newCategory = MenuCategory(item.category!, []);
    //     dataList.add(newCategory);
    //     return newCategory;
    //   });
    //
    //   // Find the subcategory
    //   var subCategory = category.subcategories
    //       .firstWhere((s) => s.name == item.subCategory, orElse: () {
    //     var newSubCategory = SubCategory(item.subCategory!, []);
    //     category.subcategories.add(newSubCategory);
    //     return newSubCategory;
    //   });
    //
    //   // Add the item to the subcategory
    //   subCategory.items.add(item);
    // }
    dataList = Utils.menuCategory;
    setLoaderState(ViewStateEnum.idle);
  }

  @override
  bool isLoading() {
    return state == ViewStateEnum.busy || moreLoading == ViewStateEnum.busy;
  }

  @override
  void fetchData() {
    getDataFromAPI();
    update();
  }

  @override
  void loadMore() {
    // getDataFromAPI();
    update();
  }
}
