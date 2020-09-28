// Search a maze for a path from mouse to cheese WITHOUT recursion.
// CONSTRAINT: You may not use ArrayLists to implement this. Use the
// AStack class instead.  You may import java.util.List to match the
// expected return types.
/// I Only use loops , 2 while and 1 for loop for the main body. Basically, I have a while loop that will continue until the path leads to the 
/// cheese, it has a for loop right after just like the recursive case to call all 4 directions using while loops to go in one Direction utill
/// it hits a dead end. if t does it skips all for directions and one by one removes each bad Direction from my Hope AStack. There are no helper methods
import java.util.*;
public class IterativeMazeSearch extends MazeSearch{
  private AStack<Direction> hope = new AStack<Direction>();
  // Constructor to initialize any state required to search mazes
  public IterativeMazeSearch(){}
  
  // Search for cheese in the given maze. Return a path of Directions
  // from mouse to cheese.  If no such path exists, return
  // null. CONSTRAINT: You may not use recursive calls or recursive
  // helper methods.
  @Override
  public List<Direction> searchForCheese(Maze maze){
    /// Starting Coord for the mouse
    Coord r = maze.getMouseLocation();
   /// count is used to see how many directions are not valid, if it equals 4 pop out the top coord and minues the coord "r" from the last Diretion
    int count = 0; 
    boolean hold = false;
    /// count one is used just like count but its for maze not finding any path so it can stop looping
    int count1 = 0;
    try{
      /// Main top while loop to check the base case path is false
      while( super.isPathToCheese(maze,(hope.toList()))== false){
        /// For loop to run through all the Directions by using a while loop that runs all the way in one Direction
        for(int i = 0; i < Direction.directions.length; i++){
          /// while loop that runs in one Direction untill it hits a wall, out of boonds, the coord is flagged or isPathToCheese = true,
          /// these are all the base cases that get checked everytime a Direction is looked at
          while(maze.inBounds(Coord.add(Direction.directions[i].getChange(), r))== true && maze.isWall(Coord.add(Direction.directions[i].getChange(), r)) == false && maze.isFlagged(Coord.add(Direction.directions[i].getChange(), r)) == false && super.isPathToCheese(maze,(hope.toList())) == false){
            /// "r" is he current coord and it gets added onto if the direction is valid
            r = Coord.add(Direction.directions[i].getChange(), r);
            /// add the valid Directiont to this classes AStack "hope"
            hope.push(Direction.directions[i]);
            /// set the current coordinates as flagged
            maze.setFlag(r);
          }
        }
        /// corner case to make sure the current path is not to cheese
        if( super.isPathToCheese(maze,(hope.toList()))== true){
          return hope.toList();
        }
        /// this for loop checks all 4 directions and makes sure path is not to cheese to stop an infinite loop
        for( int i = 0; i < Direction.directions.length; i++){
          if( maze.isWall(Coord.add(Direction.directions[i].getChange(), r)) == true || maze.isFlagged(Coord.add(Direction.directions[i].getChange(), r)) == true || maze.inBounds(Coord.add(Direction.directions[i].getChange(), r))== false){
            count = count + 1;
          }
        }
        /// basic check to see if it the current coord need to be moved back by its direction, note as I said beofore with count
        /// = 4
        if(count == 4){    
          r = Coord.subtract(r, hope.pop().getChange());
        }
        count = 0;
        /// stops and infinite loop
        if( hope.size() == 0){
          return null;
        }
        /// this makes sure path is not to cheese to stop an infinite loop
        if(count == 4 && super.isPathToCheese(maze,(hope.toList()))== false &&  hope.size() == 0){return null;}
        count = 0;
      
      }

      
      return hope.toList();
    }catch(Exception e){}
      /// stops and infinite loop
    if( hope.size() == 0){
      return null;
    }
    return hope.toList();
  }
}
