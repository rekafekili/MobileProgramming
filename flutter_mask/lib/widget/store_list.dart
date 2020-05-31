import 'package:flutter/material.dart';
import 'package:fluttermask/viewmodel/store_view_model.dart';

class StoreList extends StatelessWidget {
  final List<StoreViewModel> stores;

  StoreList({this.stores});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: stores.length,
        itemBuilder: (context, index) {
          final store = stores[index];

          return ListTile(
            title: Text(store.name),
            subtitle: Text(store.addr),
            trailing: Column(
              children: <Widget>[
                Text(store.remainStat.remainState,
                    style: TextStyle(
                        color: store.remainStat.color,
                        fontWeight: FontWeight.bold)),
                Text(
                  store.remainStat.description,
                  style: TextStyle(
                    color: store.remainStat.color,
                  ),
                )
              ],
            ),
          );
        });
  }
}