import 'dart:math';
import 'dart:ui';

import 'package:dark_wall_app/card_data.dart';
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

  double scrollPercent = 0.0;

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
            child: new CardFlipper(
              cards: demoCards,
              //card flipper to get a function in a parameter onScroll
              onScroll: (double scrollPercent){
                setState(() {
                  this.scrollPercent = scrollPercent;
                });
              },
            ),
          ),

          //bottom bar
          new BottomBar(
            cardCount: demoCards.length,
            scrollPercent: scrollPercent,
          ),
        ],
      ),
    );
  }
}

class CardFlipper extends StatefulWidget { //needs to be a stateful widget because the card flipper needs to keep the record of the card which we flip

  //for cardFlipper to understand different cards
  final List<CardViewModel> cards;
  final Function(double scrollPercent) onScroll;

  CardFlipper({
    this.cards,
    this.onScroll,
  });

  @override
  _CardFlipperState createState() => _CardFlipperState();
}

class _CardFlipperState extends State<CardFlipper> with TickerProviderStateMixin {
  double scrollPercent = 0.0;
  Offset startDrag;
  double startDragPercentScroll;
  //these will come at the end
  double finishScrollStart;
  double finishScrollEnd;
  AnimationController finishScrollController;

  @override
  void initState(){
    super.initState();
    
    finishScrollController = new AnimationController(
      duration: const Duration(milliseconds: 150),
      vsync: this,
    )
    ..addListener((){ //will run every time the animation changes
      setState((){ //this is how we gonna generate scrollPercent every frame of animation
        scrollPercent =
            lerpDouble(finishScrollStart, finishScrollEnd, finishScrollController.value); //linear interpolation

        if(widget.onScroll != null){
          widget.onScroll(scrollPercent);
        }
      });
    });
  }

  @override
  void dispose(){
    finishScrollController.dispose();
    super.dispose();
  }



  //now we will make all the methods for the dragging
  void _onHorizontalDragStart(DragStartDetails details){
    startDrag = details.globalPosition;
    startDragPercentScroll = scrollPercent;
  }

  void _onHorizontalDragUpdate(DragUpdateDetails details){
    final currDrag = details.globalPosition;
    final dragDistance = currDrag.dx - startDrag.dx;
    final singleCardDragPercent = dragDistance / context.size.width; //says one for one scrolling

    setState((){
      scrollPercent = (startDragPercentScroll + (-singleCardDragPercent / widget.cards.length))
          .clamp(0.0, 1.0 - (1 / widget.cards.length));

      if(widget.onScroll != null){
        widget.onScroll(scrollPercent);
      }
    });
  }

  void _onHorizontalDragEnd(DragEndDetails details){
    final numCards = widget.cards.length;
    //to figure out where the animation is suppossed to be

    //we will start the animation from where ever the user ended their scrolling
    finishScrollStart = scrollPercent;

    //where want to animate to
    finishScrollEnd = (scrollPercent * numCards).round() / numCards;
    finishScrollController.forward(from: 0.0);

    setState(() {
      startDrag = null;
      startDragPercentScroll = null;
    });
  }

  //list of all the cards
  List<Widget> _buildCards(){
    final cardCount = widget.cards.length;

    int cardIndex = -1;
    return widget.cards.map((CardViewModel viewModel){
      cardIndex++;
      return _buildCard(viewModel, cardIndex, cardCount, scrollPercent);
    }).toList();
  }

  Matrix4 _buildCardProjection(double scrollPercent){
    // Pre-multiplied matrix of a projection matrix and a view matrix.
    //
    // Projection matrix is a simplified perspective matrix
    // http://web.iitd.ac.in/~hegde/cad/lecture/L9_persproj.pdf
    // in the form of
    // [[1.0, 0.0, 0.0, 0.0],
    //  [0.0, 1.0, 0.0, 0.0],
    //  [0.0, 0.0, 1.0, 0.0],
    //  [0.0, 0.0, -perspective, 1.0]]
    //
    // View matrix is a simplified camera view matrix.
    // Basically re-scales to keep object at original size at angle = 0 at
    // any radius in the form of
    // [[1.0, 0.0, 0.0, 0.0],
    //  [0.0, 1.0, 0.0, 0.0],
    //  [0.0, 0.0, 1.0, -radius],
    //  [0.0, 0.0, 0.0, 1.0]]
    final perspective = 0.002;
    final radius = 1.0;
    final angle = scrollPercent * pi / 8;
    final horizontalTranslation = 0.0;
    Matrix4 projection = new Matrix4.identity()
      ..setEntry(0, 0, 1 / radius)
      ..setEntry(1, 1, 1 / radius)
      ..setEntry(3, 2, -perspective)
      ..setEntry(2, 3, -radius)
      ..setEntry(3, 3, perspective * radius + 1.0);

    // Model matrix by first translating the object from the origin of the world
    // by radius in the z axis and then rotating against the world.
    final rotationPointMultiplier = angle > 0.0 ? angle / angle.abs() : 1.0;
    print('Angle: $angle');
    projection *= new Matrix4.translationValues(
        horizontalTranslation + (rotationPointMultiplier * 300.0), 0.0, 0.0) *
        new Matrix4.rotationY(angle) *
        new Matrix4.translationValues(0.0, 0.0, radius) *
        new Matrix4.translationValues(-rotationPointMultiplier * 300.0, 0.0, 0.0);

    return projection;
  }

