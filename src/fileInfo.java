import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class

public class fileInfo 
{
    public void newFile(String name) // creates a text file and names it the given string
    {
        try 
        {
            File temp = new File(name+".txt");
            if (temp.createNewFile()) 
              System.out.println("File created: " + temp.getName());
            else
              System.out.println("File already exists.");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeFile(String msg, FileWriter myWriter) // adds a string to a text file using a FileWriter
    {
        try 
        {
            myWriter.write(msg);
            System.out.println("Successfully wrote to the file.");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
