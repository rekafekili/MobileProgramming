import 'dart:collection';

import 'package:flutter/material.dart';

// TODO: "DropDownButton"을 여러개 두어 행정구역을 하나씩 선정하도록 하고 싶었으나... 시간이 너무 소요될 것 같아 패스...
class AreaSelectDropdownRow extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[
      ],
    );
  }
}

class GreyBorderDropdownButton extends StatefulWidget {
  final String hint;
  final List<String> dropdownMenuList;

  GreyBorderDropdownButton({this.hint, this.dropdownMenuList});

  @override
  _GreyBorderDropdownButtonState createState() =>
      _GreyBorderDropdownButtonState();
}

class _GreyBorderDropdownButtonState extends State<GreyBorderDropdownButton> {
  String selectedValue;

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: EdgeInsets.fromLTRB(10, 0, 10, 0),
        decoration: BoxDecoration(
          color: Colors.white,
          border: Border.all(color: Colors.grey),
          borderRadius: BorderRadius.circular(5),
        ),
        child: DropdownButton(
          value: selectedValue,
          hint: Text(widget.hint),
          elevation: 20,
          style: TextStyle(color: Colors.black),
          underline: SizedBox(),
          onChanged: (value) {
            setState(() {
              selectedValue = value;
            });
          },
          items: widget.dropdownMenuList.map<DropdownMenuItem<String>>((value) {
            return DropdownMenuItem<String>(
              child: Text(value),
              value: value,
            );
          }).toList(),
        ));
  }
}
