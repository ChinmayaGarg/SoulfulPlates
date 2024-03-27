import '../../../app_singleton.dart';
import '../../../constants/app_theme.dart';
import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../model/payment_model.dart';
import '../../../network/network_interfaces/end_points.dart';
import '../../../network/network_interfaces/i_dio_singleton.dart';
import '../../../network/network_utils/api_call.dart';
import '../../../utils/pagination_utils.dart';

class TransactionsController extends BaseController
    with PaginationUtils<PaymentModel> {
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
    var response = await ApiCall().call<PaymentModel>(
        method: RequestMethod.getPost,
        endPoint: EndPoints.paymentHistoryBuyer,
        apiCallType: ApiCallType.user,
        obj: PaymentModel(),
        parameters: {
          "userId": AppSingleton.loggedInUserProfile?.id,
          "limit": recordsPerPage,
          "offset": pageNo,
          "status": PaymentStatus.Pending.name
        });

    if (response != null) {
      List<PaymentModel> temp = response;
      if (temp.isEmpty || temp.length < recordsPerPage) {
        hasReachedMax = true;
      }
      if (pageNo == 0) {
        dataList.clear();
      }
      if (temp.isNotEmpty) {
        dataList.addAll(temp);
      }
      updateLoader(ViewStateEnum.idle);
    } else {
      dataList = [];
      updateLoader(ViewStateEnum.idle);
    }
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
