package xmlws.roombooking.xmltools;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class RoomBookingDomParser implements RoomBookingParser {

    private RoomBooking roomBooking;

    public RoomBooking parse(InputStream inputStream) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            System.out.println(doc.getElementsByTagName("label").item(0).getTextContent());
            System.out.println(doc.getElementsByTagName("username").item(0).getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomBooking;
    }

}
