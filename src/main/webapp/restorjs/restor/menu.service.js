'use strict';

angular.module('restor').factory(
		'Menu',
		[
				'$resource',
				function($resource) {
					return $resource(
							'http://localhost:8080/restor/api/menus/:id', {
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
								}
							});

				} ]);
