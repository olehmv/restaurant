package restor.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import restor.dto.admin.Admin;
import restor.dto.client.Client;
import restor.dto.item.Item;
import restor.dto.menu.Menu;
import restor.dto.order.Order;
import restor.exception.AdminNotFoundException;
import restor.exception.ClientNotFoundException;
import restor.exception.ItemNotFoundException;
import restor.exception.MenuNotFoundException;
import restor.exception.OrderNotFoundException;
import restor.exception.error.ErrorInfo;
import restor.service.admin.IAdminService;
import restor.service.client.IClientService;
import restor.service.item.IItemService;
import restor.service.menu.IMenuService;
import restor.service.order.IOrderService;

@RestController
@RequestMapping(value = "/api")
public class RestorRestController {
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IItemService itemService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IClientService clientService;
	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/menus", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Menu> showMenus() {
		List<Menu> list = menuService.fetchMenus();
		return list;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Item> showItems() {
		List<Item> list = itemService.fetchItems();
		return list;
	}

	@RequestMapping(value = "/menus/{dto_id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Menu showMenu(@PathVariable int dto_id) {
		Menu dto = menuService.fetchMenu(dto_id);
		if (dto == null) {
			throw new MenuNotFoundException();
		}
		return dto;
	}

	@RequestMapping(value = "/items/{dto_id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Item showItem(@PathVariable int dto_id) {
		Item dto = itemService.fetchItem(dto_id);
		if (dto == null) {
			throw new ItemNotFoundException();
		}
		return dto;
	}

