// Represent a 2D maze with a single mouse and multipel cheese
// locations. Positions on the maze can be flagged or unflagged to
// help keep track of search paths.
/// I made a class inside Maze called "thing" it holds a String and a boolean that is used for flagged status
/// my 2d Array "maze2d" holds these objects just like the gems in the last project
import java.util.*;
import java.io.*;
public class Maze {
  /// Basic List to keep track of all cheese location
  private List<Coord> cheeseloc = new ArrayList<Coord>();
  /// My 2d array that holds the objects that represent all the Strings from the scanner 
  private thing [][] maze2d;
  /// "row" and "col" are for size and locations of my "2darray
  private int row;
  private int col;
  
  /// Basic mouse Coord to be able to refere to this mazes mouse location through the "getMouseLocation" method 
  private Coord mouse = null;
  
  /// This is my "thing" calss as mentioned above, it acts as the object for my 2D array so I can call its string and boolean
  /// for flagged status, making it easy to reference As I made the class private I did not see the need to make its variables private
  private class thing{
    /// "rep" is my String representaion so if it was a wall "rep" would equal "|", "flag" is my boolean checker
    public String rep;
    public boolean flag = false;
    /// Basic constructor 
    public thing(String rep){
      this.rep = rep;
    }
  }
  
  
  
  
  
  
  
//   Construct a Maze from the contents of a file.  On construction,
//   all flags are cleared.  The file format should be similar to the
//   following.
//   
//   ||||||||
//   |     M|
//   | | ||||
//   | |    |
//   | ||||||
//   |      |
//   |||||| |
//   | ||   |
//   |C|  |C|
//   ||||||||
  public Maze(File f) throws Exception{
    /// "temp" is the string that will b used to hold the scanner tokens
    String temp = "";
    try{
      /// Two scanners , "frun" is the first run throught to grab col and row counts,
      /// "srun" is the second run which fills my "maze2d" array
      Scanner frun = new Scanner(f);
      Scanner srun = new Scanner(f);
      
      /// basic while loop to count rows
      while(frun.hasNextLine()){
        temp = frun.nextLine();
        this.row = row+1;
      }
      this.col = temp.length();
      /// make a new maze with the current rows and col demensions
      maze2d = new thing[this.row][this.col];
      /// for loop to fill the new "maze2d" with "thing" objects and fill thier strings
      for(int i = 0; i < row; i++){
        if(srun.hasNextLine()){
          temp = srun.nextLine();
          for(int ii = 0; ii < col; ii++){
            if(temp.charAt(ii) == ' '){
              maze2d[i][ii] = new thing(" ");
            }else if(temp.charAt(ii) == '|'){
              maze2d[i][ii] = new thing("|");
            }else if(temp.charAt(ii) == 'M'){
              mouse = new Coord(i,ii);
              maze2d[i][ii] = new thing("M");
            }else if(temp.charAt(ii) == 'C'){
              maze2d[i][ii] = new thing("C");
              cheeseloc.add(new Coord(i,ii));
            }
          }
        }
      }
      srun.close();
      frun.close();
    }catch(Exception e){};
    
  }
  // Construct a maze form the given string. The format of the string
  // identical to the format of the file above.
  public Maze(String s) throws Exception{
    String temp = "";
    try{
      /// Two scanners , "frun" is the first run throught to grab col and row counts,
      /// "srun" is the second run which fills my "maze2d" array
      Scanner frun = new Scanner(s);
      Scanner srun = new Scanner(s);
      /// basic while loop to count rows
      while(frun.hasNextLine()){
        temp = frun.nextLine();
        this.row = row+1;
      }
      this.col = temp.length();
      /// make a new maze with the current rows and col demensions
      maze2d = new thing[this.row][this.col];
      /// for loop to fill the new "maze2d" with "thing" objects and fill thier strings
      for(int i = 0; i < row; i++){
        if(srun.hasNextLine()){
          temp = srun.nextLine();
          for(int ii = 0; ii < col; ii++){
            if(temp.charAt(ii) == ' '){
              maze2d[i][ii] = new thing(" ");
            }else if(temp.charAt(ii) == '|'){
              maze2d[i][ii] = new thing("|");
            }else if(temp.charAt(ii) == 'M'){
              maze2d[i][ii] = new thing("M");
              mouse = new Coord(i,ii);
            }else if(temp.charAt(ii) == 'C'){
              maze2d[i][ii] = new thing("C");
              cheeseloc.add(new Coord(i,ii));
            }
          }
        }
      }
      srun.close();
      frun.close();
    }catch(Exception e){};
    
  }
  
//  // Return how many rows are in the maze.
  public int getRows(){
    return this.row;
  }
//  // Return how many columns are in the maze.
  public int getCols(){
    return this.col;
  }
  
