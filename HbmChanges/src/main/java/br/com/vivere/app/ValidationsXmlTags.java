package br.com.vivere.app;

import java.io.File;
import java.util.HashMap;

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

@SuppressWarnings("serial")
public class ValidationsXmlTags {
	
	private static HashMap<String, String> nomesFuncionaisMap = new HashMap<String, String>() {{
		put("AdmSimBloco","AdmSimBloco");
		put("SisOrganizacao","GrupoEconomico");
		put("TbgApoliceMot","ApoliceMotivo");
		put("TbgApoliceRisco","ApoliceRisco");
		put("TbgCanalCaptacao","CanalCaptacao");
		put("TbgCaptacaoParam","ParametrosCaptacao");
		put("TbgContabilEve","ContabilEvento");
		put("TbgDocTerceiro","DocTerceiro");
		put("TbgDocUtilImov","DocUtilImov");
		put("TbgEmpresa","Empresa");
		put("TbgEmpresaOper","EmpresaOper");
		put("TbgFilial","Filial");
		put("TbgFornecCanal","FornecedorCanal");
		put("TbgFornecLoja","FornecedorLoja");
		put("TbgIndexador","Indexador");
		put("TbgLoja","Loja");
		put("TbgLojaCarac","LojaCarac");
		put("TbgLojaLiberacao","LojaLiberacao");
		put("TbgLojaObjFinan","LojaObjFinan");
		put("TbgLojaProduto","LojaProduto");
		put("TbgLojaUtImov","LojaUtImov");
		put("TbgObjFinanUtil","ObjetoFinanciamentoUtilizacao");
		put("TbgObjFinanc","ObjetivoFinanciamento");
		put("TbgOfficer","Officer");
		put("TbgOperVarejo","OperadorVarejo");
		put("TbgProduto","Produto");
		put("TbgRegional","Regional");
		put("TbgSeguro","Seguro");
		put("TbgSeguroApolice","SeguroApolice");
		put("TbgSeguroParam","ParametrosSeguro");
		put("TbgTerceiro","Terceiro");
		put("TbgTerceiroAtrso","TerceiroAtraso");
		put("TbgTerceiroResp","TerceiroResp");
		put("TbgUtilImovel","UtilImovel");
		put("SisItensCdc","ItensCdc");
		put("SisPadraoEvento","PadraoEvento");
		put("TbgCaracMora","CaracteristicaMora");
		put("TbgCartCtaEsp","CarteiraContaEspecial");
		put("TbgCartCtaMsg","CarteiraClienteMensagem");
		put("TbgCartInterface","CarteiraInterface");
		put("TbgCartNossoNum","CarteiraNumeracaoBloquetos");
		put("TbgCartRetErro","CarteiraRetornoErro");
		put("TbgCarteiraCorr","CarteiraCorrespondente");
		put("TbgClassImovel","ClassificacaoImovel");
		put("TbgCmcSeguradora","SeguradoraRegraComercial");
		put("TbgCntaIntrRisc","ContaInterfaceRisco");
		put("TbgCobMoraMulta","CobMoraMulta");
		put("TbgComuncMsgTxt","ComunicadoMensagemTexto");
		put("TbgComunicMsg","ComunicadosMensagem");
		put("TbgContabilConta","ContabilConta");
		put("TbgContabilEve","ContabilEve");
		put("TbgContabilGrupo","ContabilGrupo");
		put("TbgCorDamp","RegrasRessarcidoAmortizar");
		put("TbgCorPgtoVend","RegrasPagamentoVendedor");
		put("TbgDescFgts","InfoDescontFgts");
		put("TbgDocCanal","DocumentoCanal");
		put("TbgDocCapacidade","DocumentoCapacidade");
		put("TbgDocClassImov","DocumClassifImovel");
		put("TbgDocComunhao","DocumentoComunhao");
		put("TbgDocEstCivil","DocumEstadoCivil");
		put("TbgDocMunicipio","DocumentoMunicipio");
		put("TbgDocNatOcup","DocumentoNaturezaOcupacional");
		put("TbgDocNatocuCan","DocNatocuCan");
		put("TbgDocQuaPtcnt","ParamDocumQualif");
		put("TbgDocScript","DocumentoScript");
		put("TbgDocSegtoPad","DocSegmentoPadrao");
		put("TbgDocTpImovel","DocumentoTpImovel");
		put("TbgEnqGrupo","EnquadramentoGrupo");
		put("TbgEnqGrupoUf","EnquadramentoGrupoUf");
		put("TbgEnqPlanoEmpr","EnquadramentoPlanoEmpresarial");
		put("TbgFgtsRetorno","FgtsRetorno");
		put("TbgFluxoCadastro","FluxoCadastro");
		put("TbgFunding","ParamTpFunding");
		put("TbgFundingParam","ParamTpFundingConf");
		put("TbgGarantia","Garantia");
		put("TbgGrpProfissao","GrupoProfissao");
		put("TbgGrupoAtraso","GrupoAtraso");
		put("TbgGrupoStEsp","ParamGrupoStEsp");
		put("TbgGrupoTaxa","GrupoTaxa");
		put("TbgGrupoTaxaDoc","GrupoTaxaDocumento");
		put("TbgIdxDiario","IndiceDiario");
		put("TbgIdxEspIndet","RegistroIndicePercentIof");
		put("TbgIndexador","Indexador");
		put("TbgIndiceMensal","IndiceMensal");
		put("TbgIrAplicacao","AliquotaIrAplicacao");
		put("TbgJuros","Juros");
		put("TbgJurosFaixa","JurosFaixa");
		put("TbgJurosParam","ParametrosJuros");
		put("TbgListaBenProd","QualificacaoProduto");
		put("TbgMatrizImovel","MatrizImovel");
		put("TbgModeloNegocio","ModeloNegocio");
		put("TbgMotRecusPort","MotivoRecusaPortabilidadeCred");
		put("TbgNatGrpProfis","NaturezaGrupoProfissao");
		put("TbgNaturezaOcup","NaturezaOcup");
		put("TbgOcorExclud","OcorrenciaExcludente");
		put("TbgOrigemReceb","OrigemRecebimento");
		put("TbgPadraoPerfil","PadraoPerfil");
		put("TbgPadraoTr","PadraoTr");
		put("TbgParecerDepto","ParecerDepto");
		put("TbgPcJurosAmort","PercComprometJurosAmortizacao");
		put("TbgPgtoRetErro","PagamentoRetornoErro");
		put("TbgQualifPartcnt","QualificacaoParticipante");
		put("TbgQuestGenerica","QuestGenerica");
		put("TbgQuestResposta","RespostasQuestoes");
		put("TbgRegraComerc","RegraComerc");
		put("TbgRelacionamCli","RelacionamCli");
		put("TbgRelacionamTax","RelacionamentoTaxa");
		put("TbgRgrCmcTaxa","RegraComercialTaxa");
		put("TbgRiscoBacen","RiscoBacen");
		put("TbgScrCredItens","ScriptsDecicaoItens");
		put("TbgScriptCredito","RegrasScriptCredito");
		put("TbgScriptParam","ArvoreDecisaoScripts");
		put("TbgSegSegtoPad","SegSegtoPad");
		put("TbgSegmentoPad","SegmentoPadrao");
		put("TbgSitAtraso","SituacaoAtraso");
		put("TbgSitContabil","SituacaoContabil");
		put("TbgStEspecial","SituacaoEspecial");
		put("TbgSubModalidade","SubModalidadeProduto");
		put("TbgTaxaDiaria","TaxaDiaria");
		put("TbgTaxaDoc","TaxaDoc");
		put("TbgTipoAnuente","TipoAnuente");
		put("TbgTpLiberacao","TipoLiberacao");
		put("TbgTpLiquidacao","TipoLiquidacao");
		put("TbgTpModProduto","TipoModalidadeProduto");
		put("TbgTpPropriedade","TpPropriedade");
		put("TbgTpSede","TipoSede");
		put("TbgValDifCorr","ValorDiferencaCorrigido");
		put("TbgValFinanc","ValorFinanciamento");
		put("TbgValImovel","ValorImovel");
		put("SisBloco","Bloco");
		put("TbgDocumento","Documento");
		put("TbgProduto","Produto");
		put("TbgSeguroApolice","SeguroApolice");
		put("TbgTerceiro","Terceiro");
		put("AdmPrdChecklist","AdministracaoProdutoChecklist");
		put("AdmPrdChklstOri","ProdutoChecklistOrigem");
		put("AdmPrdDocAtivo","AdministracaoProdutoDocumentoAtivo");
		put("AdmPrdFaseAviso","AdministracaoProdutoFaseAviso");
		put("AdmPrdFaseBloco","AdministracaoProdutoFaseBloco");
		put("AdmPrdFaseServ","AdministracaoProdutoFaseServico");
		put("AdmPrdFaseStesp","AdministracaoProdutoFaseCaracterEspecial");
		put("AdmPrdMotDesenq","ProdutoMotivoDesenquadramento");
		put("AdmPrdMotdesAtv","ProdutoMotivoDesenquadramentoAtividade");
		put("AdmPrdMotdesDep","ProdutoMotivoDesenquadramentoDependencia");
		put("AdmPrdMotDesDtv","ProdutoMotivoDesenquadramentoFases");
		put("AdmPrdMotdesExc","ProdutoMotivoDesenquadramentoExcecao");
		put("AdmPrdMotdesRgr","ProdutoMotivoDesenquadramentoRegras");
		put("AdmPrdMtdRgrpar","ProdutoMotivoRegraDesenquadramentoParametro");
		put("AdmPrdParDepto","ProdutoParecerDepartamento");
		put("AdmPrdReqAtiva","ProdutoRequisicaoAtiva");
		put("AdmPrdReqDoc","AdministracaoProdutoRequisitoDocumento");
		put("AdmPrdReqFase","AdministracaoProdutoRequisitoFase");
		put("AdmPrdReqFaseS","AdministracaoProdutoRequisitoFaseS");
		put("AdmProdutoFase","AdministracaoProdutoFase");
		put("SisBloco","Bloco");
		put("SisBlocoCampo","BlocoCampo");
		put("SisFase","Fase");
		put("SisGrpBlocoDoc","GrupoBlocoDocumento");
		put("SisGrupoBloco","GrupoBloco");
		put("SisRgrDesenqPar","RegrasDesenquadramentoParametro");
		put("SisRgrDesenquadr","RegrasDesenquadramento");
		put("TbgAviso","Aviso");
		put("TbgAvsoProdFase","AvisoProdutoFase");
		put("TbgCanalEmail","CanalEmail");
		put("TbgCanalMensg","CanalMensagem");
		put("TbgClausula","Clausula");
		put("TbgClausulaDoc","ClausulaDoc");
		put("TbgClausulaMunic","ClausulaMunic");
		put("TbgClausulaTerc","ClausulaTerc");
		put("TbgClausulaTexto","ClausulaTexto");
		put("TbgClausulaUf","ClausulaUf");
		put("TbgContrAnexo","ContratoAnexo");
		put("TbgContrAssina","ContratoAssinatura");
		put("TbgContrClausula","ContratoClausula");
		put("TbgContrCompos","ContratoComposicao");
		put("TbgContrGarant","ContratoGarantia");
		put("TbgContrOutras","ContratoOutras");
		put("TbgContrParticip","LayoutGrupoContratoParametro");
		put("TbgContrResumo","ContratoResumo");
		put("TbgContrato","Contrato");
		put("TbgDestCnlMensg","DestinoCanalMensagem");
		put("TbgDocAnaFase","FaseAnaliseDocumento");
		put("TbgDocAutomatico","DocAutomatico");
		put("TbgDocCompFase","DocumentoComposicaoFase");
		put("TbgDocCompItem","DocumentoComposicaoItem");
		put("TbgDocComposicao","DocComposicao");
		put("TbgDocMunParam","DocumentoMunicipioParametro");
		put("TbgDocMunSubst","DocumentoMunicipioSubstituido");
		put("TbgDocPendencia","DocPendencia");
		put("TbgDocProduto","DocumentoProduto");
		put("TbgDocResposta","DocumentoResposta");
		put("TbgDocTpEmpresa","DocumentoTipoEmpresa");
		put("TbgDocTpPessoa","DocumentoTipoPessoa");
		put("TbgDocUfParam","DocumentoUfParametro");
		put("TbgDocUfSubst","DocumentoUfSubstituido");
		put("TbgDocumento","Documento");
		put("TbgFaseArea","FaseArea");
		put("TbgFaseCanal","FaseCanal");
		put("TbgFaseConfig","FaseConfiguracao");
		put("TbgFaseDepParam","FaseDepositoParametro");
		put("TbgFaseDeposito","FaseDeposito");
		put("TbgFaseItemDep","FaseItemDeposito");
		put("TbgFaseMotFase","FaseMotivoFase");
		put("TbgFaseMotOcor","FaseMotivoOcorrencia");
		put("TbgFaseMotivo","FaseMotivo");
		put("TbgFilProduto","FiltroProduto");
		put("TbgGrpFaseCanal","GrupoFaseCanal");
		put("TbgGrupoFase","GrupoFase");
		put("TbgGrupoOcorr","GrupoOcorrencia");
		put("TbgIdentProduto","IdentificacaoProduto");
		put("TbgIntGrpFase","IntGrpFase");
		put("TbgInterfOcor","InterfaceOcorrencia");
		put("TbgItmIdentProd","ItemIdentificacaoProduto");
		put("TbgItmIdentPtrp","ItemIdentificacaoParticipante");
		put("TbgMatrImovelUf","MatriculaImovelUf");
		put("TbgMatrPtrpCart","MatrizParticipanteCartorio");
		put("TbgMatrizPart","MatrizParticipantes");
		put("TbgMatrizRepr","MatrizRepresentante");
		put("TbgModalProduto","ModalidadeProduto");
		put("TbgMotDesenquadr","MotivoDesenquadramento");
		put("TbgNivlCitClaus","NivelCitacaoClausula");
		put("TbgOcorAtend","OcorrenciaAtendimento");
		put("TbgOcorTaxa","OcorrenciaTaxa");
		put("TbgOcorTpLiquid","OcorrenciaTipoLiquidacao");
		put("TbgOcorVlr","OcorrenciaValor");
		put("TbgOcorrencia","Ocorrencia");
		put("TbgOperFaseArea","OperadorFaseArea");
		put("TbgOperFaseDep","OperadorFaseDeposito");
		put("TbgPerfilOcor","PerfilOcorrencia");
		put("TbgPrdContrComp","ProdutoContratoComposicao");
		put("TbgPrdContrMail","TemplateRelatorioEmail");
		put("TbgPrdFseDstCn","ProdutoFaseDestinoCanal");
		put("TbgProcOcor","ProcedimentoOcorrencia");
		put("TbgProdAtributo","ProdutoAtributo");
		put("TbgProdFormLib","ProdutoFormaLiberacao");
		put("TbgProdFormLiq","ProdutoFormaLiquidacao");
		put("TbgProdTemplate","ProdutoTemplate");
		put("TbgProdutoFgts","ProdutoFgts");
		put("TbgProdutoGrupo","ProdutoGrupo");
		put("TbgProdutoIndex","ProdutoIndex");
		put("TbgProdutoOcor","ProdutoOcor");
		put("TbgProdutoTaxa","ProdutoTaxa");
		put("TbgProdutoUf","ProdutoUf");
		put("TbgReapImgDoc","ReapImgDoc");
		put("TbgSubProduto","SubProduto");
		put("TbgSuspExcFase","SuspensaoExcecaoFase");
		put("TbgSuspGrpFase","SuspensaoGrupoFase");
		put("TbgTmpltDestnCn","TemplateDestinatarioCanal");
		put("TbgTpDestnMensg","TipoDestinatarioMensagem");
		put("TbgTpGrpPartici","TipoGrupoParticipacao");
		put("TbgTpModalidade","TipoModalidade");
		put("TbgTpPropriProd","TipoPropriedadeProduto");
		put("TbgUtilImovProd","UtilizacaoImovelProduto");
		put("SisClasseItem","ClasseItem");
		put("SisItensCdc","ItensCdc");
		put("TbgEmpresa","Empresa");
		put("TbgQuestGenerica","QuestGenerica");
		put("TbgTaxaDoc","TaxaDoc");
		put("TbgTerceiro","Terceiro");
		put("AdmAgencia","AgenciaAdministracao");
		put("AdmAlcadaAprov","AlcadaAprovacao");
		put("AdmItensVarQst","ItensOpcaoRespostaOperacao");
		put("AdmMenu","Menu");
		put("AdmModulo","Modulo");
		put("AdmPerfil","Perfil");
		put("AdmPerfilMenu","PerfilMenu");
		put("AdmPerfilRegra","PerfilRegra");
		put("SegPerfDetFase","PerfilDetalheFase");
		put("SegPerfil","SegurancaPerfil");
		put("SegPerfilGrp","PerfilGrupo");
		put("SegPermissao","Permissao");
		put("SegRegraSenha","RegraSenha");
		put("SegUsuPerfil","UsuarioPerfil");
		put("SegUsuario","SegurancaUsuario");
		put("SisClasseItem","ClasseItem");
		put("SisColunas","Colunas");
		put("SisEntidade","Entidade");
		put("SisGrupoRegra","GrupoRegra");
		put("SisProfile","Profile");
		put("SisPrograma","Programa");
		put("SisRegra","Regra");
		put("SisServAtributo","ServicoAtributo");
		put("SisServErro","ServicoErro");
		put("SisServParametro","ServidorParametro");
		put("SisServico","Servico");
		put("SisSessao","Sessao");
		put("SisSistema","Sistema");
		put("SisUf","Uf");
		put("TbgAgencia","Agencia");
		put("TbgBancos","Bancos");
		put("TbgCapacidCivil","CapacidadeCivil");
		put("TbgCatImovel","CategoriaImovel");
		put("TbgCep","CEP");
		put("TbgDadosInterv","DadosCadastraisIntervenientes");
		put("TbgDescPerfil","DescontoPerfil");
		put("TbgDominioContd","DominioConteudo");
		put("TbgDominioTabela","DominioTabela");
		put("TbgEscolaridade","Escolaridade");
		put("TbgEstadoCivil","EstadoCivil");
		put("TbgFeriado","Feriado");
		put("TbgFeriadoMun","FeriadoMun");
		put("TbgFeriadoUf","FeriadoUf");
		put("TbgFonteRec","FonteRecurso");
		put("TbgGerMercado","GerenciaMercado");
		put("TbgGrauParente","GrauParente");
		put("TbgHistNavegacao","HistoricoNavegacao");
		put("TbgInstrucao","Instrucao");
		put("TbgInterface","Interface");
		put("TbgInterveniente","Intereveniente");
		put("TbgMatrPtrpUf","MatrizParticipanteUf");
		put("TbgModeloDoc","ModeloDocumento");
		put("TbgMotivoCancel","MotivoCancelamento");
		put("TbgMunicipio","Municipio");
		put("TbgMunicipioCep","MunicipioCep");
		put("TbgNacionalidade","Nacionalidade");
		put("TbgNaturJurid","NaturezaJuridica");
		put("TbgPadraoCons","InformacoesEstruturaContrato");
		put("TbgPais","Pais");
		put("TbgPalavraGenero","PalavraGenero");
		put("TbgQualifRepLeg","QualificacaoRepresentanteLegal");
		put("TbgRamoAtividade","RamoAtividade");
		put("TbgRegiao","Regiao");
		put("TbgRegimeTribut","RegimeTributario");
		put("TbgSegmentoCli","SegmentoCli");
		put("TbgSitFuncProp","SituacaoFuncionalProponente");
		put("TbgTela","Tela");
		put("TbgTipComent","TipoComentario");
		put("TbgTpComunhao","TipoComunhao");
		put("TbgTpConta","TipoConta");
		put("TbgTpEmpresa","TipoEmpresa");
		put("TbgTpLogradouro","TipoLogradouro");
		put("TbgTpResidencia","TipoResidencia");
		put("TbgTpSeguro","TipoSeguro");
		put("TbgUf","Uf");
		put("TbgEmpresa","Empresa");
		put("TbgFilial","Filial");

	}};
	
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
			String nomeTabelaHbm = arquivoHbm.getName().replace(".hbm.xml", "");
			String nomeFuncional = attrName.getTextContent().replace(nomeTabelaHbm, "domain."+nomesFuncionaisMap.get(nomeTabelaHbm));
			attrName.setTextContent(nomeFuncional);
			
