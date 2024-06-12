package util;

import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class SOAPClient {

    public static SOAPMessage createSOAPRequest(String claveAcceso) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("ec", "http://ec.gob.sri.ws.autorizacion");

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("autorizacionComprobante", "ec");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("claveAccesoComprobante");
        soapBodyElem1.addTextNode(claveAcceso);

        soapMessage.saveChanges();
        return soapMessage;
    }

    public static SOAPMessage callSOAPWebService(String soapEndpointUrl, String claveAcceso) throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        SOAPMessage soapRequest = createSOAPRequest(claveAcceso);
        return soapConnection.call(soapRequest, soapEndpointUrl);
    }

    public static String processSOAPResponse(SOAPMessage soapResponse) throws Exception {
        soapResponse.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapResponse.writeTo(out);
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }
}
