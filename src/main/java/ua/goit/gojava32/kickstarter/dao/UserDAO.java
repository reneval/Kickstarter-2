package ua.goit.gojava32.kickstarter.dao;

import ua.goit.gojava32.kickstarter.model.User;
import ua.goit.gojava32.kickstarter.service.GenericCRUDService;

public interface UserDAO extends GenericCRUDService{
  void add(User user);
  User findUserByToken(String token);
}