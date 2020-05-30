import 'package:flutter/material.dart';
import 'package:fluttermask/model/store.dart';
import 'package:fluttermask/page/store_list_page.dart';
import 'package:fluttermask/viewmodel/store_view_model.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        home: ChangeNotifierProvider(
          create: (context) => StoreListViewModel(),
          child: StoreListPage(),
        )
    );
  }
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
