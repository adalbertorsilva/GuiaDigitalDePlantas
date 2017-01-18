angular.module("guia").service("indexAPI", function($http){
	
	this.getSpecies = function(){
		return $http.get("http://localhost:8080/guia");
	}
	
});