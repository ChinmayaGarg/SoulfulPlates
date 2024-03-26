import 'package:soulful_plates/app_singleton.dart';
import 'package:soulful_plates/model/location/address_model.dart';
import 'package:soulful_plates/model/menu/menu_model.dart';
import 'package:soulful_plates/model/order_detail_model.dart';
import 'package:soulful_plates/model/payment_model.dart';

import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../model/data_model.dart';
import '../../../model/store_model.dart';
import '../../../network/network_interfaces/end_points.dart';
import '../../../network/network_interfaces/i_dio_singleton.dart';
import '../../../network/network_utils/api_call.dart';
import '../../../utils/pagination_utils.dart';
import '../../../utils/utils.dart';

class WishlistController extends BaseController
    with PaginationUtils<DataModel> {
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
    updateLoader(ViewStateEnum.busy);
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getAddress() async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<AddressModel>(
      method: RequestMethod.get,
      endPoint:
          "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}",
      obj: AddressModel(),
      apiCallType: ApiCallType.seller,
    );
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getNearByStores() async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<StoreModel>(
      method: RequestMethod.get,
      endPoint: "${EndPoints.getNearByStores}/${6}/2001",
      obj: StoreModel(),
      apiCallType: ApiCallType.seller,
    );
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void addAddress({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint:
            "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Address created successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while creating Address. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void updateAddress({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint:
            "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}/${data['id']}",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Address update successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while update Address. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void deleteAddress({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.delete,
        endPoint:
            "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}/${data['id']}",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Address delete successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while delete Address. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void updateNotificationStatus({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.put,
        endPoint:
            "${EndPoints.updateNotificationStatus}/${AppSingleton.loggedInUserProfile?.id}",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("updateNotificationStatus successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while updateNotificationStatus. Please try again later.",
          false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getUserProfile() async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<AddressModel>(
      method: RequestMethod.get,
      endPoint:
          "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}",
      obj: AddressModel(),
      apiCallType: ApiCallType.seller,
    );
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void addCategory({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.addCategory,
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Category created successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while creating category. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void addSubCategory({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.addSubCategory,
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Sub Category created successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while creating sub category. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void editSubCategory({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.put,
        endPoint: EndPoints.addSubCategory + "/12",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Sub Category created successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while creating sub category. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void addMenuItem({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.addMenuItem,
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("addMenuItem created successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while creating addMenuItem. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getMenuItems() async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<MenuModel>(
      method: RequestMethod.get,
      endPoint: "${EndPoints.getMenuItem}/1",
      obj: MenuModel(),
      apiCallType: ApiCallType.seller,
    );
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getOrderDetails({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<OrderDetailModel>(
        method: RequestMethod.getPost,
        endPoint: EndPoints.getOrderDetails,
        apiCallType: ApiCallType.user,
        obj: OrderDetailModel(),
        parameters: data);
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getOrdersForUser({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<OrderDetailModel>(
        method: RequestMethod.getPost,
        endPoint: EndPoints.getOrdersForUser,
        apiCallType: ApiCallType.user,
        obj: OrderDetailModel(),
        parameters: data);
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getOrdersForStore({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<OrderDetailModel>(
        method: RequestMethod.getPost,
        endPoint: EndPoints.getOrdersForStore,
        apiCallType: ApiCallType.seller,
        obj: OrderDetailModel(),
        parameters: data);
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  /*
  {
	"userId": 1,
	"limit": 20,
	"offset": 0,
	"status": "Pending"
}
   */
  void getPaymentHistoryBuyer({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<PaymentModel>(
        method: RequestMethod.getPost,
        endPoint: EndPoints.paymentHistoryBuyer,
        apiCallType: ApiCallType.user,
        obj: PaymentModel(),
        parameters: data);
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getPaymentHistorySeller({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<PaymentModel>(
        method: RequestMethod.getPost,
        endPoint: EndPoints.paymentHistoryStore,
        obj: PaymentModel(),
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getAverageRating() async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
      method: RequestMethod.get,
      endPoint: EndPoints.getAverageRating + "/1",
      apiCallType: ApiCallType.seller,
    );
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void createPayment({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.createPayment,
        apiCallType: ApiCallType.user,
        parameters: data);
    // {"code":1,"description":"Order Created.","data":{"orderId":5}}
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void updatePaymentStatus({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.updatePaymentStatus,
        apiCallType: ApiCallType.user,
        parameters: data);
    // {"code":1,"description":"Order Created.","data":{"orderId":5}}
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void updateTransactionStatus({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.updateTransactionStatus,
        apiCallType: ApiCallType.user,
        parameters: data);
    // {"code":1,"description":"Order Created.","data":{"orderId":5}}
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void createRating({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.createRating,
        apiCallType: ApiCallType.user,
        parameters: data);
    // {"code":1,"description":"Order Created.","data":{"orderId":5}}
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void createOrder({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.createOrder,
        apiCallType: ApiCallType.user,
        parameters: data);
    // {"code":1,"description":"Order Created.","data":{"orderId":5}}
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void updateOrderStatus({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint: EndPoints.updateOrderStatus,
        apiCallType: ApiCallType.user,
        parameters: data);
    // {"code":1,"description":"Order Created.","data":{"orderId":5}}
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
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
    getDataFromAPI();
    update();
  }
}
