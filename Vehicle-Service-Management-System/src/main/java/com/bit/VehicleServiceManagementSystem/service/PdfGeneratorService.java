package com.bit.VehicleServiceManagementSystem.service;

import com.bit.VehicleServiceManagementSystem.Repository.CustomerRepo;
import com.bit.VehicleServiceManagementSystem.entity.Customer;
import com.bit.VehicleServiceManagementSystem.entity.ServiceRecord;
import com.bit.VehicleServiceManagementSystem.entity.Vehicle;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PdfGeneratorService {

    @Autowired
    private CustomerRepo customerRepository;

    public void export(HttpServletResponse response, Long customerId) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            HeaderFooter footer = new HeaderFooter(true, new Phrase("Page"));
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setBorderWidthBottom(0);
            document.setFooter(footer);

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(24);

            Paragraph title = new Paragraph("Customer Information", fontTitle);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20);

            // Table header
            table.addCell(createCell("Date", Font.BOLD, Element.ALIGN_CENTER));
            table.addCell(createCell("Customer Name", Font.BOLD, Element.ALIGN_CENTER));
            table.addCell(createCell("Contact", Font.BOLD, Element.ALIGN_CENTER));
            table.addCell(createCell("Vehicle Type", Font.BOLD, Element.ALIGN_CENTER));
            table.addCell(createCell("Registration Number", Font.BOLD, Element.ALIGN_CENTER));
            table.addCell(createCell("Service Amount", Font.BOLD, Element.ALIGN_CENTER));
            table.addCell(createCell("Oil Change", Font.BOLD, Element.ALIGN_CENTER));
            table.addCell(createCell("Washing", Font.BOLD, Element.ALIGN_CENTER));
            table.addCell(createCell("Other Amenities", Font.BOLD, Element.ALIGN_CENTER));
           // table.addCell(createCell("Payment", Font.BOLD, Element.ALIGN_CENTER));

            // Table data
            for (Vehicle vehicle : customer.getVehicles()) {
                for (ServiceRecord serviceRecord : vehicle.getServiceRecords()) {
                    table.addCell(createCell(serviceRecord.getDate().toString(), Font.NORMAL, Element.ALIGN_LEFT));
                    table.addCell(createCell(customer.getCustomerName(), Font.NORMAL, Element.ALIGN_LEFT));
                    table.addCell(createCell(customer.getCustomerContact(), Font.NORMAL, Element.ALIGN_LEFT));
                    table.addCell(createCell(vehicle.getVehicleType(), Font.NORMAL, Element.ALIGN_LEFT));
                    table.addCell(createCell(vehicle.getVehicleRegNo(), Font.NORMAL, Element.ALIGN_LEFT));
                    table.addCell(createCell(String.valueOf(serviceRecord.getServiceAmount()), Font.NORMAL, Element.ALIGN_LEFT));
                    table.addCell(createCell(serviceRecord.isOilChange(), Font.NORMAL, Element.ALIGN_LEFT));
                    table.addCell(createCell(serviceRecord.isWashing(), Font.NORMAL, Element.ALIGN_LEFT));
                    table.addCell(createCell(serviceRecord.getOtherAmenities(), Font.NORMAL, Element.ALIGN_LEFT));
                    // Uncomment and handle the payment status if necessary
                    // table.addCell(createCell(serviceRecord.isPayment() ? "Paid" : "Unpaid", Font.NORMAL, Element.ALIGN_LEFT));
                }
            }

            document.add(table);
            document.close();
        } else {
            throw new Exception("Customer not found with ID: " + customerId);
        }
    }

    private PdfPCell createCell(String text, int style, int alignment) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        font.setStyle(style);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(alignment);
        if (style == Font.BOLD) {
            cell.setFixedHeight(40f);
        }
        return cell;
    }
}
