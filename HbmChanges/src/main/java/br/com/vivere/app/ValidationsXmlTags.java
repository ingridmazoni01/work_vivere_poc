package br.com.vivere.app;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.vivere.enums.TipoDados;

public class ValidationsXmlTags {
	
	//Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		String diretorio = "C:\\Users\\silvio.angelo\\Downloads\\hbm automatizar";

		File file = new File(diretorio);
		File arquivos[] = file.listFiles();
		
		for (File arquivoHbm : arquivos) {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(arquivoHbm);
			doc.getDocumentElement().normalize();
			
			NodeList metaTestList = doc.getElementsByTagName("meta");
			
			if(metaTestList.getLength() != 0) {
				System.out.println("Arquivo XML "+arquivoHbm.getName()+" já se encontra atualizado");
				continue;
			}
			
			//Modifica
			NodeList elementList = doc.getElementsByTagName("class");
			Node classElement =  elementList.item(0);
			
			//Tag Audit
			NodeList commentList = doc.getElementsByTagName("comment");
			Node commentElement =  commentList.item(0);
			Element metaNode = doc.createElement("meta");
			metaNode.setTextContent("com.viverebrasil.contextaware.audittracking.IAuditable");
			Attr attAttribute = doc.createAttribute("attribute");
			attAttribute.setValue("implements");
			metaNode.setAttributeNode(attAttribute);
			Attr attInherit = doc.createAttribute("inherit");
			attInherit.setValue("false");
			metaNode.setAttributeNode(attInherit);
			classElement.insertBefore(metaNode, commentElement);
			
			//Metas de use no id
			NodeList idList = doc.getElementsByTagName("id");
			if(idList != null && idList.getLength() > 0) {
				Node idElement =  idList.item(0);
				NodeList childNodeList = idElement.getChildNodes();
				
				//Meta
				for (int i = 0; i < childNodeList.getLength(); i++) {
					Node childElement =  childNodeList.item(i);
					
					if(childElement.getNodeName().equals("column")) {
						
						Element metaIdNode = doc.createElement("meta_alterar");
						metaIdNode.setTextContent("true");
						Attr attIdAttribute = doc.createAttribute("attribute");
						attIdAttribute.setValue("use-in-tostring");
						metaIdNode.setAttributeNode(attIdAttribute);
						
						Element metaId2Node = doc.createElement("meta_alterar");
						metaId2Node.setTextContent("true");
						Attr attId2Attribute = doc.createAttribute("attribute");
						attId2Attribute.setValue("use-in-equals");
						metaId2Node.setAttributeNode(attId2Attribute);
						metaId2Node.removeAttribute("inherit");
						
						idElement.insertBefore(metaIdNode, childElement);
						idElement.insertBefore(metaId2Node, childElement);
						break;
					}
					
				}
				
				NamedNodeMap attr = idElement.getAttributes();
				Node attrType = attr.getNamedItem("type");
				
				if(attrType.getTextContent().equals("big_integer")) {
					
					for (int i = 0; i < childNodeList.getLength(); i++) {
						
						Node childElement =  childNodeList.item(i);
						
						if(childElement.getNodeName().equals("generator")) {
							
							Element generatorSequenceElement = doc.createElement("generator");
							Attr attClass = doc.createAttribute("class");
							attClass.setValue("org.hibernate.id.enhanced.SequenceStyleGenerator");
							generatorSequenceElement.setAttributeNode(attClass);
							
							Element optimizerParamElement = doc.createElement("param");
							optimizerParamElement.setTextContent("none");
							Attr attOptName = doc.createAttribute("name");
							attOptName.setValue("optimizer");
							optimizerParamElement.setAttributeNode(attOptName);
							generatorSequenceElement.appendChild(optimizerParamElement);
							
							Element incSizeParamElement = doc.createElement("param");
							incSizeParamElement.setTextContent("1");
							Attr attIncSizeName = doc.createAttribute("name");
							attIncSizeName.setValue("increment_size");
							incSizeParamElement.setAttributeNode(attIncSizeName);
							generatorSequenceElement.appendChild(incSizeParamElement);
							
							NamedNodeMap attrClass = classElement.getAttributes();
							Node attrTable = attrClass.getNamedItem("table");
							Element seqNameParamElement = doc.createElement("param");
							seqNameParamElement.setTextContent("SEQ_"+attrTable.getTextContent());
							Attr seqNameSizeName = doc.createAttribute("name");
							seqNameSizeName.setValue("sequence_name");
							seqNameParamElement.setAttributeNode(seqNameSizeName);
							generatorSequenceElement.appendChild(seqNameParamElement);
							
							idElement.replaceChild(generatorSequenceElement, childElement);
							break;
						}
						
					}
					
				}
				
			}
			
			NodeList compositeIdList = doc.getElementsByTagName("composite-id");
			if(idList != null && compositeIdList.getLength() > 0) {
				
				Node compositeIdElement =  compositeIdList.item(0);
				NodeList childNodeList = compositeIdElement.getChildNodes();
				Node keyPropertyElement =  childNodeList.item(0);
				
				Element metaIdCompNode = doc.createElement("meta");
				metaIdCompNode.setTextContent("true");
				Attr attIdCompAttribute = doc.createAttribute("attribute");
				attIdCompAttribute.setValue("use-in-tostring");
				metaIdCompNode.setAttributeNode(attIdCompAttribute);
				
				Element metaId2CompNode = doc.createElement("meta");
				metaId2CompNode.setTextContent("true");
				Attr attId2CompAttribute = doc.createAttribute("attribute");
				attId2CompAttribute.setValue("use-in-equals");
				metaId2CompNode.setAttributeNode(attId2CompAttribute);
				
				compositeIdElement.insertBefore(metaIdCompNode, keyPropertyElement);
				compositeIdElement.insertBefore(metaId2CompNode, keyPropertyElement);
			}
			
			//Batimento dos tipos de serializable e float
			NodeList propertyList = doc.getElementsByTagName("property");
			
			for (int i = 0; i < propertyList.getLength(); i++) {
				
				Node propertyElement =  propertyList.item(i);
				
				NamedNodeMap propertyMap = propertyElement.getAttributes();
				Node attrType = propertyMap.getNamedItem("type");
				
				switch (TipoDados.recuperarEnum(attrType.getTextContent())) {
					case SERIALIZABLE:
						attrType.setTextContent(TipoDados.SERIALIZABLE.getNomeReal());
						break;
						
					case FLOAT:
						attrType.setTextContent(TipoDados.FLOAT.getNomeReal());
						break;
						
					default:
						break;
				}
				
			}
			
			//Alterando os nomes funcionais
			NamedNodeMap attrClass = classElement.getAttributes();
			Node attrName = attrClass.getNamedItem("name");
			String nomeTabela = arquivoHbm.getName().replace(".hbm.xml", "");
			attrName.getTextContent();
			
			//Escreve
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(arquivoHbm);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			System.out.println("Arquivo XML "+arquivoHbm.getName()+" atualizado com sucesso.");
			
		}//fim da iteracao for
		
	}//fim do metodo main

}//fim da classe