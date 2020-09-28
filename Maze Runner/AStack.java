// Basic stack class backed by standard java arrays. Provides push(),
// pop(), top() methods along with list versions of the elements.
// When a push() would exceed the internal capacity of the internal
// array, the array is doubled in size.
// 
// CONSTRAINT: Unless otherwise specified in individual methods, this
// class must implemented without relying on any other classes aside
// from standard arrays and Object.

/// All of my one notes use three /// to denote I wrote them. This class acts as a Stack using just arrays to 
/// facitlite a List like use some Comments that were already self explained
import java.util.*;
public class AStack<T>{
  
  /// "arraystack" is used to hold all elements of T and will be coppied if it its size gets to big
  private T [] arraystack;
  /// "capacity" is how much this Stack can hold before needing to be exspanded
  private  int capacity;
  /// "size" is used to keep track of how many elements are in the current stack.
  private int size;
  
  // Create a stack with the default capacity 10. The stack expands
  // its internal array when the number of elements pushed() into it
  // would exceed the internal capacity.
  @SuppressWarnings("unchecked")
  public AStack(){
    this.arraystack = (T[]) new Object[10];
    this.capacity = 10;
  }
  // Create a stack with the indicated initial capacity.
  @SuppressWarnings("unchecked")
    public AStack(int n){
          this.arraystack = (T[]) new Object[n];
          this.capacity = n;
    }

  // Get the virtual size of the stack which is how many elements have
  // been pushed into it but not popped.
    public int size(){
      return this.size;
    }

  // Get the size of the internal array("arraystack") used by the stack to store
  // elemnts. This number indicates how many elements can be stored in
  // the stack before an expansion must occur.
    public int getCapacity(){
      return this.arraystack.length;
    }

  // Add a new element to the top of the stack. Expand the internal
    // array if needed.
    @SuppressWarnings("unchecked")
    public void push(T x){
      /// check to see a special corner case if capacity == 1 it will not multiply correctly so set capacity 
      if(this.size == this.getCapacity() && this.getCapacity() ==1){
        T [] temp = (T[]) new Object[2];
        /// copy all elements to temp to be able to return an array of the correct size 
        for(int i = 0; i < this.arraystack.length; i++){
          temp[i] =  this.arraystack[i];
        }
        this.arraystack = temp;
        arraystack[this.size] = x;
        this.size++;
        return;
      }
      /// Defualt case to make the capacity 2 times the lenth and then add the element T
      if(this.size == this.getCapacity()){
        T [] temp = (T[]) new Object[this.arraystack.length*2];
        /// copy all elements to temp to be able to return an array of the correct size 
        for(int i = 0; i < this.arraystack.length; i++){
          temp[i] =  this.arraystack[i];
        }
        /// make this classes array equal to the new array temp of double size
        this.arraystack = temp;
        arraystack[this.size] = x;
        this.size++;
        return;
      }
      /// add the element to the array at the end. 
      if(this.size == 0){
        arraystack[this.size] = x;
        this.size++;
      } else {arraystack[this.size] = x;
        this.size++;
      }
    }
        
        
//         Remove the top element of the stack and return it. If the stack
//   is emmpty, throw a RuntimeException with the message
//   "Stack empty"
    public T pop() throws RuntimeException {
      /// throw a Runtime Exception if the stack has no elements 
      if( size == 0){
        throw new RuntimeException("Stack empty");
      }
      /// grab the element and subtract 1 from size
      T temp = this.arraystack[this.size-1];
      this.arraystack[this.size-1] = null;
      size--;
    return  temp;
    }
  // Return the top element of the stack .If the stack is emmpty,
  // throw a RuntimeException with the message "Stack empty"
    public T top(){
    if( size == 0){
        throw new RuntimeException("Stack empty");
      }
      T temp = this.arraystack[this.size-1];
    return  temp;
    }
    

  // Pretty print the stack as a string.  The string should reflect
  // the size() of the stack and not include elements of the internal
  // array which are not defined.  While building the string
  // representation you may use the String, StringBuilder, and
  // StringBuffer classes.
    public String toString(){
      /// Corner case to see if the array is empty
      if( size == 0){
        return "[]";
      }
      
      /// Corner case to see if the array only has one element the prints it
       if( size == 1){
         String smalltemp = String.format("[%s]",this.arraystack[0].toString());
        return smalltemp;
      }
       String temp = "";
       /// Use a loop to go through the entire  stack and add the itmes to a String to return
      for(int i = 0; i < this.size-1; i++){
         temp = temp +this.arraystack[i].toString()+","+ " ";
      }
      temp = ("[" + temp+ this.arraystack[this.size-1].toString()).trim() + "]"; 
         return temp;
    }

  // Return a list of the elements in the stack. The bottom stack item
  // is at index 0 and the top stack item is at index size()-1. This
  // method does not change the stack. For this method only you may
  // use ArrayLists.
    public List<T> toList(){
      /// make a list, add all the elements to it and return it like the Astack.
      ArrayList<T> temp1 = new ArrayList<T>();
       for(int i = 0; i <size(); i++){
         if( arraystack[i] !=null ){
           temp1.add(arraystack[i]);
         }
         }
        List<T> temp = temp1;
    
      return temp;
    }

}