*  Assignment 1 -  The Art of Clean Code
** Submission by
Nikhil Jakhar
Roll No. 20171186
** Course
Software Engineering - Spring 2019

** Disclaimer

This code has been orginally forked from the Args program described in: http://butunclebob.com/ArticleS.UncleBob.CleanCodeArgs. I have further tried
to implement a clean coding practice on this source code written in Java.

* Usage 
To use the code please implement the main class in the following way.

#+BEGIN_SRC java
public class ArgsMain {

  public static void main(String[] args) {
    try {
      Args arg = new Args("l,p#,d*", args);

      boolean logging = arg.getBoolean('l');

      int port = arg.getInt('p');

      String directory = arg.getString('d');

      executeApplication(logging, port, directory);

    } catch (ArgsException e) {
      System.out.printf("Argument error: %s\n", e.errorMessage());
    }
  }

  private static void executeApplication(boolean logging, int port, String directory) {

    System.out.printf("logging is %s, port:%d, directory:%s\n",logging, port, directory);

  }
}
#+END_SRC 

For the ease of coding, I have used Eclipse IDE as the development environment.

* Schema:
 - char    - Boolean arg.
 - char*   - String arg.
 - char#   - Integer arg.
 - char##  - double arg.
 - char[*] - one element of a string array.

Example schema: (f,s*,n#,a##,p[*])
Coresponding command line: "-f -s Bob -n 1 -a 3.2 -p e1 -p e2 -p e3

* Clean Code Practices

The source code makes use of the following clean coding prcatices:-

** Clean Code Practices in Source Code

+ The package names are named to convey the semantic categorisation for the classes inside the package.
+ Classes addressing different aspects have been seperated by packages( The classes defining exceptions,marshallers and arguments
 are kept in a seperate packages), to enable modularity and seperation of concerns.

+ The imports inside the classes have been seperated into groups in the order.
  - java libraries import
  - java libraries static import
  - local libraries
  - local libraries static imports

+ The class interfaces names have been named to convey the semantic meaning of the operations / functions defined in the class.
+ The interface names start with an /"I"/ to denote that the file contains the definition of an interface.
+ Added validation functions in place of if conditions.
+ Removed redundant code.
+ Changed variable names to convey their use.
+ Structured if/else conditions to make them more readable.
+ Added unit test cases to test methods of different classes.


![Directory Structure](https://github.com/nikhiljakhar/Assignment1-SoftwareEng/blob/master/Assignment-1/83545022_650622812348044_5480953340047130624_n.png)
