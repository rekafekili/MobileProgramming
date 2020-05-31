import 'dart:typed_data';

import 'package:flutter/material.dart';

class LoadingWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Expanded(
        child: Container(
            decoration: BoxDecoration(color: Colors.white),
            constraints: BoxConstraints.expand(),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text('정보를 가져오는 중'),
                CircularProgressIndicator(),
              ],
            )));
  }
}

class NoResultTextWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Container(
        constraints: BoxConstraints.expand(),
        decoration: BoxDecoration(color: Colors.white),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Icon(
              Icons.report_problem,
              size: 100.0,
              color: Colors.grey,
            ),
            SizedBox(height: 20),
            Text(
              '검색 결과가 없습니다.',
              style: TextStyle(
                  fontSize: 30,
                  fontWeight: FontWeight.bold,
                  color: Colors.grey),
            ),
            SizedBox(height: 10),
            Text(
              '주소를 기준으로 해당 "구" 또는 "동" 내에 존재하는\n 판매처 및 재고 상태 등의 판매 정보 제공합니다.\n"시" 단위만 입력하는 것은 불가능합니다.',
              textAlign: TextAlign.center,
              style: TextStyle(color: Colors.grey),
            )
          ],
        ),
      ),
    );
  }
}
