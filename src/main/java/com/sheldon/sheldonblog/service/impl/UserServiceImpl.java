package com.sheldon.sheldonblog.service.impl;

import com.sheldon.sheldonblog.consts.SessionConstants;
import com.sheldon.sheldonblog.entity.User;
import com.sheldon.sheldonblog.entity.dto.form.UserLoginForm;
import com.sheldon.sheldonblog.entity.dto.form.UserRegisterForm;
import com.sheldon.sheldonblog.dao.UserMapper;
import com.sheldon.sheldonblog.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户信息操作业务实现类
 *
 * @author James
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper mMapper;

  @Override
  public User loginAuthentication(UserLoginForm loginForm) {

    User loginUser = new User();
    loginUser.setUsername(loginForm.getUsername());
    loginUser.setPassword(DigestUtils.md5Hex(loginForm.getPassword()));
    List<User> userList = mMapper.select(loginUser);
    if (null != userList && userList.size() == 1) {
      return userList.get(0);
    }
    return null;
  }

  @Override
  public boolean registerUsernameCheckExist(UserRegisterForm registerForm) {
    User checkUser = new User();
    checkUser.setUsername(registerForm.getUsername());
    List<User> listRegistedUser = mMapper.select(checkUser);
    return listRegistedUser.size() > 0;
  }

  @Override
  public void insertUser(User user) {
    String pwdStr = user.getPassword();
    user.setPassword(DigestUtils.md5Hex(pwdStr));
    mMapper.insertSelective(user);
  }

  @Override
  public void joinSession(HttpServletRequest request, User user) {
    HttpSession requestSession = request.getSession(true);
    requestSession.setAttribute(SessionConstants.SESSION_CURRENT_USER, user);
  }

  @Override
  public void destroySession(HttpServletRequest request) {
    HttpSession requestSession = request.getSession(true);
    requestSession.removeAttribute(SessionConstants.SESSION_CURRENT_USER);
  }
}
