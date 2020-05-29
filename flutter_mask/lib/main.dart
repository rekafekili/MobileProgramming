import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:fluttermask/model/store.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final stores = List<Store>();
  var isLoading = true;

  // 비동기 async(Future 반환) - await
  // 비동기 메소드의 반환값은 Future가 되어야 하며, 특정 타입을 지정할 때는 Future<int> 같이 제네릭을 사용한다.
  Future fetch() async {
    setState(() {
      isLoading = true;
    });
    var url =
        "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json?lat=37.266389&lng=126.999333&m=5000";

    // await를 사용하여 해당 호출이 완료될때까지 기다려준다.(비동기 안에서 동기화 해준다.)
    var response = await http.get(url);

    final jsonResult = jsonDecode(utf8.decode(response.bodyBytes));

    final jsonStores = jsonResult['stores'];

    // setState : 화면을 갱신하도록 요청하는 메소드
    setState(() {
      stores.clear();
      jsonStores.forEach((jsonStore) {
        stores.add(Store.fromJson(jsonStore));
      });
      isLoading = false;
    });

    print('Fetch Complete');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("마스크 재고 있는 곳 : ${stores.where((e) {
          return e.remainStat == 'plenty' ||
              e.remainStat == 'some' ||
              e.remainStat == 'few';
        }).length}곳"),
        actions: <Widget>[
          IconButton(
            onPressed: fetch,
            icon: Icon(Icons.refresh),
          )
        ],
      ),
      body: isLoading
          ? loadingWidget()
          : ListView(
              children: stores.where((e) {
                return e.remainStat == 'plenty' ||
                    e.remainStat == 'some' ||
                    e.remainStat == 'few';
              }).map((e) {
                return ListTile(
                  title: Text(e.name),
                  subtitle: Text(e.addr),
                  trailing: _buildRemainStatWidget(e),
                );
              }).toList(),
            ),
    );
  }

  Widget _buildRemainStatWidget(Store store) {
    var remainStat = '판매중지';
    var description = '판매중지';
    var color = Colors.black;

    switch (store.remainStat) {
      case 'plenty':
        remainStat = '충분';
        description = '100개 이상';
        color = Colors.green;
        break;
      case 'some':
        remainStat = '보통';
        description = '30 ~ 100개';
        color = Colors.yellow;
        break;
      case 'few':
        remainStat = '부족';
        description = '2 ~ 30개';
        color = Colors.red;
        break;
      case 'empty':
        remainStat = '소진임박';
        description = '1개 이하';
        color = Colors.grey;
        break;
    }

    return Column(
      children: <Widget>[
        Text(remainStat,
            style: TextStyle(color: color, fontWeight: FontWeight.bold)),
        Text(description, style: TextStyle(color: color)),
      ],
    );
  }

  Widget loadingWidget() {
    return Center(
        child: Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Text('정보를 가져오는 중'),
        CircularProgressIndicator(),
      ],
    ));
  }
}
