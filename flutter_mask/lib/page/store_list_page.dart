import 'package:flutter/material.dart';
import 'package:fluttermask/viewmodel/store_view_model.dart';
import 'package:fluttermask/widget/area_dropdown.dart';
import 'package:fluttermask/widget/common_widgets.dart';
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
        title: viewModel.stores.isEmpty ? Text('공적 마스크') : Text('공적 마스크 판매처 : ${viewModel.stores.length}개'),
        backgroundColor: Colors.lightGreen,
      ),
      body: Container(
        color: Color(int.parse('#DEFADC'.replaceFirst('#', '0xFF'))),
        padding: EdgeInsets.all(10),
        child: Column(
          children: <Widget>[
            StoreSearch(controller: _controller, viewModel: viewModel),
            SizedBox(
              height: 10,
            ),
            viewModel.isLoading
                ? LoadingWidget()
                : viewModel.stores.isEmpty
                    ? NoResultTextWidget()
                    : Expanded(
                        child: StoreList(stores: viewModel.stores),
                      )
          ],
        ),
      ),
    );
  }
}
