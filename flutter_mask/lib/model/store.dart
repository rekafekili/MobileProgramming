class Store {
  String code;
  String name;
  String addr;
  String type;
  num lat; // dart에서 Int와 Double은 서로 호환되지 않으므로 num을 사용한다.
  num lng;
  String stockAt;
  String remainStat;
  String createdAt;

  Store(
      {this.code,
      this.name,
      this.addr,
      this.type,
      this.lat,
      this.lng,
      this.stockAt,
      this.remainStat,
      this.createdAt});

  // factory : 임의로 Store 인스턴스를 생성하여 반환한다.
  factory Store.fromJson(Map<String, dynamic> json) {
    return Store(
        code: json['code'],
        name: json['name'],
        addr: json['addr'],
        type: json['type'],
        lat: json['lat'],
        lng: json['lng'],
        stockAt: json['stock_at'],
        remainStat: json['remain_stat'],
        createdAt: json['created_at']);
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['code'] = this.code;
    data['name'] = this.name;
    data['addr'] = this.addr;
    data['type'] = this.type;
    data['lat'] = this.lat;
    data['lng'] = this.lng;
    data['stock_at'] = this.stockAt;
    data['remain_stat'] = this.remainStat;
    data['created_at'] = this.createdAt;
    return data;
  }
}
