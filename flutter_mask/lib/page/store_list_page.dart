import 'package:flutter/material.dart';
import 'package:fluttermask/viewmodel/store_view_model.dart';
import 'package:fluttermask/widget/area_dropdown.dart';
import 'package:fluttermask/widget/store_list.dart';
import 'package:fluttermask/widget/store_search.dart';

import 'package:provider/provider.dart';

class StoreListPage extends StatefulWidget {
  @override
  _StoreListPageState createState() => _StoreListPageState();
}

class _StoreListPageState extends State<StoreListPage> {
  final TextEditingController _controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<StoreListViewModel>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text('공적 마스크'),
        backgroundColor: Colors.lightGreen,
      ),
      body: Container(
        color: Color(int.parse('#DEFADC'.replaceFirst('#', '0xFF'))),
        padding: EdgeInsets.all(10),
        child: Column(
          children: <Widget>[
            StoreSearch(controller: _controller, viewModel: viewModel),
            Expanded(
              child: StoreList(stores: viewModel.stores),
            )
          ],
        ),
      ),
    );
  }
}
