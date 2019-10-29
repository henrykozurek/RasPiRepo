import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
/**
Write a program that checks whether a sequence of HTML tags
is properly nested. For each opening tag, such as <p>, there
must be a closing tag </p>. A tag such as <p> may have other
tags inside, for example <p> <ul> <li> </li> </ul> <a> </a> </p>

The inner tags must be closed before the outer ones.
Your program should process a file containing tags.
For simplicity, assume that the tags are separated by
spaces, and that there is no text inside the tags.
 */
public class HTMLChecker
{
    public static void main(String[] args)
    {
        String filename = "TagSample1.html";

        Map<String, String> map = new TreeMap<>();

        boolean good = true;

        map.put("<p>", "</p>");
        map.put("<ul>", "</ul>");
        map.put("<a>", "</a>");
        map.put("<li>", "</li>");

        Stack<String> opens = new Stack<>();

        try (Scanner in = new Scanner(new File(filename)))
        {
            // Your code goes here

            while (in.hasNext() && good)
            {

                String s = in.next();

                System.out.print(s);

                if (s.charAt(1) == '/')
                {
                    if (opens.isEmpty())
                    {
                        good = false;
                    }
                    else
                    {
                        String shouldBe = opens.pop();
                        System.out.print(shouldBe);
                        if (! s.equals( map.get(shouldBe) ))
                        {
                            good = false;
                        }
                    }
                }
                else
                {
                    opens.push(s);
                }
                System.out.print("\n");
            }

            if (good) System.out.println("Looks good");
            else System.out.println("Uh oh, syntax error detected");

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + filename);
        }

    }
}
