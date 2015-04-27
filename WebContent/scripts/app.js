angular.module('app', []).controller('AutoCompleteCrtl', AutoCompleteCrtl);

AutoCompleteCrtl.$inject = [ '$scope', '$http' ];

function AutoCompleteCrtl($scope, $http) {
	$scope.pesquisar = function(pesquisa) {
		if (pesquisa == "") {
			$scope.completing = false;
		} else {
			$http
					.get('api?api=TituloAutoComplente&q=' + pesquisa)
					.success(function(data) {
						$scope.completing = true;
						$scope.dicas = data;
					})
					.error(
							function(data) {
								console
										.log("Ocorreu um erro no banco de dados ao trazer auto-ajuda da home");
							});
		}

		$scope.addValueSearch = function(dica) {
			$scope.search = dica.titulo;
			$scope.dicas = [];
		}
	};
}