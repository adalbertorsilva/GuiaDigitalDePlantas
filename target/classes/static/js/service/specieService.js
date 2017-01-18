angular.module("guia").service("specieAPI", function($http){	
	
	this.getSpecie = function(id){
		return $http.get("http://localhost:8080/specie/" + id);
	}
	
	this.saveSpecie = function(specie){
	    return $http.post("http://localhost:8080/specie/", specie);
	}
	
});