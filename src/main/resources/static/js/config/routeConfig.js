angular.module("guia").config(function($routeProvider, $locationProvider){
	
	$routeProvider.when("/",{
		templateUrl: "../views/list.html",
		controller: "indexController"
	});
	
	$routeProvider.when("/specie",{
		templateUrl: "../views/specie.html",
		controller: "specieController"
	});
	
	$routeProvider.when("/specie/:id",{
		templateUrl: "../views/specie.html",
		controller: "specieController"
	});
	
	$routeProvider.otherwise({redirectTo: "/"});
	
	$locationProvider.html5Mode({
	      enabled: true,
	      requireBase: false
	    });
	
});