import 'package:flutter/material.dart';
import 'package:fluttermvvmexample2/service/http_service.dart';
import 'package:fluttermvvmexample2/model/movie.dart';

class MovieListViewModel extends ChangeNotifier {
  List<MovieViewModel> movies = List<MovieViewModel>();

  Future<void> fetchMovies(String keyword) async {
    final results = await Webservice().fetchMovies(keyword);
    this.movies = results.map((item) => MovieViewModel(movie: item)).toList();
    print(this.movies);
    notifyListeners();
  }
}

class MovieViewModel {
  final Movie movie;

  MovieViewModel({this.movie});

  String get title {
    return this.movie.title;
  }

  String get poster {
    return this.movie.poster;
  }
}