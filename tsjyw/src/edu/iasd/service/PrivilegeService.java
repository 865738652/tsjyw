package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iasd.form.AdminMenuVO;

public interface PrivilegeService {
	public List<AdminMenuVO> getAdminMenuByUser(Integer userId);
}
