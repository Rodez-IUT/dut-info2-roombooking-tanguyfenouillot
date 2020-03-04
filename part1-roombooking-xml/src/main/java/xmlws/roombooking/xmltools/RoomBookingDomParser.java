package xmlws.roombooking.xmltools;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class RoomBookingDomParser implements RoomBookingParser {

    private RoomBooking roomBooking = new RoomBooking();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");


    public RoomBooking parse(InputStream inputStream) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            roomBooking.setRoomLabel(doc.getElementsByTagName("label").item(0).getTextContent());
            roomBooking.setUsername(doc.getElementsByTagName("username").item(0).getTextContent());
            roomBooking.setStartDate(sdf.parse(doc.getElementsByTagName("startDate").item(0).getTextContent()));
            roomBooking.setEndDate(sdf.parse(doc.getElementsByTagName("endDate").item(0).getTextContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomBooking;
    }

}
