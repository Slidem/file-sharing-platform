// package com.file.sharing.rest;
//
// import java.util.List;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpStatus;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.file.sharing.common.dto.UserDTO;
// import com.file.sharing.exceptions.FileSharingException;
//
/// **
// * @author Alexandru
// *
// */
// @RestController
// @RequestMapping("/users")
// public class UserRestController {
//
// @Value("${jwt.header}")
// private String tokenHeader;
//
// private JwtTokenUtil jwtTokenUtil;
//
// private UserDetailsService userDetailsService;
//
// private UserService userService;
//
// private RequestValidator requestValidator;
//
// /**
// * @param jwtTokenUtil
// * @param userDetailsService
// * @param userService
// * @param requestValidator
// */
// @Autowired
// public UserRestController(JwtTokenUtil jwtTokenUtil, UserDetailsService
// userDetailsService, UserService userService,
// RequestValidator requestValidator) {
// this.jwtTokenUtil = jwtTokenUtil;
// this.userDetailsService = userDetailsService;
// this.userService = userService;
// this.requestValidator = requestValidator;
// }
//
// /**
// * @param request
// * @return
// */
// @RequestMapping(value = "/jwt", method = RequestMethod.GET)
// @ResponseStatus(code = HttpStatus.OK)
// public JwtUser getAuthenticatedUser(HttpServletRequest request) {
// String token = request.getHeader(tokenHeader);
// String username = jwtTokenUtil.getUsernameFromToken(token);
// JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
// return user;
// }
//
// /**
// * @return
// */
// @RequestMapping(method = RequestMethod.POST)
// @ResponseStatus(code = HttpStatus.OK)
// public void createNewUser(@RequestBody UserDTO userDTO) {
// userService.saveUser(userDTO);
// }
//
// /**
// * @return
// */
// @RequestMapping(value = "/list", method = RequestMethod.GET)
// @ResponseStatus(code = HttpStatus.OK)
// public List<UserDTO> listAllUsers() {
// List<UserDTO> users = userService.getUsers();
// if (users.isEmpty()) {
// throw new UsernameNotFoundException("No users found in the DB");
// }
// return users;
// }
//
// /**
// * @param email
// * @return
// */
// @RequestMapping(method = RequestMethod.GET)
// @ResponseStatus(code = HttpStatus.OK)
// public UserDTO getUserByEmail(@RequestParam(value = "email", required = true)
// String email) {
// if (!requestValidator.isValidEmail(email)) {
// throw new FileSharingException(HttpServletResponse.SC_BAD_REQUEST);
// }
// return userService.findUserByEmail(email);
// }
//
// }
