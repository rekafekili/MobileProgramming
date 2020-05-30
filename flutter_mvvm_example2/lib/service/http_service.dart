import 'package:http/http.dart' as http;
import 'dart:convert';

import '../model/movie.dart';

class Webservice {

  Future<List<Movie>> fetchMovies(String keyword) async {
    final OMDB_API_KEY = "920f56f6";
    final url = "http://www.omdbapi.com/?s=$keyword&apikey=$OMDB_API_KEY";
    final response = await http.get(url);

    if(response.statusCode == 200) {
      final body = jsonDecode(response.body);
      final Iterable json = body["Search"];
      return json.map((movie) => Movie.fromJson(movie)).toList();
    } else {
      throw Exception("Unable to perform request!");
    }
  }
}