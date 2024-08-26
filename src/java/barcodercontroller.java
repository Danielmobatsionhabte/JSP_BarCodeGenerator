/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.lowagie.text.pdf.Barcode39;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.imageio.ImageIO;
import sun.net.www.content.image.png;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Dani-Boy
 */
public class barcodercontroller extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String strBarCode = request.getParameter("barcode");

byte[] pngImageData = null;
try {
Barcode39 code39ext = new Barcode39();
code39ext.setCode(strBarCode);
code39ext.setStartStopText(false);
code39ext.setExtended(true);
java.awt.Image rawImage = code39ext.createAwtImage(Color.BLACK, Color.WHITE);
BufferedImage outImage = new BufferedImage(rawImage.getWidth(null), rawImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
outImage.getGraphics().drawImage(rawImage, 0, 0, null);
ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
ImageIO.write(outImage, "png", bytesOut);
bytesOut.flush();
pngImageData = bytesOut.toByteArray();
            byte[] image = pngImageData;
            
} catch (Exception e) {
e.printStackTrace();
}

    Connection con =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/info","root","");
            String sql = "INSERT INTO barcode(bar) values (?)";
            PreparedStatement statement = con.prepareStatement(sql);
           java.sql.Blob blob =null;
           blob.setBytes(1, pngImageData);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(barcodercontroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(barcodercontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    if (pngImageData != null) {
    response.setContentLength(pngImageData.length);
    response.setContentType("image/png");
    OutputStream out = response.getOutputStream();
    out.write(pngImageData);
    out.flush();
    out.close();
}
}
}
