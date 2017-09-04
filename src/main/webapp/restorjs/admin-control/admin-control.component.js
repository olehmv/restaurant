/**
 * 
 */
angular
		.module('adminControl')
		.component(
				'adminControl',
				{
					templateUrl : '/restor/restorjs/admin-control/admin-control.template.html',
					controller : [
							'$routeParams',
							'Order',
							'Admin',
							'Client',
							'Menu',
							'$scope',
							'Item',
							function AdminDetailController($routeParams, Order,
									Admin, Client, Menu, $scope, Item) {
								var self = this;
								self.admins = Admin.query();
								$scope.items = [];
								self.addItem = function() {
									self.admin.order.active = true;
									self.admin.order.admin_id = self.admin.id;
									self.admin.order.client_id = self.newClient.id;
									$scope.items.push(self.admin.order.item);
									self.admin.order.orderItems = $scope.items;
									self.admin.order.item = {};
								}
								self.deleteItem = function(item) {
									for (var i = self.admin.order.orderItems.length; i--;) {
										if (self.admin.order.orderItems[i] === item) {
											self.admin.order.orderItems.splice(i, 1);
										}
									}
								}
								self.submit = function() {
									self.admin.order.active = true;
									self.admin.order.admin_id = self.admin.id;
									self.admin.order.client_id = self.newClient.id;
									Order.save(self.admin.order,
											function(data) {
												self.admin.newOrder = data;
											});
									self.admin.order={};
									$scope.items=[];
								}

								self.menus = Menu.query();

								self.addClient = function() {
									Client.save(self.client, function(data) {
										self.newClient = data;
										self.client = {};
									});

								}

								self.menus = Menu.query();

							} ]
				});
