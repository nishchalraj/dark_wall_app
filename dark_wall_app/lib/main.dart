import "package:flutter/material.dart";
import 'package:flutter/services.dart';

void main() {

  SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle.light);
  runApp(new MyApp());

}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    return new MaterialApp(
      title: 'Incentive  Carousel',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget{
  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage>{
  @override
  Widget build(BuildContext context) {

    return new Scaffold(
      backgroundColor: Colors.black,  //to set the background color as white
      body: new Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          //room for status bar
          new Container(
            width: double.infinity,
            height: 20.0,
          ),

          //cards we need
          new Expanded(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: new CardFlipper(), //initiate the card widget
            ),
          ),


          //bottom bar
          new Container(
            width: double.infinity,
            height: 50.0,
            color: Colors.grey,
          ),
        ],
      ),
    );
  }
}

class CardFlipper extends StatefulWidget { //needs to be a stateful widget because the card flipper needs to keep the record of the card which we flip
  @override
  _CardFlipperState createState() => _CardFlipperState();
}

class _CardFlipperState extends State<CardFlipper> {
  double scrollPercent = 0.0;
  Offset startDrag;
  double startDragPercentScroll;
  //these will come at the end
  double finishScrollStart;
  double finishScrollEnd;
  AnimationController firstScrollController;

  //now we will make all the methods for the dragging
  void _onHorizontalDragStart(DragStartDetails details){
    startDrag = details.globalPosition;
    startDragPercentScroll = scrollPercent;
  }

  void _onHorizontalDragUpdate(DragUpdateDetails details){
    final currDrag = details.globalPosition;
    final dragDistance = currDrag.dx - startDrag.dx;
    final singleCardDragPercent = dragDistance / context.size.width; //says one for one scrolling

    final numCards = 5;
    setState((){
      scrollPercent = (startDragPercentScroll + (-singleCardDragPercent / numCards)).clamp(0.0, 1.0 - (1 / numCards));
    });
  }

  void _onHorizontalDragEnd(DragEndDetails details){
    //TODO: finish

    setState(() {
      startDrag = null;
      startDragPercentScroll = null;
    });
  }

  //list of all the cards
  List<Widget> _buildCards(){
    return [
      _buildCard(0,5,scrollPercent),
      _buildCard(1,5,scrollPercent),
      _buildCard(2,5,scrollPercent),
      _buildCard(3,5,scrollPercent),
      _buildCard(4,5,scrollPercent),
    ];
  }

  //to know which card we are in
  Widget _buildCard(int cardIndex, int cardCount, double scrollPercent){
    final cardScrollPercent = scrollPercent/ (1/cardCount); //to make the cards separately spread out

    return FractionalTranslation(
      translation: new Offset(cardIndex - cardScrollPercent, 0.0),
      child: new Padding( //padding so that the card don't touch each other when spread out
        padding: const EdgeInsets.all(16.0),
        child: new Card(

        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onHorizontalDragStart: _onHorizontalDragStart,
      onHorizontalDragUpdate: _onHorizontalDragUpdate,
      onHorizontalDragEnd: _onHorizontalDragEnd,
      behavior: HitTestBehavior.translucent, //so that dragging outside the card makes the card move in the dragged direction
      child: Stack( //we are not sure that a row will help in this, so we will take a stack and manually keep record of the cards
        children: _buildCards( //this method will return the list of cards in stack

        ),
      ),
    );
  }
}


class Card extends StatelessWidget { //now we are free here to do whatever we want to do with the cards
  @override
  Widget build(BuildContext context) {
    return Stack(
      // fit: StackFit.expand, --> use this when you have a image that is not fitting tbe screen
      children: <Widget>[
        //background
        new ClipRRect( //stands for Rounded Rectangle
          borderRadius: new BorderRadius.circular(10.0), //makes the corner rounded
          child: new Image.asset(
            'assets/wall1.jpg',
            fit: BoxFit.cover,
          ),
        ),

        //content //now this may will be good when we use a column
        new Column( //and we also know that the content is aligned in axis
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            new Padding(
              padding: const EdgeInsets.only(top: 30.0,left: 60.0,right: 20.0),
              child: new Text(
                "Always Look Forward".toUpperCase(),
                style: new TextStyle(
                  color: Colors.white,
                  fontSize: 20.0,
                  // fontFamily: ,  --> use this if want custom fonts
                  fontWeight: FontWeight.bold,
                  letterSpacing: 2.0,
                ),
              ),
            ),
            new Expanded(child: new Container(),),
            new Row(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                new Text(
                  'IN LIFE',
                  style: new TextStyle(
                    color: Colors.white,
                    fontSize: 100.0,
                    letterSpacing: -5.0,
                  ),
                ),
                new Padding(
                  padding: const EdgeInsets.only(top: 10.0, left: 10.0),
                  child: new Text(
                    '*',
                    style: new TextStyle(
                      color: Colors.white,
                      fontSize: 20.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ],
            ),
            new Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                new Icon(
                  Icons.all_inclusive,
                  color: Colors.white,
                ),
                new Padding(
                  padding: const EdgeInsets.only(left: 10.0),
                  child: new Text(
                    'NR',
                    style: new TextStyle(
                      color: Colors.white,
                      fontSize: 30.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ],
            ),
            new Expanded(child: new Container(),),
            new Padding(
              padding: const EdgeInsets.only(top: 50.0, bottom: 50.0),
              child: new Container(
                decoration: new BoxDecoration(
                  borderRadius: new BorderRadius.circular(20.0),
                  border: new Border.all(
                    color: Colors.white,
                    width: 1.0,
                  ),
                  color: Colors.black.withOpacity(0.3),
                ),
                child: new Padding(
                  padding: const EdgeInsets.symmetric(vertical: 5.0, horizontal: 20.0),
                  child: new Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: <Widget>[
                      new Text(
                        'Live',
                        style: new TextStyle(
                          color: Colors.white,
                          fontSize: 16.0,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      new Padding(
                        padding: const EdgeInsets.only(left: 10.0, right: 10.0),
                        child: new Icon(
                          Icons.accessibility,
                          color: Colors.white,
                        ),
                      ),
                      new Text(
                        'Life',
                        style: new TextStyle(
                          color: Colors.white,
                          fontSize: 16.0,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ],//so by default, a row tries to be as wide as it can and column as tall as it can
                  ),
                ),
              ),
            ),
          ],
        ),
      ],
    );
  }
}
