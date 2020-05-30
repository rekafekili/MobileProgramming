import 'package:flutter/material.dart';
import 'package:fluttermvvmexample2/page/movie_list_page.dart';
import 'package:fluttermvvmexample2/viewmodel/main_viewmodel.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Movies',
      home:ChangeNotifierProvider(
        create: (context) => MovieListViewModel(),
        child: MovieListPage(),
      )
    );
  }
}