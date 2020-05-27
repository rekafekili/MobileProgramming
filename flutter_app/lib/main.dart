import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

// 미리보기 : https://flutterstudio.app/
// HotReload -> 저장하면 자동으로 변경된 앱을 띄워준다.
class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: HelloPage("Yutang"));
  }
}

// stful 입력 후 엔터 -> 자동 완성
// StatefulWidget = 상태를 가지고 있고, 화면을 변경시킬 수 있다.
class HelloPage extends StatefulWidget {
  final String title;

  HelloPage(this.title);

  @override
  _HelloPageState createState() => _HelloPageState();
}

class _HelloPageState extends State<HelloPage> {
  String _message = "Hello World"; // _를 앞에 붙이면 private 으로 접근 지정된다.
  int _count = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        floatingActionButton: FloatingActionButton(
            child: Icon(Icons.add), onPressed: _changeMessage),
        appBar: AppBar(title: Text(widget.title)),
        body: Center(
            child: Column(
              mainAxisAlignment:  MainAxisAlignment.center,
              children: <Widget>[
                Text(_message, style: TextStyle(fontSize: 30)),
                Text("$_count", style: TextStyle(fontSize: 30)),
                RaisedButton(
                    child: Text("화면 이동")),
          ],
        )));
  }

  //child: Text(_message + " : " + _count.toString(), style: TextStyle(fontSize: 30)))
  void _changeMessage() {
    setState(() {
      _message = "Bye World";
      _count++;
    });
  }
}
