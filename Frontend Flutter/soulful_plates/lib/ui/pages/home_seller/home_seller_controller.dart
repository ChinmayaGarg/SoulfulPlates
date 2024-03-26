import 'package:soulful_plates/app_singleton.dart';

import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../model/data_model.dart';
import '../../../utils/pagination_utils.dart';

class HomeSellerController extends BaseController
    with PaginationUtils<DataModel> {
  @override
  void onInit() {
    super.onInit();
    print("This is current token ${AppSingleton.loggedInUserProfile?.id}");
    print(
        "This is current token ${AppSingleton.loggedInUserProfile?.sellerId}");
    print("This is current token ${AppSingleton.loggedInUserProfile?.token}");
    initPagination();
  }

  updateLoader(ViewStateEnum state) {
    if (pageNo >= 1) {
      moreLoading = state;
    } else {
      setLoaderState(state);
    }
  }

  void getDataFromAPI() async {}

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
