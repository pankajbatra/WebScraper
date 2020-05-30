package com.webscraper.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 6:43:11 PM
 */
public class XmlUtils {

    public static String getTextValueForHtmlNode(Node node)
    {
        if (node == null)
            return "";

        StringBuffer textValue = new StringBuffer("");
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                NodeList kids = node.getChildNodes();
                if (kids != null) {
                    for (int i = 0; i < kids.getLength(); i++) {
                        textValue.append(getTextValueForHtmlNode(kids.item(i)));
                    }
                }
                break;
            case Node.TEXT_NODE:
                textValue.append(node.getNodeValue());
                break;
            case Node.DOCUMENT_NODE:
                textValue.append(getTextValueForHtmlNode(((Document) node).getDocumentElement()));
                break;
        }
        return textValue.toString().replaceAll("-","");//.trim();
    }

    static boolean isTextNode(Node n) {
        if (n == null)
            return false;
        short nodeType = n.getNodeType();
        return nodeType == Node.CDATA_SECTION_NODE || nodeType == Node.TEXT_NODE;
    }
}
