"use strict";

app.controller('HomeController', ['homeService', '$scope',
    function (homeService, $scope) {
        homeService.getCategories().then(function (response) {
            $scope.categories = response;
        })
    }
]);

app.controller('CategoryController', ['categoryService', '$routeParams', '$scope',
    function (categoryService, $routeParams, $scope) {
        $scope.category = $routeParams.category;
        categoryService.getTypesForCategory($routeParams.category)
            .then(response => {
                $scope.types = response;
            })
    }
]);

app.controller('TypeController', ['$scope', '$routeParams', 'typeService',
    function ($scope, $routeParams, typeService) {
        $scope.category = $routeParams.category;
        $scope.type = $routeParams.type;
        typeService.getItems($routeParams.type)
            .then(response => {
                $scope.items = response;
            })
    }
]);

app.controller('DetailController', ['$scope', '$routeParams', 'entityService',
    function ($scope, $routeParams, entityService) {
        $scope.category = $routeParams.category;
        $scope.type = $routeParams.type;
        $scope.text = $routeParams.text;
        entityService.get($routeParams.type, $routeParams.id)
            .then(response => {
                $scope.item = response;
            });
    }
]);

app.controller('CreateController', ['$scope', '$routeParams', 'entityService',
    function ($scope, $routeParams, entityService) {
        $scope.category = $routeParams.category;
        $scope.type = $routeParams.type;
        entityService.getFormOptions($routeParams.type)
            .then(response => {
                $scope.makers = response.makers;
                $scope.sizes = response.sizes;
            });
        document.querySelector('#file').addEventListener('change', function() {
            entityService.updatePreview(this.files[0], 'create', $scope);
        });
        $scope.create = item => {
            entityService.create($scope.category, $routeParams.type, item);
        }
    }
]);

app.controller('UpdateController', ['$scope', '$routeParams', 'entityService',
    function ($scope, $routeParams, entityService) {
        $scope.category = $routeParams.category;
        $scope.type = $routeParams.type;
        entityService.get($routeParams.type, $routeParams.id)
            .then(response => {
                $scope.item = response;
                $scope.originalName = response.name;
                $scope.originalPrice = response.price;
                $scope.originalSize = response.size.name;
                $scope.originalMaker = response.maker.name;
                // Stringify the size and maker so the page loads
                // with the current selections already showing in the drop down lists.
                $scope.item.size = JSON.stringify(response.size);
                $scope.item.maker = JSON.stringify(response.maker);
            });
        entityService.getFormOptions($routeParams.type)
            .then(response => {
                $scope.makers = response.makers;
                $scope.sizes = response.sizes;
            });
        document.querySelector('#updateFile').addEventListener('change', function() {
            entityService.updatePreview(this.files[0], 'update', $scope.item.imageUrl);
        });
        $scope.update = item => {
            entityService.update($scope.category, item);
        }
    }
]);

app.controller('SoftDeleteController', ['$scope', '$routeParams', 'entityService',
    function ($scope, $routeParams, entityService) {
        $scope.category = $routeParams.category;
        $scope.type = $routeParams.type;
        entityService.getAllNotDeleted($routeParams.type).then(response => {
            $scope.items = response;
        });
        $scope.delete = item => {
            entityService.softDelete($scope.items, item).then(response => {
                $scope.items = response;
            })
        }
    }
]);

app.controller('ErrorController', ['$scope', '$routeParams',
    function ($scope, $routeParams) {
        $scope.text = $routeParams.text;
    }
]);

app.controller('AdminController', ['adminService', '$scope',
    function (adminService, $scope) {
        adminService.getActions()
            .then(response => {
                $scope.actions = response;
            })
    }
]);

app.controller('HardDeleteController', ['adminService', 'entityService', '$routeParams', '$scope',
    function (adminService, entityService, $routeParams, $scope) {
        adminService.getTypes()
            .then(response => {
                $scope.types = response;
            });
        $scope.view = type => {
            document.getElementById('itemsToDelete').style.visibility = 'visible';
            adminService.getAllDeleted(type.name).then(response => {
                $scope.items = response;
            })
        };
        $scope.restore = item => {
            entityService.restore($scope.items, item)
                .then(response => {
                    $scope.items = response;
                })
        };
        $scope.delete = item => {
            entityService.hardDelete($scope.items, item)
                .then(response => {
                    $scope.items = response;
                })
        }
    }
]);