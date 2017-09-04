'use strict';

angular.module('restor').factory('Item', [ '$resource', function($resource) {
	return $resource('http://localhost:8080/restor/api/items/:id', {
		id : '@id'
	}, {
		'get' : {
			method : 'GET'
		},
		'save' : {
			method : 'POST'
		},
		'query' : {
			method : 'GET',
			isArray : true
		},
		'remove' : {
			method : 'DELETE'
		},
		'delete' : {
			method : 'DELETE'
		},

		'update' : {
			method : 'PUT'
		}

	});

} ]);
