import 'package:flutter/material.dart';
import 'package:fluttermask/viewmodel/store_view_model.dart';
import 'package:fluttermask/widget/common_widgets.dart';

class StoreList extends StatelessWidget {
  final List<StoreViewModel> stores;

  StoreList({this.stores});

  @override
  Widget build(BuildContext context) {
    return stores.isEmpty
        ? NoResultTextWidget()
        : Container(
            padding: EdgeInsets.fromLTRB(0, 10, 0, 10),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(10),
            ),
            child: ListView.builder(
              itemCount: stores.length,
              itemBuilder: (context, index) {
                final store = stores[index];

                return Card(
                  shape: StadiumBorder(
                      side: BorderSide(
                    color: store.remainStat.color,
                  )),
                  child: ListTile(
                    dense: true,
                    title: Text(
                      store.name,
                      style:
                          TextStyle(fontWeight: FontWeight.bold, fontSize: 15),
                    ),
                    subtitle: Text(store.addr),
                    trailing: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Text(store.remainStat.remainState,
                            style: TextStyle(
                                color: store.remainStat.color,
                                fontWeight: FontWeight.bold)),
                        SizedBox(height: 10),
                        Text(
                          store.remainStat.description,
                          style: TextStyle(
                            color: store.remainStat.color,
                          ),
                        )
                      ],
                    ),
                  ),
                );
              },
            ),
          );
  }
}
