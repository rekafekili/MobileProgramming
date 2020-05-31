import 'package:flutter/material.dart';
import 'package:fluttermask/viewmodel/store_view_model.dart';

class StoreSearch extends StatelessWidget {
  final TextEditingController controller;
  final StoreListViewModel viewModel;

  StoreSearch({this.controller, this.viewModel});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(5),
      ),
      child: Theme(
        data: ThemeData(
          primaryColor: Colors.black26,
        ),
        child: TextField(
          controller: controller,
          onSubmitted: (value) {
            if (value.isNotEmpty) {
              viewModel.fetchStores(value);
              controller.clear();
            }
          },
          style: TextStyle(color: Colors.black),
          decoration: InputDecoration(
              border: OutlineInputBorder(),
              hintStyle: TextStyle(color: Colors.grey),
              labelText: "검색",
              hintText: "Ex : 충청남도 천안시 동남구 병천면",
              prefixIcon: Icon(Icons.search)),
        ),
      )
    );
  }
}
