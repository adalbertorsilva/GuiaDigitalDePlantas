angular.module("guia").controller("specieController",function($scope, $routeParams, specieAPI){
	
	var getSpecieById = function(id){
		if(id){
			specieAPI.getSpecie(id).then(function(response){
				$scope.specie = response.data
			});
		}
	};
	
	getSpecieById($routeParams.id);
	
	$scope.addCoordinate = function(){
		
		validateSpecie();
		
		if(!$scope.specie.coordinates){
			$scope.specie.coordinates = [];
		}
		
		$scope.specie.coordinates.push({
			latitude: $scope.latitude,
			longitude: $scope.longitude,
			place: $scope.place
		});
		
		
		clearCoordinate();
		
		
	}
	
	$scope.removeCoordinate = function(coordinate){
		
		$scope.specie.coordinates = $scope.specie.coordinates.filter(function(coord){
			
										if(!(coord.latitude == coordinate.latitude) &&
										   !(coord.longitude == coordinate.longitude) &&
										   !(coord.place == coordinate.place)){
											return coord
										}
			
									});
	}
	
	function clearCoordinate(){
		$scope.latitude = ""
		$scope.longitude = ""
		$scope.place = ""
	}
	
	$scope.addImage = function(){
		
		validateSpecie();
		
		if(!$scope.specie.pictures){
			$scope.specie.pictures = [];
		}
		
		$scope.specie.pictures.push({
			encoded_image: $scope.image_file.base64,
			picture_name: $scope.image_file.filename
		});
		
		$scope.image_file = {}
		
	}
	
	function validateSpecie(){
		if(!$scope.specie){
			$scope.specie = {}
		}
	}
	
	$scope.save = function(){
		
		 $scope.specie.sample = {
				encoded_image: $scope.demo_image_file.base64,
				picture_name: $scope.demo_image_file.filename
		 } 
		
		specieAPI.saveSpecie($scope.specie).then(function(response){
			console.log("saved !!!");
		});
	}
});