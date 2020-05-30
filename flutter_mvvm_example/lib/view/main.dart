import 'package:flutter/material.dart';
import 'package:fluttermvvmexample/viewmodel/main_viewmodel.dart';

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
  final textFieldController = TextEditingController();
  var _isEmailValid;
  var _emailErrorText;

  final _viewModel = SubscriptionViewModelImpl();

  @override
  void initState() {
    textFieldController.addListener(
        () => _viewModel.inputMailText.add(textFieldController.text));
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('MVVM SAMPLE')),
      body: Container(
          margin: EdgeInsets.fromLTRB(20, 20, 20, 20),
          child: Column(
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Expanded(
                    child: StreamBuilder<String>(
                      stream: _viewModel.outputErrorText,
                      builder: (context, snapshot) {
                        return TextField(
                          controller: textFieldController,
                          keyboardType: TextInputType.emailAddress,
                          decoration: InputDecoration(
                              labelText: "mvvm email",
                              errorText: snapshot.data),
                        );
                      },
                    ),
                  ),
                  StreamBuilder(
                      stream: _viewModel.outputIsButtonEnabled,
                      builder: (context, snapshot) {
                        return RaisedButton(
                          onPressed: () {},
                        );
                      })
                ],
              )
            ],
          )),
    );
  }
}
