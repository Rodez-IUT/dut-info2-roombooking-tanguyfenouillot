package xmlws.roombooking.xmltools;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlws.roombooking.xmltools.samples.RoomBookingBasicSaxParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class RoomBookingSaxParser implements RoomBookingParser {

    private RoomBooking roomBooking = new RoomBooking();
    private String localNameTemp;

    public RoomBooking parse(InputStream inputStream) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(inputStream, new RoomBookingBasicHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.roomBooking;
    }

    private class RoomBookingBasicHandler extends DefaultHandler {
        public void startElement(String namespaceURI,
                                 String localName,
                                 String qName,
                                 Attributes atts) {
            localNameTemp = localName;
        }
        public void characters(char ch[], int start, int length)
                throws SAXException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            String data = new String(ch, start, length);

            if (data.charAt(0) !=  '\n') {

                if (localNameTemp.equals("label")) {
                    roomBooking.setRoomLabel(data);
                }

                if (localNameTemp.equals("username")) {
                    roomBooking.setUsername(data);
                }

                if (localNameTemp.equals("startDate")) {
                    try {
                        roomBooking.setStartDate(sdf.parse(data));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (localNameTemp.equals("endDate")) {
                    try {
                        roomBooking.setEndDate(sdf.parse(data));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

}