			NodeList manyToOneList = doc.getElementsByTagName("many-to-one");
			
			for (int i = 0; i < manyToOneList.getLength(); i++) {
				
				Node manyToOneElement =  manyToOneList.item(i);
				
				NamedNodeMap manyToOneAttMap = manyToOneElement.getAttributes();
				Node attrClas = manyToOneAttMap.getNamedItem("class");
				String nomeTabelaHbmManyToOne = attrClas.getTextContent().split("com.viverebrasil.app.parametrizador.")[1];
				String nomeFuncionalManyToOne = attrName.getTextContent().replace(nomeTabelaHbmManyToOne, "domain."+nomesFuncionaisMap.get(nomeTabelaHbmManyToOne));
				attrClas.setTextContent(nomeFuncionalManyToOne);
			}
			
			NodeList oneToManyList = doc.getElementsByTagName("one-to-many");
			
			for (int i = 0; i < oneToManyList.getLength(); i++) {
				
				Node oneToManyElement =  oneToManyList.item(i);
				
				NamedNodeMap oneToManyAttMap = oneToManyElement.getAttributes();
				Node attrClas = oneToManyAttMap.getNamedItem("class");
				String nomeTabelaHbmoneToMany = attrClas.getTextContent().split("com.viverebrasil.app.parametrizador.")[1];
				String nomeFuncionaloneToMany = attrName.getTextContent().replace(nomeTabelaHbmoneToMany, "domain."+nomesFuncionaisMap.get(nomeTabelaHbmoneToMany));
				attrClas.setTextContent(nomeFuncionaloneToMany);
			}
			
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