  //to know which card we are in
  Widget _buildCard(CardViewModel viewModel, int cardIndex, int cardCount, double scrollPercent){
    final cardScrollPercent = scrollPercent/ (1/cardCount); //to make the cards separately spread out
    final parallax = scrollPercent - (cardIndex/cardCount); //to make the cards understand the background effect
    //the parallax value can be larger than 1 and smaller than 0

    return FractionalTranslation(
      translation: new Offset(cardIndex - cardScrollPercent, 0.0),
      child: new Padding( //padding so that the card don't touch each other when spread out
        padding: const EdgeInsets.all(16.0),
        child: new Transform(
          transform: _buildCardProjection(cardScrollPercent - cardIndex), //this will return a matrix
          child: new Card(
            viewModel: viewModel,
            parallaxPercent: parallax,
          ),
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

  final CardViewModel viewModel;
  final double parallaxPercent;

  Card({
    this.parallaxPercent = 0.0,
    this.viewModel,
  });

  @override
  Widget build(BuildContext context) {
    return Stack(
      // fit: StackFit.expand, --> use this when you have a image that is not fitting tbe screen
      children: <Widget>[
        //background
        new ClipRRect( //stands for Rounded Rectangle
          borderRadius: new BorderRadius.circular(10.0), //makes the corner rounded
          child: FractionalTranslation(
            translation: new Offset(parallaxPercent*2.0,0.0),
            child: OverflowBox(
              maxWidth: double.infinity,
              child: new Image.asset(
                viewModel.backdropAssetPath,
                fit: BoxFit.cover,
              ),
            ),
          ),
        ),

        //content //now this may will be good when we use a column
        new Column( //and we also know that the content is aligned in axis
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            new Padding(
              padding: const EdgeInsets.only(top: 30.0,left: 60.0,right: 20.0),
              child: new Text(
                viewModel.quotePart1.toUpperCase(),
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
                  //now we will have to interpolate the two strings
                  '${viewModel.quotePart2} ${viewModel.quotePart3}',
                  style: new TextStyle(
                    color: Colors.white,
                    fontSize: 50.0,
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
                    viewModel.byWhom,
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
                        viewModel.doPart1,
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
                        viewModel.doPart1,
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

class BottomBar extends StatelessWidget {

  final int cardCount;
  final double scrollPercent;

  //now their constructor
  BottomBar({
    this.cardCount,
    this.scrollPercent,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 15.0, bottom: 15.0),
      child: new Row(
        children: <Widget>[
          new Expanded(
              child: new Center( //this will center the icons in the available state
                child: new Icon(
                  Icons.arrow_back,
                  color: Colors.white,
                ),
              ),
          ),
          new Expanded(
              child: new Container(
                width: double.infinity,
                height: 5.0, //5 units tall
                child: new ScrollIndicator(
                  cardCount: cardCount,
                  scrollPercent: scrollPercent,
                ),
              ),
          ),
          new Expanded(
            child: new Center( //this will center the icons in the available state
              child: new Icon(
                Icons.arrow_forward,
                color: Colors.white,
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class ScrollIndicator extends StatelessWidget {

  final int cardCount;
  final double scrollPercent;

  ScrollIndicator({
    this.cardCount,
    this.scrollPercent,
  });


  @override
  Widget build(BuildContext context) {
    return new CustomPaint(
      painter: new ScrollIndicatorPainter(
        cardCount: cardCount,
        scrollPercent: scrollPercent,
      ),
      child: new Container(),
    );
  }
}

class ScrollIndicatorPainter extends CustomPainter{

  final int cardCount;
  final double scrollPercent;
  final Paint trackPaint;
  final Paint thumbPaint;

  //now lets declare the constructor
  ScrollIndicatorPainter({
    this.cardCount,
    this.scrollPercent,
  }): //now the below is used to make the bar and its painting styles
        trackPaint = new Paint()
        ..color = const Color(0xFF444444)
        ..style = PaintingStyle.fill,
        thumbPaint = new Paint()
        ..color = Colors.white
        ..style = PaintingStyle.fill;

  @override
  void paint(Canvas canvas, Size size) {
    //lets draw a track
    canvas.drawRRect(
        new RRect.fromRectAndCorners(
      new Rect.fromLTWH(
        0.0,
        0.0,
        size.width,
        size.height,
      ),
          topLeft: new Radius.circular(3.0),
          topRight: new Radius.circular(3.0),
          bottomLeft: new Radius.circular(3.0),
          bottomRight: new Radius.circular(3.0),
        ),
        trackPaint);

    //lets now draw a thumb
    final thumbWidth = size.width/cardCount;
    final thumbLeft = scrollPercent * size.width;
    canvas.drawRRect(
        new RRect.fromRectAndCorners(
          new Rect.fromLTWH(
            thumbLeft,
            0.0,
            thumbWidth,
            size.height,
          ),
          topLeft: new Radius.circular(3.0),
          topRight: new Radius.circular(3.0),
          bottomLeft: new Radius.circular(3.0),
          bottomRight: new Radius.circular(3.0),
        ),
        thumbPaint);

  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
  return true; //as it should always repaint and we don't need to optimize that
  }

}