package br.com.vivere.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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
import br.com.vivere.exception.NomeFuncionalException;
import br.com.vivere.util.PropertiesReader;

@SuppressWarnings("serial")
public abstract class ValidationsXmlTags {

	private static HashMap<String, String> nomesFuncionaisMap = new HashMap<String, String>() {
		{

			Properties nomesFuncionaisProp = PropertiesReader.getFuncionalProp();
			Set<String> keys = nomesFuncionaisProp.stringPropertyNames();

			for (String key : keys) {
				put(key, nomesFuncionaisProp.getProperty(key));
			}

		}
	};

	private static List<String> listaEntidadesSemSequence = new ArrayList<String>(
			Arrays.asList(
					PropertiesReader.getValor("entidades.sem.sequence.pr3").split(",")					
			)
			
	);

	private static List<String> listaEntidadesSemTagAuditoria = new ArrayList<String>(
			Arrays.asList(PropertiesReader.getValor("entidades.sem.tag.auditoria").split(","))
	);
	
	private static List<String> listaInicioEntidadesParametrizador = new ArrayList<String>(
			Arrays.asList(PropertiesReader.getValor("entidades.parametrizador").split(","))
	);

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		change();

	}// fim do metodo main

	public static void change() throws Exception {

		String diretorio = PropertiesReader.getValor("diretorio.hbm");

		File file = new File(diretorio);
		File arquivos[] = file.listFiles();

		for (File arquivoHbm : arquivos) {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(arquivoHbm);
			doc.getDocumentElement().normalize();

			NodeList metaTestList = doc.getElementsByTagName("meta");

			if (metaTestList.getLength() != 0) {
				System.out.println("Arquivo XML " + arquivoHbm.getName() + " já se encontra atualizado");
				continue;
			}

			NodeList elementList = doc.getElementsByTagName("class");
			Node classElement = elementList.item(0);

			inserirTags(doc, classElement, arquivoHbm);

			realizarValidacoes(doc, classElement, arquivoHbm);

			escreverArquivoHBM(doc, classElement, arquivoHbm);

		} // fim da iteracao for

		System.out.println("Atualização de Todos HBMs realizado com sucesso.");

	}

	private static void inserirTags(Document doc, Node classElement, File arquivoHbm) {

		inserirTagAuditoria(doc, classElement);

		inserirTagsChaveSimples(doc, classElement);

		inserirTagsChaveComposta(doc, classElement, arquivoHbm);

	}

	private static void realizarValidacoes(Document doc, Node classElement, File arquivoHbm)
			throws NomeFuncionalException {

		validacaoTipagemPropriedades(doc);

		validacaoNomesFuncionaisEntidades(doc, classElement, arquivoHbm);

	}

	private static void inserirTagAuditoria(Document doc, Node classElement) {

		NamedNodeMap attrClass = classElement.getAttributes();
		Node attrTable = attrClass.getNamedItem("table");

		if (!listaEntidadesSemTagAuditoria.contains(attrTable.getTextContent().substring(0, 3))) {

			NodeList commentList = doc.getElementsByTagName("comment");
			Node commentElement = commentList.item(0);
			Element metaNode = doc.createElement("meta");
			metaNode.setTextContent("com.viverebrasil.contextaware.audittracking.IAuditable");
			Attr attAttribute = doc.createAttribute("attribute");
			attAttribute.setValue("implements");
			metaNode.setAttributeNode(attAttribute);
			Attr attInherit = doc.createAttribute("inherit");
			attInherit.setValue("false");
			metaNode.setAttributeNode(attInherit);
			classElement.insertBefore(metaNode, commentElement);

		}

	}// fim do metodo inserirTagAuditoria

	private static void inserirTagsChaveSimples(Document doc, Node classElement) {

		// Metas de use no id
		NodeList idList = doc.getElementsByTagName("id");
		if (idList != null && idList.getLength() > 0) {
			Node idElement = idList.item(0);
			NodeList childNodeList = idElement.getChildNodes();

			// Meta
			for (int i = 0; i < childNodeList.getLength(); i++) {
				Node childElement = childNodeList.item(i);

				if (childElement.getNodeName().equals("column")) {

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

			if (attrType.getTextContent().equals("big_integer")) {

				for (int i = 0; i < childNodeList.getLength(); i++) {

					Node childElement = childNodeList.item(i);

					if (childElement.getNodeName().equals("generator")) {

						NamedNodeMap attrClass = classElement.getAttributes();
						Node attrTable = attrClass.getNamedItem("table");

						if (listaEntidadesSemSequence.contains(attrTable.getTextContent())) {
							continue;
						}

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

						Element seqNameParamElement = doc.createElement("param");
						seqNameParamElement.setTextContent("SEQ_" + attrTable.getTextContent());
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

	}// fim do metodo inserirTagsChaveSimples

	private static void inserirTagsChaveComposta(Document doc, Node classElement, File arquivoHbm) {

		NodeList compositeIdList = doc.getElementsByTagName("composite-id");
		if (compositeIdList != null && compositeIdList.getLength() > 0) {

			Node compositeIdElement = compositeIdList.item(0);
			NodeList childNodeList = compositeIdElement.getChildNodes();
			Node keyPropertyElement = childNodeList.item(0);

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

			NamedNodeMap attrClass = compositeIdElement.getAttributes();
			Node attrClassComp = attrClass.getNamedItem("class");
			String nomeTabelaHbm = arquivoHbm.getName().replace(".hbm.xml", "");
			String nomeTabelaHbmId = arquivoHbm.getName().replace(".hbm.xml", "").concat("Id");
			String nomeFuncional = attrClassComp.getTextContent().replace(nomeTabelaHbmId,
					"domain." + nomesFuncionaisMap.get(nomeTabelaHbm).concat("Id"));
			attrClassComp.setTextContent(nomeFuncional);
		}

	}// fim do metodo inserirTagsChaveComposta

	private static void validacaoTipagemPropriedades(Document doc) {

		NodeList propertyList = doc.getElementsByTagName("property");

		for (int i = 0; i < propertyList.getLength(); i++) {

			Node propertyElement = propertyList.item(i);

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

	}// fim do metodo validacaoTipagemPropriedades

	private static void validacaoNomesFuncionaisEntidades(Document doc, Node classElement, File arquivoHbm)
			throws NomeFuncionalException {

		// Alterando os nomes funcionais
		NamedNodeMap attrClass = classElement.getAttributes();
		Node attrName = attrClass.getNamedItem("name");
		String nomeTabelaHbm = arquivoHbm.getName().replace(".hbm.xml", "");

		if (nomesFuncionaisMap.get(nomeTabelaHbm) == null) {
			throw new NomeFuncionalException(
					"Parametro no arquivo properties para a tabela " + nomeTabelaHbm + " nao encontrado.");
		}

		String nomeFuncional = attrName.getTextContent().replace(nomeTabelaHbm,
				"domain." + nomesFuncionaisMap.get(nomeTabelaHbm));
		attrName.setTextContent(nomeFuncional);

		List<String> relationshipList = new ArrayList<String>(
				Arrays.asList("one-to-one", "one-to-many", "many-to-one", "many-to-many"));

		for (String relationship : relationshipList) {
			alteracaoNomesFuncionaisRelacionamentosEntidades(doc, relationship, classElement);
		}

	}// fim do metodo validacaoNomesFuncionaisEntidades

	private static void alteracaoNomesFuncionaisRelacionamentosEntidades(Document doc, String relationship,Node classElement)
			throws NomeFuncionalException {

		NamedNodeMap attrClass = classElement.getAttributes();
		Node attrTable = attrClass.getNamedItem("table");
		
		NodeList relationshipList = doc.getElementsByTagName(relationship);

		for (int i = 0; i < relationshipList.getLength(); i++) {

			Node relationshipElement = relationshipList.item(i);

			NamedNodeMap relationshipMap = relationshipElement.getAttributes();
			Node attrClas = relationshipMap.getNamedItem("class");
			String nomeTabelaHbmoneToMany = attrClas.getTextContent().split("com.viverebrasil.app.")[1];
			nomeTabelaHbmoneToMany = nomeTabelaHbmoneToMany.substring(nomeTabelaHbmoneToMany.indexOf(".") + 1, nomeTabelaHbmoneToMany.length());
			
			if (listaInicioEntidadesParametrizador.contains(attrTable.getTextContent().substring(0, 3))) {
				
				String nomeTabela = attrClas.getTextContent().split("com.viverebrasil.app.")[1];
				String nomePacote = nomeTabela.substring(0,nomeTabela.indexOf("."));
				attrClas.setTextContent(attrClas.getTextContent().replace(nomePacote, "parametrizador"));
				
			}

			if (nomesFuncionaisMap.get(nomeTabelaHbmoneToMany) == null) {
				throw new NomeFuncionalException(
						"Parametro no arquivo properties para a tabela " + nomeTabelaHbmoneToMany + " nao encontrado.");
			}

			String nomeFuncionaloneToMany = attrClas.getTextContent().replace(nomeTabelaHbmoneToMany, "domain." + nomesFuncionaisMap.get(nomeTabelaHbmoneToMany));
			attrClas.setTextContent(nomeFuncionaloneToMany);
		}

	}// fim do metodo alteracaoNomesFuncionaisRelacionamentosEntidades

	private static void escreverArquivoHBM(Document doc, Node classElement, File arquivoHbm) throws Exception {

		doc.getDocumentElement().normalize();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(arquivoHbm);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
		System.out.println("Arquivo XML " + arquivoHbm.getName() + " atualizado com sucesso.");

	}

}// fim da classe