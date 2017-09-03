package restor.service.admin;

import java.util.List;

import restor.dto.admin.Admin;
import restor.dto.order.Order;

public interface IAdminService {
	Admin addAdmin(Admin dto);

	Admin updateAdmin(Admin dto);

	Admin deleteAdmin(Admin dto);

	Admin fetchAdmin(int dto_id);

	List<Admin> fetchAdmins();

	List<Order> fetchOrders(int dto_id);
}
