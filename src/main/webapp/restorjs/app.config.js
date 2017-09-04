'use strict';

angular.module('restorApp').config(
		function config($locationProvider, $routeProvider) {
			$locationProvider.hashPrefix('!');

			$routeProvider.when('/menus', {
				template : '<menus-list></menus-list>'
					
			}).when('/menus/:id', {
				template : '<menus-detail></menus-detail>'
			}).when('/admins', {
				template : '<admin-control></admin-control>'
						
			}).when('/menus/:id/items', {
				template : '<menu-items></menu-items>'
					
			}).when('/clients', {
				template : '<clients-list></clients-list>'
					
			}).when('/clients/:id', {
				template : '<client-detail></client-detail>'
			
			}).when('/clients/:id/orders', {
				template : '<client-orders></client-orders>'
					
			}).when('/admins', {
				template : '<admin-control></admin-control>'
					
			}).when('/admins/:id', {
				template : '<admin-detail></admin-detail>'
					
			}).when('/admins/:id/orders', {
				template : '<admin-orders></admin-orders>'
					
			}).when('/orders', {
				template : '<orders-list></orders-list>'
					
			}).when('/orders/:id', {
				template : '<order-detail></order-detail>'
					
			}).when('/orders/:id/items', {
				template : '<order-items></order-items>'

			
			}).otherwise('/admins');

		});
