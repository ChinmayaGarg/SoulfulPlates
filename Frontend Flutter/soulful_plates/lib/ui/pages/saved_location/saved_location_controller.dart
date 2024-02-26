import 'package:soulful_plates/controller/base_controller.dart';
import 'package:soulful_plates/model/location/location_model.dart';

import '../../../constants/enums/view_state.dart';
import '../../../utils/pagination_utils.dart';

class SavedLocationController extends BaseController
    with PaginationUtils<LocationModel> {
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
    dataList = LocationModel.getAllLocations();
    /*updateLoader(ViewStateEnum.busy);
    var result = ; //male api call here

    if (result.hasException) {
      dataList = [];
      updateLoader(ViewStateEnum.idle);
      update();
      return;
    }

    if (result.data != null && result.data!.containsKey('data')) {
      List<DataModel> temp = DataModel.fromJsonArray(result.data!['data']);
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
    }*/
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
