package com.turismo.gestion_destinos.service;

import com.turismo.gestion_destinos.model.Usuario;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FacturaService {
    public void generarFactura(Usuario usuario) {
        String archivo = "factura_" + usuario.getId() + ".pdf";

        try (PDDocument documento = new PDDocument()) {
            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            try (PDPageContentStream contenido = new PDPageContentStream(documento, pagina)) {
                // Título de la factura
                contenido.beginText();
                contenido.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contenido.newLineAtOffset(50, 750);
                contenido.showText("Factura de Usuario");
                contenido.endText();

                // Información del usuario
                contenido.beginText();
                contenido.setFont(PDType1Font.HELVETICA, 12);
                contenido.newLineAtOffset(50, 700);
                contenido.showText("Nombre: " + usuario.getNombre());
                contenido.newLineAtOffset(0, -15);
                contenido.showText("Email: " + usuario.getEmail());
                contenido.endText();

                // Encabezado de destinos
                contenido.beginText();
                contenido.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contenido.newLineAtOffset(50, 650);
                contenido.showText("Destinos Asignados:");
                contenido.endText();

                // Lista de destinos
                float yPosition = 630;
                double total = 0;
                for (var destino : usuario.getDestinosAsignados()) {
                    contenido.beginText();
                    contenido.setFont(PDType1Font.HELVETICA, 12);
                    contenido.newLineAtOffset(50, yPosition);
                    contenido.showText(destino.getNombre() + " - $" + destino.getPrecio());
                    contenido.endText();

                    yPosition -= 15;
                    total += destino.getPrecio();
                }

                // Total
                contenido.beginText();
                contenido.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contenido.newLineAtOffset(50, yPosition - 20);
                contenido.showText("Total: $" + total);
                contenido.endText();
            }

            // Guardar el archivo PDF
            documento.save(archivo);
            System.out.println("Factura generada: " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
