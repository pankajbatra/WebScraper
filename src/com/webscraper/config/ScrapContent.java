package com.webscraper.config;

import com.webscraper.exception.ScraperException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 3:22:43 PM
 */
public class ScrapContent {
    private final Properties columnsMapping;
    private String contentFileName;
    private FileWriter writer;

    public ScrapContent(String paramsFileName) throws ScraperException {
        columnsMapping = new Properties();
        try {
            columnsMapping.load(new FileInputStream(paramsFileName));
        }
        catch (IOException e) {
            throw new ScraperException("Error reading request parameters.", e);
        }
    }

    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<String>();
        Enumeration propertyNames = columnsMapping.propertyNames();
        while (propertyNames.hasMoreElements()) {
            columnNames.add((String) propertyNames.nextElement());
        }
        return columnNames;
    }

    public String getPatternForColumn(String columnName)
    {
        return columnsMapping.getProperty(columnName);
    }

    public int getNumberOfColumns()
    {
        return columnsMapping.size();
    }

    public String getContentFileName() {
        return contentFileName;
    }

    public void setContentFileName(String contentFileName) {
        this.contentFileName = contentFileName;
    }

    public FileWriter getFileWriter() throws IOException {
        if(writer==null)
            writer = new FileWriter(getContentFileName());
        return writer;
    }
}
