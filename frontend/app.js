angular.module('sucheApp', []).controller('SuchController', ['$scope', '$http', function($scope, $http) {
    $scope.ean = '';
    $scope.artikelListe = [];

    $scope.sucheEan = function() {
        if($scope.ean.length > 2) {
            $http.get('http://localhost:8080/artikel/suche?ean=' + $scope.ean)
                .then(function(response) {
                    $scope.artikelListe = response.data;
                }, function(error) {
                    console.log('Fehler bei der Suche', error);
                });
        }
    };
}]);
