"use strict";
const fileByteLimit = 3000000;  // Maximum file size in MB for uploading files, this should match the figure in application.properties.
const fileSizeLimitMessage = "The file size limit is " + fileByteLimit / 1000000 + "MB, please chose a smaller file to upload."

app.service('homeService', ['$http', function ($http) {
    this.getCategories = function () {
        return $http.get('view')
            .then(successCallback => {
                return successCallback.data;
            })
            .catch(errorCallback => {
                let text = errorCallback.status + ' error: no inventory categories were found.';
                return window.location = '#!/error/' + text;
            })
    }
}])

app.service('categoryService', ['$http', function ($http) {
    this.getTypesForCategory = function (name) {
        return $http.get('view/category/?categoryName=' + name)
            .then(successCallback => {
                return successCallback.data;
            })
            .catch(errorCallback => {
                let text = errorCallback.status + ' error: Category "' + name + '" was not found.';
                return window.location = '#!/error/' + text;
            })
    }
}])

app.service('typeService', ['$http', function ($http) {
    this.getItems = function (type) {
        return $http.get('/view/type/?typeName=' + type)
            .then(successCallback => {
                if (successCallback.data.length === 0) {
                    document.querySelector('#message').innerText =
                        'There are no ' + type + ' available.';
                }
                return successCallback.data;
            })
            .catch(errorCallback => {
                let text = errorCallback.status + ' error: Type ' + type + ' was not found in the database.';
                return window.location = '#!/error/' + text;
            })
    }
}])