	@RequestMapping(value = "/menus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Menu> createMenu(@RequestBody Menu dto, UriComponentsBuilder builder) {
		dto = menuService.addMenu(dto);
		Menu entity = menuService.fetchMenu(dto.getId());
		HttpHeaders header = new HttpHeaders();
		URI location = builder.path("/api/menus/").path(String.valueOf(entity.getId())).build().toUri();
		header.setLocation(location);
		ResponseEntity<Menu> response = new ResponseEntity<Menu>(entity, header, HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/items", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Item> createItem(@RequestBody Item dto, UriComponentsBuilder builder) {
		dto.setOrder_id(0);
		dto = itemService.addItem(dto);
		Item entity = itemService.fetchItem(dto.getId());
		HttpHeaders header = new HttpHeaders();
		URI location = builder.path("/api/items/").path(String.valueOf(entity.getId())).build().toUri();
		System.err.println(location.toString());
		header.setLocation(location);
		ResponseEntity<Item> response = new ResponseEntity<>(entity, header, HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/menus/{dto_id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Menu updateMenu(@PathVariable int dto_id, @RequestBody Menu dto) {
		dto.setId(dto_id);
		Menu entity = menuService.fetchMenu(dto_id);
		if (entity == null) {
			throw new MenuNotFoundException();
		}
		menuService.updateMenu(dto);
		entity = menuService.fetchMenu(dto_id);
		return entity;
	}

	@RequestMapping(value = "/items/{dto_id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Item updateItem(@PathVariable int dto_id, @RequestBody Item dto) {
		dto.setId(dto_id);
		Item entity = itemService.fetchItem(dto_id);
		if (entity == null) {
			throw new ItemNotFoundException();
		}
		dto = itemService.updateItem(dto);
		entity = itemService.fetchItem(dto_id);
		return entity;
	}

	@RequestMapping(value = "/menus/{dto_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Menu deleteMenu(@PathVariable int dto_id) {
		Menu dto = menuService.fetchMenu(dto_id);
		if (dto == null) {
			throw new MenuNotFoundException();
		}
		menuService.deleteMenu(dto);
		return null;
	}

	@RequestMapping(value = "/items/{dto_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Item deleteItem(@PathVariable int dto_id) {
		Item dto = itemService.fetchItem(dto_id);
		if (dto == null) {
			throw new ItemNotFoundException();
		} else {
			dto = itemService.deleteItem(dto);
		}
		return null;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(MenuNotFoundException.class)
	ErrorInfo handleBadRequestItem(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL(), ex);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ItemNotFoundException.class)
	ErrorInfo handleBadRequestMenu(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL(), ex);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/admins", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Admin> showAdmins() {
		List<Admin> list = adminService.fetchAdmins();
		return list;
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Client> showClients() {
		List<Client> list = clientService.fetchClients();
		return list;
	}

	@RequestMapping(value = "/admins/{dto_id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Admin showAdmin(@PathVariable int dto_id) {
		Admin dto = adminService.fetchAdmin(dto_id);
		if (dto == null) {
			throw new AdminNotFoundException();
		}
		return dto;
	}

	@RequestMapping(value = "/clients/{dto_id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Client showClient(@PathVariable int dto_id) {
		Client dto = clientService.fetchClient(dto_id);
		if (dto == null) {
			throw new ClientNotFoundException();
		}
		return dto;
	}

	@RequestMapping(value = "/admins", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin dto, UriComponentsBuilder builder) {
		Admin entity = adminService.addAdmin(dto);
		HttpHeaders header = new HttpHeaders();
		URI location = builder.path("/api/admins/").path(String.valueOf(entity.getId())).build().toUri();
		header.setLocation(location);
		ResponseEntity<Admin> response = new ResponseEntity<>(entity, header, HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/clients", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Client> createClient(@RequestBody Client dto, UriComponentsBuilder builder) {
		Client entity = clientService.addClient(dto);
		HttpHeaders header = new HttpHeaders();
		URI location = builder.path("/api/clients/").path(String.valueOf(entity.getId())).build().toUri();
		header.setLocation(location);
		ResponseEntity<Client> response = new ResponseEntity<>(entity, header, HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/admins/{dto_id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Admin updateAdmin(@PathVariable int dto_id, @RequestBody Admin dto) {
		dto.setId(dto_id);
		Admin entity = adminService.fetchAdmin(dto_id);
		if (entity == null) {
			throw new AdminNotFoundException();
		}
		entity = adminService.updateAdmin(dto);
		return entity;
	}

	@RequestMapping(value = "/clients/{dto_id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Client updateClient(@PathVariable int dto_id, @RequestBody Client dto) {
		dto.setId(dto_id);
		Client entity = clientService.fetchClient(dto_id);
		if (entity == null) {
			throw new ClientNotFoundException();
		}
		clientService.updateClient(dto);
		entity = clientService.fetchClient(dto_id);
		return entity;
	}

	@RequestMapping(value = "/admins/{dto_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Admin deleteAdmin(@PathVariable int dto_id) {
		Admin dto = adminService.fetchAdmin(dto_id);
		if (dto == null) {
			throw new AdminNotFoundException();
		}
		return adminService.deleteAdmin(dto);
	}

	@RequestMapping(value = "/clients/{dto_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Client deleteClient(@PathVariable int dto_id) {
		Client dto = clientService.fetchClient(dto_id);
		if (dto == null) {
			throw new ClientNotFoundException();
		} else {
			dto = clientService.deleteClient(dto);
		}
		return dto;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AdminNotFoundException.class)
	ErrorInfo handleBadRequestAdmin(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL(), ex);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ClientNotFoundException.class)
	ErrorInfo handleBadRequestClient(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL(), ex);
	}

	//////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Order> showOrders() {
		List<Order> list = orderService.fetchOrders();
		return list;
	}

	@RequestMapping(value = "/orders/{dto_id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Order showOrder(@PathVariable int dto_id) {
		Order dto = orderService.fetchOrder(dto_id);
		if (dto == null) {
			throw new OrderNotFoundException();
		}
		return dto;
	}

	@RequestMapping(value = "/orders", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Order> createOrder(@RequestBody Order dto, UriComponentsBuilder builder) {
		Order entity = orderService.addOrder(dto);
		HttpHeaders header = new HttpHeaders();
		URI location = builder.path("/api/clients/").path(String.valueOf(entity.getId())).build().toUri();
		header.setLocation(location);
		ResponseEntity<Order> response = new ResponseEntity<>(entity, header, HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/orders/{dto_id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Order updateOrder(@PathVariable int dto_id, @RequestBody Order dto) {
		dto.setId(dto_id);
		Order entity = orderService.fetchOrder(dto_id);
		if (entity == null) {
			throw new OrderNotFoundException();
		}
		orderService.updateOrder(dto);
		return orderService.fetchOrder(dto_id);
	}

	@RequestMapping(value = "/orders/{dto_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Order deleteOrder(@PathVariable int dto_id) {
		Order dto = orderService.fetchOrder(dto_id);
		if (dto == null) {
			throw new OrderNotFoundException();
		} else {
			dto = orderService.deleteOrder(dto);
		}
		return dto;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(OrderNotFoundException.class)
	ErrorInfo handleBadRequestOrder(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL(), ex);
	}
}