  // Return the initial location of the mouse in the maze as a
  // coordinate (row,col).  Assume there is exactly one mouse. Return
  // null if no mouse is present.
  public Coord getMouseLocation(){
    return this.mouse;
  }
  
  // Return a list of coordinates of all the chesse locations in the
  // maze. If no cheese is present, return an empty list.
  public List<Coord> getCheeseLocations(){
    return this.cheeseloc;
  }
  
  // Return true if the given coordinate is in bounds for the maze and
  // false otherwise.
  public boolean inBounds(Coord c){
    if(c.row >= this.row || c.row < 0 || c.col >= this.col || c.col < 0){
      return false;
    }
    return true;
  }
  
  // Return true if the given coordinate is open space for the mouse
  // and false otherwise. The status of the flag (set or clear) at the
  // given location should not affect whether true/false is returned.
  public boolean isSpace(Coord c){
    if(inBounds(c)){
      if(maze2d[c.row][c.col].rep.equals(" ") == true){
        return true;
      }
    }
    return false;
  }
  
  // Return true if the given location refers to a wall and false
  // otherwise.
  public boolean isWall(Coord c){
    if(inBounds(c)){
      if(maze2d[c.row][c.col].rep.equals("|") == true){
        return true;
      }
    }
    return false;
  }
  
  // Return true if the given location refers to the starting location
  // of the mouse and false otherwise.
  public boolean isMouse(Coord c){
    if(inBounds(c)){
      if(maze2d[c.row][c.col].rep.equals("M") == true){
        return true;
      }
    }
    return false;
  }
  
  // Return true if the given location refers to cheese and false
  // otherwise.
  public boolean isCheese(Coord c){
    if(inBounds(c)){
      if(maze2d[c.row][c.col].rep.equals("C") == true){
        return true;
      }
    }
    return false;
  }
  
  // Return true if the given location has its flag set and false
  // otherwise.
  public boolean isFlagged(Coord c){
    if(inBounds(c)){
      if(maze2d[c.row][c.col].flag == true){
        return true;
      }
    }
    return false;
  }
  
  // Set the flag at the given location to true.  
  public void setFlag(Coord c){
    if(inBounds(c) == true){
      maze2d[c.row][c.col].flag = true;
    }
  }
  
  // Set the flag at the given location to false.  
  public void clearFlag(Coord c){
    if(inBounds(c)== true){
      maze2d[c.row][c.col].flag = false;
    }
  }
  
  // Clear all flags in the maze
  public void clearFlags(){
    for(int i = 0; i < row; i++){
      for(int ii = 0; ii < col; ii++){
        maze2d[i][ii].flag = false;
      }
    }
  }
  // Starting from the parameter start, flag all positions along the
  // path given by the directions list.
  public void flagPath(List<Direction> path, Coord start) {
    /// makes the start location flagged
    maze2d[start.row][start.col].flag = true;
    /// loop for to go through and mark all path coords by adding it to the current "start" coord
    for(int i = 0; i < path.size(); i++){
      start = Coord.add(path.get(i).getChange(), start);
      maze2d[start.row][start.col].flag = true;
    }
  }
  
//  // Create a display string for the maze. This is identical to the
//  // input format of the maze except that any open spaces which are
//  // flagged should display as periods (.) rather than spaces ( ). For
//  // example, the following maze has some open spaces in a path
//  // flagged which guide the mouse to the cheese.
//  // 
//  // ||||||||
//  // |.....M|
//  // |.| ||||
//  // |.|    |
//  // |.||||||
//  // |......|
//  // ||||||.|
//  // | ||  .|
//  // |C|  |C|
//  // ||||||||
  public String toString(){
    /// "start" is the whole string that gets added onto by "temp"
    String start = "";
    String temp = "";
    /// Double for loops to build the string one character at a time
    for(int i = 0; i < row; i++){
      if (start.length() != 0){
        start=start + "\n";
      }
      for(int ii = 0; ii < col; ii++){
        if( maze2d[i][ii].flag == true && maze2d[i][ii].rep.equals(" ")){
          temp = temp + ".";
          continue;                                                                  
        }
        temp  = temp  + maze2d[i][ii].rep ;
      }
      start = start +temp;
      temp = "";
    }
    return start+"\n";
  }
}