app.service('entityService', ['$http', function ($http) {

    function unauthorisedAccessResponse() {
        let text = 'You are not authorised to access the maker pages.'
        return window.location = '#!/error/' + text;
    }

    function prepareFormData(item, image) {
        item.size = JSON.parse(item.size);
        item.maker = JSON.parse(item.maker);
        let formData = new FormData();
        formData.append('data', JSON.stringify(item));
        if (image != null) {
            formData.append('image', new Blob([image]));
        }
        return formData;
    }

    function prepareDataWithChangedDeleteStatus(item, deleteStatus) {
        // The item receives a $$hashKey value when it comes from the server as part of Iterable<>.
        // This needs to be removed so the item can be read by the server.
        delete item.$$hashKey;
        item.deleted = deleteStatus;
        let formData = new FormData();
        formData.append('data', JSON.stringify(item));
        return formData;
    }

    this.getFormOptions = function (type) {
        return $http.get('form/?type=' + type)
            .then(successCallback => {
                return successCallback.data;
            })
            .catch(errorCallback => {
                if (errorCallback.status === 403) {
                    unauthorisedAccessResponse();
                } else {
                    let text = errorCallback.status + ' error: type "' + type + '" is not recognised, cannot provide form options.';
                    return window.location = '#!/error/' + text;
                }
            })
    };

    this.create = function (category, type, entity) {
        $http.post(type + '/create', prepareFormData(entity, document.getElementById('file').files[0]), {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .then(successCallback => {
                return window.location = '#!/category/' + category + '/type/' + type + '/' + successCallback.data + '/Successfully created:';
            })
            .catch(errorCallback => {
                if (errorCallback.status === 403) {
                    unauthorisedAccessResponse();
                } else {
                    let text = errorCallback.status + ' error: the requested item could not be added to the database.';
                    return window.location = '#!/error/' + text;
                }
            })
    };

    this.updatePreview = function (file, action, imageUrl) {
        if (file && file.size < fileByteLimit) {
            document.querySelector('#preview').setAttribute('src', URL.createObjectURL(file));
            document.querySelector('#preview').style.visibility = 'visible';
        } else if (file && file.size >= fileByteLimit) {
            alert(fileSizeLimitMessage)
        } else {
            if (action === 'create') {
                document.querySelector('#preview').style.visibility = 'hidden';
            } else if (action === 'update') {
                document.querySelector('#preview').setAttribute('src', imageUrl);
            }
        }
    };

    this.get = function (type, id) {
        return $http.get('/view/details/' + type + '/?id=' + id)
            .then(successCallback => {
                return successCallback.data;
            })
            .catch(errorCallback => {
                let text = errorCallback.status + ' error: the requested item could not be found.';
                return window.location = '#!/error/' + text;
            })
    };

    this.update = function (category, item) {
        let image = null;
        if (document.getElementById('updateFile').files[0]) {
            image = document.getElementById('updateFile').files[0];
        }
        $http.put(item.type.name + '/update/', prepareFormData(item, image), {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .then(successCallback => {
                return window.location = '#!/category/' + category + '/type/' + item.type.name + '/'
                    + successCallback.data + '/Successfully updated: ';
            })
            .catch(errorCallback => {
                if (errorCallback.status === 403) {
                    unauthorisedAccessResponse();
                } else {
                    let text = errorCallback.status + ' error: the requested update failed.';
                    return window.location = '#!/error/' + text;
                }
            })
    };

    this.getAllNotDeleted = function (type) {
        return $http.get(type + '/remove')
            .then(successCallback => {
                if (successCallback.data.length === 0) {
                    document.getElementById('message').innerText =
                        'There are no ' + type + ' available.';
                }
                return successCallback.data;
            })
            .catch(errorCallback => {
                if (errorCallback.status === 403) {
                    unauthorisedAccessResponse();
                } else {
                    let text = errorCallback.status + ' error: "' + type + '" could not be found.';
                    return window.location = '#!/error/' + text;
                }
            })
    };

    function removeItem(items, index, text) {
        items.splice(index, 1)
        if (items.length === 0) {
            document.querySelector('#message').innerText = text;
        }
        return items;
    }

    this.softDelete = function (items, item) {
        let type = item.type.name;
        return $http.put(item.type.name + '/update/', prepareDataWithChangedDeleteStatus(item, true), {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .then(() => {
                let text = 'There are no ' + type + ' available.';
                removeItem(items, items.indexOf(item), text);
                return items;
            })
            .catch(errorCallback => {
                let text = errorCallback.status + ' error: the item was not deleted successfully.';
                return window.location = '#!/error/' + text;
            })
    };

    this.hardDelete = function (items, item) {
        return $http.delete(item.type.name + '/delete/', {
            data: item,
            headers: {'Content-Type': 'application/json;charset=utf-8'}
        })
            .then(() => {
                let text = 'There are no deleted ' + item.type.name + ' in the database.';
                removeItem(items, items.indexOf(item), text);
                return items;
            })
            .catch(errorCallback => {
                let text = errorCallback.status + ' error: the item was not deleted successfully.';
                return window.location = '#!/error/' + text;
            })
    };

    this.restore = function (items, item) {
        return $http.put(item.type.name + '/restore/', prepareDataWithChangedDeleteStatus(item, false), {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .then(() => {
                items.splice(items.indexOf(item), 1)
                let text = 'There are no deleted ' + item.type.name + ' in the database.';
                removeItem(items, items.indexOf(item), text);
                return items;
            })
            .catch(errorCallback => {
                let text = errorCallback.status + ' error: the item was not restored successfully.';
                return window.location = '#!/error/' + text;
            })
    };

}])

app.service('adminService', ['$http', function ($http) {

    function unauthorisedAccessResponse() {
        let text = 'You are not authorised to access the administrative pages.'
        return window.location = '#!/error/' + text;
    }

    this.getActions = function () {
        return $http.get('/admin')
            .then(successCallback => {
                return successCallback.data;
            })
            .catch(errorCallback => {
                if (errorCallback.status === 403) {
                    unauthorisedAccessResponse('administrative');
                } else {
                    let text = errorCallback.status + ' error: sorry, we could not retrieve the admin options at this time.';
                    return window.location = '#!/error/' + text;
                }
            })
    };

    this.getTypes = function () {
        return $http.get('/admin/types')
            .then(successCallback => {
                return successCallback.data;
            })
            .catch(errorCallback => {
                if (errorCallback.status === 403) {
                    unauthorisedAccessResponse('administrative');
                } else {
                    let text = errorCallback.status + ' error: No types were found in the database.';
                    return window.location = '#!/error/' + text;
                }
            })
    };

    this.getAllDeleted = function (type) {
        return $http.get('/admin/deletions/?type=' + type)
            .then(successCallback => {
                if (successCallback.data.length === 0) {
                    document.getElementById('message').innerText =
                        'There are currently no deleted ' + type + ' in the database.';
                } else {
                    document.getElementById('message').innerText =
                        'Showing ' + type + ' marked for deletion:';
                }
                return successCallback.data;
            })
            .catch(errorCallback => {
                let text = errorCallback.status + ' error: "' + type + '" could not be found.';
                return window.location = '#!/error/' + text;
            })
    };

}])