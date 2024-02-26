import 'package:soulful_plates/network/network_interfaces/generic_model.dart';

/// item_name : ""
/// description : ""
/// category : ""
/// sub_category : ""
/// item_price : ""
/// type : ""
/// in_stock : true
/// is_recommended : true
/// item_image : ""
/// serving_type : 1
/// portion : ""

class MenuItemModel implements GenericModel {
  MenuItemModel({
    String? itemName,
    String? description,
    String? category,
    String? subCategory,
    String? itemPrice,
    String? type,
    bool? inStock,
    bool? isRecommended,
    String? itemImage,
    num? servingType,
    String? portion,
  }) {
    _itemName = itemName;
    _description = description;
    _category = category;
    _subCategory = subCategory;
    _itemPrice = itemPrice;
    _type = type;
    _inStock = inStock;
    _isRecommended = isRecommended;
    _itemImage = itemImage;
    _servingType = servingType;
    _portion = portion;
  }

  MenuItemModel.fromJson(dynamic json) {
    _itemName = json['item_name'];
    _description = json['description'];
    _category = json['category'];
    _subCategory = json['sub_category'];
    _itemPrice = json['item_price'];
    _type = json['type'];
    _inStock = json['in_stock'];
    _isRecommended = json['is_recommended'];
    _itemImage = json['item_image'];
    _servingType = json['serving_type'];
    _portion = json['portion'];
  }
  String? _itemName;
  String? _description;
  String? _category;
  String? _subCategory;
  String? _itemPrice;
  String? _type;
  bool? _inStock;
  bool? _isRecommended;
  String? _itemImage;
  num? _servingType;
  String? _portion;
  MenuItemModel copyWith({
    String? itemName,
    String? description,
    String? category,
    String? subCategory,
    String? itemPrice,
    String? type,
    bool? inStock,
    bool? isRecommended,
    String? itemImage,
    num? servingType,
    String? portion,
  }) =>
      MenuItemModel(
        itemName: itemName ?? _itemName,
        description: description ?? _description,
        category: category ?? _category,
        subCategory: subCategory ?? _subCategory,
        itemPrice: itemPrice ?? _itemPrice,
        type: type ?? _type,
        inStock: inStock ?? _inStock,
        isRecommended: isRecommended ?? _isRecommended,
        itemImage: itemImage ?? _itemImage,
        servingType: servingType ?? _servingType,
        portion: portion ?? _portion,
      );
  String? get itemName => _itemName;
  String? get description => _description;
  String? get category => _category;
  String? get subCategory => _subCategory;
  String? get itemPrice => _itemPrice;
  String? get type => _type;
  bool get inStock => _inStock ?? true;
  bool get isRecommended => _isRecommended ?? false;
  String? get itemImage => _itemImage;
  num? get servingType => _servingType;
  String? get portion => _portion;

  setRecommended(value) {
    _isRecommended = value;
  }

  setInStock(value) {
    _inStock = value;
  }

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['item_name'] = _itemName;
    map['description'] = _description;
    map['category'] = _category;
    map['sub_category'] = _subCategory;
    map['item_price'] = _itemPrice;
    map['type'] = _type;
    map['in_stock'] = _inStock;
    map['is_recommended'] = _isRecommended;
    map['item_image'] = _itemImage;
    map['serving_type'] = _servingType;
    map['portion'] = _portion;
    return map;
  }

  @override
  from(json) {
    return MenuItemModel.fromJson(json);
  }
}

class MenuCategory {
  final String name;
  final List<SubCategory> subcategories;

  MenuCategory(this.name,
      this.subcategories); // Method to add a new subcategory to the current category
  void addSubCategory(SubCategory newSubCategory) {
    if (!subcategories.contains(newSubCategory)) {
      subcategories.add(newSubCategory);
    }
  }
}

class SubCategory {
  final String name;
  final List<MenuItemModel> items;

  SubCategory(this.name, this.items);
  // Method to add a new menu item to the current subcategory
  void addMenuItem(MenuItemModel newItem) {
    if (!items.contains(newItem)) {
      items.add(newItem);
    }
  }
}
