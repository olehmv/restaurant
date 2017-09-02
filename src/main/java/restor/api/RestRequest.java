package restor.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import restor.dao.admin.IAdminDAO;
import restor.dto.menu.Menu;
import restor.service.admin.IAdminService;
import restor.service.client.IClientService;
import restor.service.item.IItemService;
import restor.service.menu.IMenuService;
import restor.service.order.IOrderService;

class RestRequest {
	private Pattern menus  = Pattern.compile("/menus");
	private Pattern menu = Pattern.compile("/menus/([0-9])");
	private Pattern items = Pattern.compile("/items");
	private Pattern item = Pattern.compile("/items/([0-9])");
	private Pattern admins = Pattern.compile("/admins");
	private Pattern admin = Pattern.compile("/admins/([0-9])");
	private Pattern clients = Pattern.compile("/clients");
	private Pattern client = Pattern.compile("/clients/([0-9])");
	private Pattern orders = Pattern.compile("/orders");
	private Pattern order = Pattern.compile("/orders/([0-9])");
	private IMenuService mServ;
	private IItemService iServ;
	private IAdminService aServ;
	private IClientService cServ;
	private IOrderService oServ;

	private Integer id;

	public RestRequest(String pathInfo) throws ServletException {
		// regex parse pathInfo
		Matcher matcher;

		// Check for ID case first, since the All pattern would also match
		matcher = menu.matcher(pathInfo);
		if (matcher.find()) {
			id = Integer.parseInt(matcher.group(1));
			Menu fetchMenu = mServ.fetchMenu(id);
//			return fetchMenu;
		}

		matcher = menus.matcher(pathInfo);
		if (matcher.find())
			return ;

		throw new ServletException("Invalid URI");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}