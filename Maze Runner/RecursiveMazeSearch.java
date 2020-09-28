// Search a maze for a path from mouse to cheese using recursion.
// CONSTRAINT: You may not use ArrayLists to implement this. Use the
// AStack class instead.  You may import java.util.List to match the
// expected return types.
/// "goodpath()" is my only helper method which is the actual recrusive call that gets called in "searchForCheese()"
/// Basically I call goodpath() and it uses its given maze from the passed in argument to get its starting mouse coord
/// on the next call of goodpath() I pass in the maze again and the new coord that has been added onto by using the Direction
/// getchange() and then Coord.add(). I use a for loop to call goodpath() 4 times for each direction, it saves alot of lines of code 
import java.util.*;
public class RecursiveMazeSearch extends MazeSearch{
  /// "hope" is the "Atasck" that will be holding all of the DIrections for the recursive call to "goodpath()"
  private AStack<Direction> hope = new AStack<Direction>();
  // Constructor to initialize any state required to search mazes
  public RecursiveMazeSearch(){}
  
  // Search for cheese in the given maze. Return a path of Directions
  // from mouse to cheese.  If no such path exists, return
  // null. Employ a recursive helper function to assist with the
  // search.
  @Override
  @SuppressWarnings("unchecked")
  public List<Direction> searchForCheese(Maze maze){
    ///  "mousey" is to get the starting location for the recursive call goodpath 
    Coord mousey = maze.getMouseLocation();
    /// Basic corner case to make sure there is a mouse
    if(maze.getMouseLocation() == null){
      return null;
    }
    /// Basic corner case to make sure there is cheeze
    if( maze.getCheeseLocations().size() == 0){
      return null;
    }
    /// the first call to my recursive method "goodpath()"
    goodpath(maze,maze.getMouseLocation());
    /// Basic corner case to make sure there is hope.... lol I mean after "goodpath()" finishes makes 
    ///sure that there is a path
    if(hope.size() == 0){
      return null;
    }
    return this.hope.toList();
  }
  
  
  
  
  @SuppressWarnings("unchecked")
  /// this is the Helper method that Recursivecall uses to search through the maze
  public void goodpath(Maze maze,Coord r){   
    try{
      /// make sure the given path is not to cheese, it it is return all the way out
      if(super.isPathToCheese(maze,(hope.toList()))){
        return;
      }
      /// for loopt to call each direction recursivly from "Diretions.revDirections"
      for(int i = 0; i < Direction.directions.length; i++){
        /// check to see if the current new direction is in bounds this is a base case
        if(maze.inBounds(Coord.add(Direction.revDirections[i].getChange(), r))== true){
          /// now check to make sure its not a wall or flagged these are a base cases
          if( maze.isWall(Coord.add(Direction.revDirections[i].getChange(), r)) == false && maze.isFlagged(Coord.add(Direction.revDirections[i].getChange(), r)) == false){
            /// push the current Direction into the "hope" Stack
            hope.push(Direction.revDirections[i]);
              /// make sure the given path is not to cheese, it it is return all the way out
            if(super.isPathToCheese(maze,(hope.toList()))){
              return;
            }
            /// Set flagged at the new corrdinate
            maze.setFlag(Coord.add(Direction.revDirections[i].getChange(), r));
            /// call "goodpath" again to restart the process
            goodpath(maze,Coord.add(Direction.revDirections[i].getChange(), r));
             /// make sure the given path is not to cheese, it it is return all the way out
            if(super.isPathToCheese(maze,(hope.toList()))){
              return;
            }
            /// clear the flag at the coordinate 
            maze.clearFlag(Coord.add(Direction.revDirections[i].getChange(), r));
            /// if it reaches this far start poping out the wrong directions
            hope.pop();
          }
        }
      }
      
      
      
      return ;
      
      
    }catch(Exception e){}
  }
}
