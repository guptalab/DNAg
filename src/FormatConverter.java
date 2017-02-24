/** Used for generating DNAg Tool : as a part of Btech. Degree,2013.
 *The code has not been modified here,except the paths, and used as it is.
 *The code has been taken from google open source : code.google.com/p/cfgtocnf/source/browser/?r=2
 *We acknowledge @author : Bob Babilon for the code.*/



/*
 * FormatConverter.java
 *
 * Created on November 25, 2007, 2:26 PM
 *
 * @author Bob Babilon
 */

  
//package DNAg;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class FormatConverter 
{
        public FormatConverter()
        {
        }
        
        /* Converts given source xml document to the text format
         * that the Cyk class requires for processing
         */
        public static void XmlToCyk(String source, String output, String inputString, ArrayList<IgnoredRule> ignoredRules)
        {
                try
                {
                        // Creating a DOM Document
                        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                        domFactory.setNamespaceAware(true);
                        
                        DocumentBuilder builder = domFactory.newDocumentBuilder();
                        Document doc = builder.parse(source);

                        // Create a few XPath queries and a factory
                        XPathFactory factory = XPathFactory.newInstance();
                        XPath xpath = factory.newXPath();
                        
                        XPathExpression expr = xpath.compile("grammar/productions/production");
                        Object result = expr.evaluate(doc, XPathConstants.NODESET);
                        NodeList productions = (NodeList)result;
                        
                        String prodname;
                        String rule = "";
                        String temp;
                        ArrayList<String> listProds = new ArrayList<String>();
                        ArrayList<String> listRules = new ArrayList<String>();
                        for(int i = 0; i < productions.getLength(); i++)
                        {
                                prodname = productions.item(i).getAttributes().item(0).getNodeValue();
                                if(prodname.length() > 1)
                                {
                                        prodname = "[" + prodname + "]";
                                }
                                
                                NodeList childNodes = productions.item(i).getChildNodes();
                                for(int j = 0; j < childNodes.getLength(); j++)
                                {
                                        temp = childNodes.item(j).getTextContent().trim();
                                        if(temp.length() > 1)
                                        {
                                                temp = "[" + temp + "]";
                                        }
                                        rule += temp;
                                }
                                
                                // save the production
                                // check if the production is already added, if so append the child rules to the existing one
                                // if not added, add it
                                int index = listProds.indexOf(prodname);
                                if(index == -1)
                                {
                                        listProds.add(prodname);
                                        listRules.add(rule);
                                }
                                else
                                {
                                        listRules.set(index, listRules.get(index) + "|" + rule);
                                }
                                rule = "";
                        }
                        
                        FileWriter writer = new FileWriter(output);
                        writer.write("Productions:\n");
                        for(int i = 0; i < ignoredRules.size(); i++)
                        {
                            if(ignoredRules.get(i).Ignored == true)
                                writer.write(ignoredRules.get(i).ProductionName + "->" + ignoredRules.get(i).Terminal + "\n");
                        }
                        for(int i = 0; i < listProds.size(); i++)
                        {
                                writer.write(listProds.get(i) + "->" + listRules.get(i) + "\n");
                        }
                        
                        // at last, write the input string
                        writer.write("String:\n" + inputString);
                        writer.close();
                } catch (IOException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }catch (SAXException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }catch (XPathExpressionException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }catch (ParserConfigurationException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }
        }
        
        /* Converts the text format grammar file to XML
         */
        public static ArrayList<IgnoredRule> CykToXml(String source, String output)
        { 
                try
                {
                        // read all rules for the input file
                        Rule r = new Rule(source);
                        r.readInput(false);
                        
                        FileWriter writer = new FileWriter(output);

                        ArrayList<IgnoredRule> ignoredRules = new ArrayList<IgnoredRule>();

                        // scan for productions that produce a single terminal and add them
                        // to an "ignore list". mark same productions as ignored.
                        for(int i = 0; i < r.rules.size(); i++)
                        {
                                ArrayList<String> rule = r.rules.get(i);
                                if(rule.size() == 2)
                                {
                                        IgnoredRule t = new IgnoredRule();

                                        char temp = rule.get(1).charAt(0);
                                        if( rule.get(1).length() == 1 && temp >= 'a' && temp <= 'z')
                                        {
                                                // do not write this, it is a terminal. we'll save for later.
                                                t.ProductionName = rule.get(0);
                                                t.Terminal = rule.get(1);
                                                t.Ignored = false;
                                                System.out.println("Temporarily ignoring production: " + t.ProductionName + " terminal: " + t.Terminal);
                                        }
                                        else
                                        {
                                        		
                                                System.out.println("Invalid grammar, found a unit production. (ignoring)");
                                        }

                                        // since the second list is length 1, it must be a terminal.
                                        // we assume the input grammar is free of unit productions.
                                        for(int k = 0; k < ignoredRules.size(); k++)
                                        {
                                                // there may be more than one production that produces a
                                                // terminal. so we need to set all productions that
                                                // match the current production in this ignore array as
                                                // 'ignored'
                                                if(t.ProductionName.compareTo(ignoredRules.get(k).ProductionName) == 0)
                                                {
                                                        System.out.println("A Found " + t.ProductionName + " is equal to existing ignored prod " + ignoredRules.get(k).ProductionName);
                                                        ignoredRules.get(k).Ignored = true;
                                                        t.Ignored = true;
                                                        break;
                                                }
                                        }
                                        ignoredRules.add(t);
                                }
                        }
                        
                        writer.write("<grammar>\n\t<productions>\n");
                        for(int i = 0; i < r.rules.size(); i++)
                        {
                                ArrayList<String> rule = r.rules.get(i);
                                
                                if( rule.size() == 2 )
                                {
                                        continue;
                                }

                                        String prodName = rule.get(0);
                                        for(int k = 0; k < ignoredRules.size(); k++)
                                        {
                                                // there may be more than one production that produces a
                                                // terminal. so we need to set all productions that
                                                // match the current production in this ignore array as
                                                // 'ignored'
                                                if(prodName.compareTo(ignoredRules.get(k).ProductionName) == 0)
                                                {
                                                        System.out.println("B Found " + prodName + " is equal to existing ignored prod " + ignoredRules.get(k).ProductionName);
                                                        ignoredRules.get(k).Ignored = true;
                                                }
                                        }

                                writer.write("\t\t<production name=\"" + rule.get(0) + "\">\n");
                                for(int j = 1; j < rule.size(); j++)
                                {
                                        char temp = rule.get(j).charAt(0);
                                        if(temp >= 'A' && temp <= 'Z')
                                        {
                                                writer.write("\t\t\t<variable>" + rule.get(j) + "</variable>\n");
                                        }
                                        else
                                        {
                                                writer.write("\t\t\t<terminal>" + rule.get(j) + "</terminal>\n");
                                        }
                                }
                                writer.write("\t\t</production>\n");
                        }

                        for(int k = 0; k < ignoredRules.size(); k++)
                        {
                            IgnoredRule ir = ignoredRules.get(k);
                            if(ir.Ignored == false)
                            {
                                writer.write("\t\t<production name=\"" + ir.ProductionName + "\">\n");
                                writer.write("\t\t\t<terminal>" + ir.Terminal + "</terminal>\n");
                                writer.write("\t\t</production>\n");
                            }
                        }
                        writer.write("\t</productions>\n</grammar>");
                        
                        writer.close();

                        return ignoredRules;

                } catch (FileNotFoundException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }catch (IOException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }
                return null;
        }
}

