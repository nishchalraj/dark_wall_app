//hard coded siz cards based on the properties
final List<CardViewModel> demoCards = [
  new CardViewModel(
    backdropAssetPath:'assets/wall1.jpg',
    quotePart1:'ALWAYS MOVE FORWARD',
    quotePart2:'IN',
    quotePart3:'LIFE',
    byWhom:'NR',
    doPart1:'LIVE',
    doPart2:'LIFE',
  ),
  new CardViewModel(
    backdropAssetPath:'assets/wall2.jpg',
    quotePart1:'I DO\'T CARE WHAT',
    quotePart2:'OTHERS',
    quotePart3:'SAY',
    byWhom:'NR',
    doPart1:'LIVE',
    doPart2:'LIFE',
  ),
  new CardViewModel(
    backdropAssetPath:'assets/wall3.png',
    quotePart1:'I \'VE MY',
    quotePart2:'OWN',
    quotePart3:'BATTLE',
    byWhom:'NR',
    doPart1:'LIVE',
    doPart2:'LIFE',
  ),
  new CardViewModel(
    backdropAssetPath:'assets/wall4.png',
    quotePart1:'POWER IS ESSENTIAL',
    quotePart2:'FOR',
    quotePart3:'US',
    byWhom:'NR',
    doPart1:'LIVE',
    doPart2:'LIFE',
  ),
  new CardViewModel(
    backdropAssetPath:'assets/wall5.jpg',
    quotePart1:'KEEP EYES ON',
    quotePart2:'YOUR',
    quotePart3:'TARGET',
    byWhom:'NR',
    doPart1:'LIVE',
    doPart2:'LIFE',
  ),
  new CardViewModel(
    backdropAssetPath:'assets/wall6.png',
    quotePart1:'NEVER ARGUE',
    quotePart2:'WITH',
    quotePart3:'FOOLS',
    byWhom:'NR',
    doPart1:'LIVE',
    doPart2:'LIFE',
  ),
];

// data structure i.e. a bunch of properties
class CardViewModel{ //according to the properties used include the data type
  final String backdropAssetPath; //background image path
  final String quotePart1; //to be used as the title
  final String quotePart2;
  final String quotePart3;
  final String byWhom;
  final String doPart1;
  final String doPart2;

  CardViewModel({
  this.backdropAssetPath,
  this.quotePart1,
  this.quotePart2,
  this.quotePart3,
  this.byWhom,
  this.doPart1,
  this.doPart2,
  });
}