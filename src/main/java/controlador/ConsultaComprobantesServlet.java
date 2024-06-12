package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.xml.soap.SOAPMessage;
import util.SOAPClient;

@WebServlet("/consultaComprobante")
public class ConsultaComprobantesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String claveAcceso = request.getParameter("claveAcceso");
        if (claveAcceso == null || claveAcceso.isEmpty()) {
            response.getWriter().println("Por favor, proporcione una clave de acceso válida.");
            return;
        }

        String endpointURL = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline";
        try {
            SOAPMessage soapResponse = SOAPClient.callSOAPWebService(endpointURL, claveAcceso);
            String result = SOAPClient.processSOAPResponse(soapResponse);
            response.setContentType("text/xml");
            response.getWriter().println(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}
