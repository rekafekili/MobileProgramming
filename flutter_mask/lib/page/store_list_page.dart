import 'package:flutter/material.dart';
import 'package:fluttermask/viewmodel/store_view_model.dart';
import 'package:fluttermask/widget/store_list.dart';

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
      ),
      body: Container(
        padding: EdgeInsets.all(10),
        child: Column(
          children: <Widget>[
            Container(
              padding: EdgeInsets.fromLTRB(10, 0, 10, 0),
              decoration: BoxDecoration(
                color: Colors.grey,
                borderRadius: BorderRadius.circular(5),
              ),
              child: TextField(
                controller: _controller,
                onSubmitted: (value) {
                  if (value.isNotEmpty) {
                    viewModel.fetchStores(value);
                    _controller.clear();
                  }
                },
                style: TextStyle(color: Colors.white),
                decoration: InputDecoration(
                    border: InputBorder.none,
                    hintStyle: TextStyle(color: Colors.white),
                    hintText: "Ex : 충청남도 천안시 동남구"),
              ),
            ),
            Expanded(
              child: StoreList(stores: viewModel.stores),
            )
          ],
        ),
      ),
    );
  }
}
