'use strict';

angular.module('restor').factory('Admin', [ '$resource', function($resource) {
	return $resource('http://localhost:8080/restor/api/admins/:id', {
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
