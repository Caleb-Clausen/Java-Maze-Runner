// Search a maze for a path from mouse to cheese. The exact search
// method is left to child classes to implement.
import java.util.*;
public abstract class MazeSearch {

  // Constructor to initialize any state required to search mazes
  public  MazeSearch(){}

  // Return true if the path given leads the mouse in maze to cheese.
  // Return false otherwise (walking through walls, not ending on
  // cheese). Throw an exception if the path would lead the mouse out
  // of bounds in the maze.
  public boolean isPathToCheese(Maze maze, List<Direction> path) throws Exception{
    
    /// "need' is a coord for the starting location
      Coord need = maze.getMouseLocation();
    
    /// basic corner cases to make sure the  map has cheese
    if(maze.getCheeseLocations().size() == 0){
      return false;
    }
    /// boolean to make see if each spot in path except the last is a space
    boolean pcor = true;
 /// basic corner cases to make sure path is not empty
    if( path.size() == 0){
      return false;
    }
    /// for loop to check each path element and cross refernce it with wall, inbounds amd mouse.
    for(int i = 0; i < path.size()-1; i++){
      need =  Coord.add(path.get(i).getChange(), need);
      if(maze.isMouse(need) == true){
        continue;
      }
      if(maze.isWall(need) == true){
        return false;
      }
      if(maze.inBounds(need) == false){
        throw new Exception("Out of Bounds");
      }
    }
      need =  Coord.add(path.get(path.size()-1).getChange(), need);
      /// final loop to make sure that pcor is still true and cheese locations is the end element
      for(int ii = 0; ii < maze.getCheeseLocations().size(); ii++){
    if(pcor == true &&  need.equals(maze.getCheeseLocations().get(ii)) == true){
      return true;
    }
  }
       return false;
       }

  // Overriden by children: Search for cheese in the given maze. If no
  // path to any cheese exists, return null.
public abstract List<Direction> searchForCheese(Maze maze);

}