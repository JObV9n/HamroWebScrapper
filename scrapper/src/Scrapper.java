import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Scrapper {
    public static void main(String[] args) {
//        System.out.println("My name is Sachin");
        String url = "https://www.sharesansar.com/today-share-price";
        try {
            Response response = Jsoup.connect(url).execute(); // object to get HTTP response
            final Document dom = Jsoup.connect(url).get();// object to get parsed HTML content of the webpage
            int status = response.statusCode();
            if (!(status == 200)) {
                System.out.println("Couldn't Connect to desired Website");
            } else {
                System.out.println("Connected to " + url);
            }

            for (Element element : dom.select("[id=headFixed]")) {//selected the id of the
                String comName = element.select("td:nth-child(2)").text();
                String comPrice = element.select("td:nth-child(7)").text();

                try {
                    comPrice = comPrice.replace(",", ".");
                    double finalPrice = Double.parseDouble(comPrice);
                } catch (NumberFormatException e) {
                    System.out.println("Error number format " + e.getMessage());
                }
                System.out.println(comName);
                    System.out.println(comPrice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
