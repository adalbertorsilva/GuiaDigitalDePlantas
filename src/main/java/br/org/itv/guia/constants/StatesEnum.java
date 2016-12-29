package br.org.itv.guia.constants;

public enum StatesEnum {
	
	PARA("para", "Pará"),
	AMAZONAS("amazonas", "Amazonas"),
	ACRE("acre", "Acre"),
	RORAIMA("roraima", "Roraima"),
	AMAPA("amapa", "Amapá"),
	TOCANTINS("tocantins", "Tocantins"),
	RONDONIA("rondonia", "Rondônia"),
	MARANHAO("maranhao", "Maranhão"),
	ALAGOAS("alagoas", "Alagoas"),
	BAHIA("bahia", "Bahia"),
	CEARA("ceara", "Ceará"),
	PARAIBA("paraiba", "Paraíba"),
	PERNAMBUCO("pernambuco", "Pernambuco"),
	PIAUI("piaui", "Piauí"),
	RIO_GRANDE_DO_NORTE("rio_grande_do_norte", "Rio Grande do Norte"),
	SERGIPE("sergipe", "Sergipe"),
	GOIAS("goias", "Goiás"),
	MATO_GROSSO("mato_grosso", "Mato Grosso"),
	MATO_GROSSO_DO_SUL("mato_grosso_do_sul", "Mato Grosso do Sul"),
	MINAS_GERAIS("minas_gerais", "Minas Gerais"),
	SAO_PAULO("sao_paulo", "São Paulo"),
	RIO_DE_JANEIRO("rio_de_janeiro", "Rio de Janeiro"),
	ESPIRITO_SANTO("espirito_santo", "Espirito Santo"),
	PARANA("parana", "Paraná"),
	SANTA_CATARINA("santa_catarina", "Santa Catarina"),
	RIO_GRANDE_DO_SUL("rio_grande_do_sul", "Rio Grande do Sul");
	
	private String key;
	private String stateName;
	
	private StatesEnum(String key, String statesName) {
		this.key = key;
		this.stateName = statesName;
	}
	
	public String getKey(){
		return key;
	}
	
	public String getStateName(){
		return stateName;
	}
	
	
}
