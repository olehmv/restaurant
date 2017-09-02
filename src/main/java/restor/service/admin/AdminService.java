package restor.service.admin;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.admin.IAdminDAO;
import restor.dto.admin.Admin;
@Component
public class AdminService implements IAdminService{
	private IAdminDAO adminDao;
	@Override
	public Admin addAdmin(Admin dto) {
		return adminDao.save(dto);
	}

	@Override
	public Admin updateAdmin(Admin dto) {
		return adminDao.update(dto);
	}

	@Override
	public Admin deleteAdmin(Admin dto) {
		return adminDao.delete(dto);
	}

	@Override
	public Admin fetchAdmin(int dto_id) {
		return adminDao.fetchAdmin(dto_id);
	}

	@Override
	public List<Admin> fetchAdmins() {
		return adminDao.fetchAdmins();
	}

}
