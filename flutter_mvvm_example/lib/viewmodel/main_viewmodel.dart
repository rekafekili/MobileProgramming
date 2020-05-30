import 'dart:async';

abstract class SubscriptionViewModel {
  Sink get inputMailText;
  Stream<bool> get outputIsButtonEnabled;
  Stream<String> get outputErrorText;

  void dispose();
}

class SubscriptionViewModelImpl implements SubscriptionViewModel {
  var _mailTextController = StreamController<String>.broadcast();

  @override
  Sink get inputMailText => _mailTextController;

  @override
  Stream<bool> get outputIsButtonEnabled => _mailTextController.stream
      .map((email) => EmailValidator.isEmailValid(email));

  @override
  Stream<String> get outputErrorText => outputIsButtonEnabled
      .map((isEnabled) => isEnabled ? null : "Invalid email");

  @override
  void dispose() => _mailTextController.close();
}

class EmailValidator {
  static isEmailValid(String str) {
    return str.contains("@");
  }
}