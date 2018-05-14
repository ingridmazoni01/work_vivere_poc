package br.com.vivere.enums;

public enum TipoDados {
	
	NAO_ENCONTRADO("",""),
	SERIALIZABLE("serializable", "calendar"), 
	FLOAT("float", "java.lang.Float"),
	LONG("long", "java.lang.Long"),
	INT("int", "java.lang.Integer")
	
	;

	private TipoDados(String nomeHbm, String nomeReal) {
		this.nomeHbm = nomeHbm;
		this.nomeReal = nomeReal;
	}

	private String nomeHbm = null;
	private String nomeReal = null;

	public String getNomeHbm() {
		return nomeHbm;
	}

	public void setNomeHbm(String nomeHbm) {
		this.nomeHbm = nomeHbm;
	}

	public String getNomeReal() {
		return nomeReal;
	}

	public void setNomeReal(String nomeReal) {
		this.nomeReal = nomeReal;
	}

	public static TipoDados recuperarEnum(String codigo) {

		for (TipoDados itemEnum : values()) {
			if (itemEnum.getNomeHbm().equals(codigo)) {
				return itemEnum;
			}
		}

		return NAO_ENCONTRADO;
	}

}
