angular.module("guia").controller('indexController',function($scope, indexAPI) {

			$scope.species = [];
			$scope.filteredSpecies = [];
			$scope.currentPage = 1;
			$scope.numPerPage = 10;

			var getSpecies = function() {
				indexAPI.getSpecies().then(function(response) {
					$scope.species = response.data.species.sort(compare);
					$scope.numPages = Math.ceil($scope.species.length/ $scope.numPerPage);
					filterSpecies();
				});
			}

			function compare(a, b) {
				if (a.scientific_name < b.scientific_name)
					return -1;
				if (a.scientific_name > b.scientific_name)
					return 1;
				return 0;
			}
			;

			var filterSpecies = function(closure) {
				var begin = (($scope.currentPage - 1) * $scope.numPerPage);
				var end = begin + $scope.numPerPage;

				$scope.filteredSpecies = $scope.species.slice(begin, end);
			};

			$scope.filterSpecies = function() {
				
				if ($scope.searchCriteria === "") {
					filterSpecies();
				} else {
					$scope.filteredSpecies = $scope.species.filter(function(specie) {
													return specie.scientific_name.toLowerCase().includes($scope.searchCriteria.toLowerCase());
												});
				}

			}

			$scope.firstPage = function() {
				$scope.currentPage = 1;
				filterSpecies();
			}

			$scope.pageFoward = function() {
				$scope.currentPage = $scope.currentPage + 1;
				filterSpecies();
			}

			$scope.pageBackward = function() {
				$scope.currentPage = $scope.currentPage - 1;
				filterSpecies();
			}

			$scope.lastPage = function() {
				$scope.currentPage = $scope.numPages;
				filterSpecies();
			}

			getSpecies();

		});