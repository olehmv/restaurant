package restor.service.admin;

import java.util.List;

import restor.dto.admin.Admin;

public interface IAdminService {
	Admin addAdmin(Admin dto);
	Admin updateAdmin(Admin dto);
	Admin deleteAdmin(Admin dto);
	Admin fetchAdmin(int dto_id);
	List<Admin>fetchAdmins();

}
