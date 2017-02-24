/** Used for generating DNAg Tool : as a part of Btech. Degree,2013.
 *The code has not been modified here,except the paths, and used as it is.
 *The code has been taken from google open source : code.google.com/p/cfgtocnf/source/browser/?r=2
 *We acknowledge @author : Connie Campbell for the code.*/
  
//package DNAg;
/*
 * CnfToCnf.java
 *
 * Created on November 24, 2007, 5:08 PM
 *
 *
 *
 * @author Bob Babilon
 */



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class CfgToCnf
{
        public CfgToCnf()
        {
        }
        
        /* Returns a new id number greater than 0
         * for use when creating new productions of
         * the form P_X where X is this integer value
         * and P is P.
         */
        private int nextUid() 
        {
                return prodUid += 1;
        }
        int prodUid = 0;
        
        Document doc = null;
        
        /* Creates new production element with single terminal child element
         * Terminal text content is set to value (input string) and the productions
         * name is the upper case value of "value" + "_1"
        */
        private Element CreateProductionElement(String value)
        {
                if(doc == null) {
                        throw new NullPointerException();
                }
                
                Element production = doc.createElement("production");
                production.setAttribute("name", value.toUpperCase() + "_0");
                production.appendChild(CreateTerminalElement(value));
                
                return production;
        }
        
        /*
         * Creates a terminal element
         */
        private Element CreateTerminalElement(String value)
        {
                if(doc == null) {
                        throw new NullPointerException();
                }
                
                Element terminal = doc.createElement("terminal");
                terminal.setTextContent(value);
                
                return terminal;
        }
        
        /* 
         * Does the hard work of converting the given CFG to GNF
         */
        public void convertGrammar(String grammarPath)
        {
                try
                {
                        // new production and terminal elements
                        //Element production, terminal;

                        // Creating a DOM Document
                        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                        domFactory.setNamespaceAware(true);
                
                        // open the xml Document for parsing
                        DocumentBuilder builder = domFactory.newDocumentBuilder();
                        doc = builder.parse(grammarPath);

                        // Create a factory for XPath queries
                        XPathFactory factory = XPathFactory.newInstance();
                        XPath xpath = factory.newXPath();
                        
                        XPathExpression productionsElement = xpath.compile("grammar/productions");
                        
                        // creates productions for all terminals that do not yet have a corresponding production
                        createTerminalProductions(xpath, productionsElement);
                        
                        // saves the current document so the style sheet can be applied
                        writeXmlDocument(doc, "C://DNAg//pass1.xml");
                        
                        // Replaces all the productions that have more than one child element and a terminal
                        // with the appropriate production that produces the terminal.
                        XslTransform.applyTransform("C://DNAg//cfgtocnf.xslt", "C://DNAg//pass1.xml", "C://DNAG//pass2.xml");
                        
                        // now must create productions for the junk that has more than 2 child elements
                        doc = builder.parse("C://DNAg//pass2.xml");
                        
                        // now split all productions that have more than 2 variables
                        splitProductions(xpath, productionsElement);


                        // finally save the document
                        writeXmlDocument(doc, "C://DNAg//output.xml");

                } catch(IOException ioex)
                {
                        ioex.printStackTrace();
                        System.exit(0);
                } catch (ParserConfigurationException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                } catch (SAXException sex)
                {
                        sex.printStackTrace();
                        System.exit(0);
                } catch (XPathException xex)
                {
                        xex.printStackTrace();
                        System.exit(0);
                } catch (ClassCastException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                } 
        }

        /* Creates a production for all terminals that do not already have a production
         * Example, entire grammar is:
         * S->BAc
         * B->b
         * A->a
         * This will find that the terminal "c" does not have a production and generate one
         * The new grammar will be:
         * S->BAc
         * B->b
         * A->a
         * [C_0]->c
         */
        private void createTerminalProductions(XPath xpath, XPathExpression productionsElement)
        {
                if(doc == null) {
                        throw new NullPointerException();
                }
                
                try
                {
                        // Selects the terminal's value for all productions with more than one variable or terminal
                        // ie: S -> ABbC the "b" would be selected b/c it is a terminal.
                        // The use of this is to find all terminals that may or may not need new productions created
                        XPathExpression expr = xpath.compile("grammar/productions//production[count(*)>1]/terminal/text()");
                        Object result = expr.evaluate(doc, XPathConstants.NODESET);
                        NodeList terminals = (NodeList)result;

                        // Selects the productions element and saves for later use when it is time
                        // to create new productions and append them to the xml file
                        NodeList prodRoot = (NodeList)productionsElement.evaluate(doc, XPathConstants.NODESET);

                        for(int i = 0; i < terminals.getLength(); i++)
                        {       
                                // Not sure what the effects are on compiling xpath expressions multiple times.
                                // Hopefully they're not lethal to Java
                                // This xpath will check if any production (with one child element) already produces
                                // the terminal that was found in some other production (with more than one child element)
                                XPathExpression expr2 = xpath.compile("grammar/productions//production[count(*)=1]/terminal[text()='" + terminals.item(i).getTextContent() + "']");

                                result = expr2.evaluate(doc, XPathConstants.NODESET);
                                NodeList nodes2 = (NodeList)result;

                                // don't have any productions that produce this terminal, so create one
                                if(nodes2.getLength() == 0) 
                                {
                                        prodRoot.item(0).appendChild(CreateProductionElement(terminals.item(i).getTextContent()));
                                }
                        }
                }
                catch (XPathException xex)
                {
                        xex.printStackTrace();
                        System.exit(0);
                } 
        }
        
        /*
         * Converts all productions of form S->x where x is two or more variables
         * Example:
         * S->ABCD
         * Would be changed to:
         * S->A[P_1] 
         * [P_1]->B[P_2]
         * [P_2]->CD
         */
        private void splitProductions(XPath xpath, XPathExpression productionsElement)
        {
                if(doc == null) {
                        throw new NullPointerException();
                }
                
                try
                {
                        // Now need to select all production elements w/greater than 2 child elements
                        XPathExpression expr2 = xpath.compile("grammar/productions//production[count(*)>2]");
                        Object result2 = expr2.evaluate(doc, XPathConstants.NODESET);
                        NodeList terminals2 = (NodeList)result2;
                        
                        Element replacement, newProd, tempProd;
                        
                        // select the productions element - everything will be appened to this
                        NodeList prodRoot = (NodeList)productionsElement.evaluate(doc, XPathConstants.NODESET);
                        
                        // now create new productions to handle cases such as S -> ABC
                        // and turn that into S -> AP; P -> BC
                        int count = terminals2.getLength();
                        for(int i = 0; i < count; i++)
                        {
                                // creating element with same name as the "i"th one which will eventually replace it
                                replacement = doc.createElement("production");
                                
                                // gets the first attributes value of the current terminal
                                replacement.setAttribute("name", terminals2.item(i).getAttributes().item(0).getNodeValue());
                                
                                // create a new production element with name P_X where X is a UID (integer value)
                                newProd = doc.createElement("production");
                                newProd.setAttribute("name", "P_" + nextUid());
                                
                                replacement.appendChild(terminals2.item(i).getChildNodes().item(0));
                                replacement.appendChild(CreateTerminalElement(newProd.getAttributes().item(0).getNodeValue()));
                                
                                while(terminals2.item(i).getChildNodes().getLength() > 2)
                                {
                                        newProd.appendChild(terminals2.item(i).getChildNodes().item(0));
                                        tempProd = doc.createElement("production");
                                        tempProd.setAttribute("name", "P_" + nextUid());
                                        newProd.appendChild(CreateTerminalElement(tempProd.getAttributes().item(0).getNodeValue()));
                                        
                                        prodRoot.item(0).appendChild(newProd);
                                        newProd = tempProd;
                                }

                                // automatically removes the child element from the production
                                newProd.appendChild(terminals2.item(i).getChildNodes().item(0));
                                newProd.appendChild(terminals2.item(i).getChildNodes().item(0));
                                
                                prodRoot.item(0).appendChild(newProd);
                                prodRoot.item(0).replaceChild(replacement, terminals2.item(i));                 
                        }//for
                }catch (XPathException xex)
                {
                        xex.printStackTrace();
                        System.exit(0);
                } 
        }
        
        /*
         * Writes given xml document, doc, to the specified filePath
         */
        private void writeXmlDocument(Document doc, String filePath) 
        {
                if(doc == null) {
                        throw new NullPointerException();
                }
                
                try
                {
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        //initialize StreamResult with File object to save to file
                        StreamResult result = new StreamResult(new StringWriter());
                        DOMSource source = new DOMSource(doc);
                        transformer.transform(source, result);
                        String xmlString = result.getWriter().toString();

                        FileOutputStream outputStream = new FileOutputStream(filePath);
                        outputStream.write(xmlString.getBytes());
                        outputStream.close();
                }catch(IOException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }catch(TransformerException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }catch (NullPointerException ex)
                {
                        ex.printStackTrace();
                        System.exit(0);
                }
        }
}
