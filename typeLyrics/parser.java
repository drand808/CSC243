// This program prints Welcome to Java!

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Parser {
  String artist;
  
  public Parser(String pageURL) {
    Document doc = new Document("");
    try{
      doc = Jsoup.connect("https://www.azlyrics.com/lyrics/phoebebridgers/waitingroom").get();
    }
    catch (IOException e) {
      System.out.println("Something went wrong.");
    }
    // "<!-- Usage of azlyrics.com content by any third-party lyrics provider is prohibited by our licensing agreement. Sorry about that. -->";
    System.out.println(doc.html());
    //Element contentElement = doc.getElementById("<!-- Usage of azlyrics.com content by any third-party lyrics provider is prohibited by our licensing agreement. Sorry about that. -->");
    //Elements link = doc.select("br");
    //System.out.println(link);
    artist = pageURL;
  }
  
  public static void main(String[] args) {
    System.out.println("Welcome to Java!");
    
    Parser p = new Parser("ya boi");
    System.out.println(p.artist);
    
    System.out.println("Done");
  }
}