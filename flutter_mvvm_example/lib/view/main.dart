import 'package:flutter/material.dart';

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

  @override
  void initState() {
    textFieldController.addListener(() {
      var email = textFieldController.text;
      var isValid = EmailValidator.isEmailValid(email);
      var errorText;
      if (!isValid) {
        errorText = "Invalid Email";
      }

      setState(() {
        _isEmailValid = isValid;
        _emailErrorText = errorText;
      });
    });
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
              TextField(
                controller: textFieldController,
                obscureText: true,
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Enter a Password'),
              ),
              FloatingActionButton(
                child: Icon(Icons.print),
                onPressed: () => showDialog(
                    context: context,
                    builder: (context) {
                      return AlertDialog(
                        content: Text(textFieldController.text),
                      );
                    }),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Expanded(
                    child: TextField(
                      controller: textFieldController,
                      keyboardType: TextInputType.emailAddress,
                      decoration: InputDecoration(
                          border: OutlineInputBorder(),
                          labelText: "Email",
                          errorText: _emailErrorText),
                    ),
                  ),
                ],
              )
            ],
          )),
    );
  }
}

class EmailValidator {
  static isEmailValid(String str) {
    return str.contains("@");
  }
}
