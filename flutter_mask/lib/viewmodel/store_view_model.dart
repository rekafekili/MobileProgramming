import 'package:flutter/material.dart';
import 'package:fluttermask/model/store.dart';
import 'package:fluttermask/service/http_service.dart';

class StoreListViewModel extends ChangeNotifier {
  List<StoreViewModel> stores = List<StoreViewModel>();
  bool isLoading = false;

  Future<void> fetchStores(String address) async {
    isLoading = true;
    notifyListeners();

    final results = await WebService().fetchStores(address);
    this.stores = results
        .where((element) {
          return element.remainStat == 'plenty' ||
              element.remainStat == 'some' ||
              element.remainStat == 'few';
        })
        .map((item) => StoreViewModel(store: item))
        .toList();

    isLoading = false;
    notifyListeners();
  }
}

// Only Get
class StoreViewModel {
  final Store store;

  StoreViewModel({this.store});

  String get addr {
    return this.store.addr;
  }

  String get code {
    return this.store.code;
  }

  String get createdAt {
    return this.store.createdAt;
  }

  double get latitude {
    return this.store.lat;
  }

  double get longitude {
    return this.store.lng;
  }

  String get name {
    return this.store.name;
  }

  RemainDescription get remainStat {
    return RemainDescription(this.store.remainStat);
  }

  String get stockAt {
    return this.store.stockAt;
  }

  String get type {
    return this.store.type;
  }
}

/// "remainStat"에 대한 추가 설명을 담는 클래스
class RemainDescription {
  var remainState;
  var description;
  var color;

  RemainDescription(String remainStat) {
    switch (remainStat) {
      case 'plenty':
        this.remainState = '충분';
        this.description = '100개 이상';
        this.color = Colors.green;
        break;
      case 'some':
        this.remainState = '보통';
        this.description = '30 ~ 100개';
        this.color = Colors.yellow;
        break;
      case 'few':
        this.remainState = '부족';
        this.description = '2 ~ 30개';
        this.color = Colors.red;
        break;
      case 'empty':
        this.remainState = '소진 임박';
        this.description = '1개 이하';
        this.color = Colors.grey;
        break;
      default:
        this.remainState = '판매 중지';
        this.description = '판매 중지';
        this.color = Colors.black;
    }
  }
}
