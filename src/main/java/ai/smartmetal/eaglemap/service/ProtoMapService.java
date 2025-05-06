package ai.smartmetal.eaglemap.service;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Service
public class ProtoMapService {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    public String generateRandomSvgBase64() {
        try {
            DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
            Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);
            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

            // Random background and circle
            svgGenerator.setPaint(randomColor());
            svgGenerator.fillRect(0, 0, WIDTH, HEIGHT);

            svgGenerator.setPaint(randomColor());
            svgGenerator.fillOval(0, 0, WIDTH, HEIGHT);

            // Write SVG to a StringWriter
            StringWriter writer = new StringWriter();
            svgGenerator.stream(writer, false);

            String svgContent = writer.toString();
            return "data:image/svg+xml;base64," +
                    Base64.getEncoder().encodeToString(svgContent.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate SVG", e);
        }
    }


    private Color randomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}
