package com.younicos.frequencytracker.controller;

import com.younicos.frequencytracker.consumer.Collector;
import com.younicos.frequencytracker.event.EventUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;

@Controller
@RequestMapping(value = "/export")
public class ExportController {

    @RequestMapping(value = "/csv")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "events.csv";

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        response.setContentType("text/csv");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = { "type", "state", "timestamp" };

        csvWriter.writeHeader(header);

        for (EventUpdate update : Collector.getUpdates()) {
            csvWriter.write(update, header);
        }

        csvWriter.close();
    }
}

