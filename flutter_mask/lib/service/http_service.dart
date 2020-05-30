import 'dart:convert';
import 'package:fluttermask/model/store.dart';
import 'package:http/http.dart' as http;

// Swagger : https://app.swaggerhub.com/apis-docs/Promptech/public-mask-info/20200307-oas3
class WebService {
  // 주소를 기준으로 해당 구 또는 동내에 존재하는 판매처 및 재고 상태 등의 판매 정보 제공
  // 예 : '서울특별시 강남구' or '서울특별시 강남구 논현동'
  // 단, '시' 단위만 입력하는 것은 불가능
  Future<List<Store>> fetchStores(String address) async {
    final requestUrl = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByAddr/json?address=$address";
    final response = await http.get(requestUrl);

    if(response.statusCode == 200) {
      final jsonResult = jsonDecode(utf8.decode(response.bodyBytes));
      final Iterable json = jsonResult["stores"];
      return json.map((store) => Store.fromJson(store)).toList();
    } else {
      throw Exception("!!! NO RESULT !!!");
    }
  }
}
