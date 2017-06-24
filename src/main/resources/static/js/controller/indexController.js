angular.module("guia").controller('indexController',function($scope, indexAPI) {

			$scope.species = [];
			$scope.filteredSpecies = [];
			$scope.currentPage = 1;
			$scope.numPerPage = 10;

			const getSpecies = () => {
				indexAPI.getSpecies().then((response) => {
					$scope.species = response.data.species.sort(compare);
					$scope.numPages = Math.ceil($scope.species.length/ $scope.numPerPage);
					filterSpecies();
				});
			}

			const compare = (a, b) => {
				if (a.scientific_name < b.scientific_name)
					return -1;
				if (a.scientific_name > b.scientific_name)
					return 1;
				return 0;
			}
			

			const filterSpecies = (closure) => {
				var begin = (($scope.currentPage - 1) * $scope.numPerPage);
				var end = begin + $scope.numPerPage;

				$scope.filteredSpecies = $scope.species.slice(begin, end);
			};

			$scope.filterSpecies = () => {
				
				if ($scope.searchCriteria === '') {
					filterSpecies();
				} else {
					$scope.filteredSpecies = $scope.species.filter( (specie) => {
													return specie.scientific_name.toLowerCase().includes($scope.searchCriteria.toLowerCase());
												});
				}

			}

			$scope.firstPage = () => {
				$scope.currentPage = 1;
				filterSpecies();
			}

			$scope.pageFoward = () => {
				$scope.currentPage = $scope.currentPage + 1;
				filterSpecies();
			}

			$scope.pageBackward = () => {
				$scope.currentPage = $scope.currentPage - 1;
				filterSpecies();
			}

			$scope.lastPage = () => {
				$scope.currentPage = $scope.numPages;
				filterSpecies();
			}

			getSpecies();

		});