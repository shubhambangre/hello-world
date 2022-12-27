/*
 * package ai.rnt.main.dao;
 * 
 * import java.beans.PropertyVetoException; import java.io.ByteArrayInputStream;
 * import java.io.File; import java.io.FileOutputStream; import
 * java.io.IOException; import java.sql.Blob; import java.sql.Connection; import
 * java.sql.PreparedStatement; import java.sql.ResultSet; import
 * java.sql.SQLException; import java.sql.Statement; import
 * java.text.DateFormat; import java.text.SimpleDateFormat; import
 * java.util.ArrayList; import java.util.HashMap;
 * 
 * import org.apache.logging.log4j.LogManager; import
 * org.apache.logging.log4j.Logger; import ai.rnt.lms.model.User; import
 * ai.rnt.main.model.RBAC; import ai.rnt.main.util.DBConnectionHandlerUtil;
 * import ai.rnt.rms.model.Profile;
 * 
 * public class MainLogInDao {
 * 
 * private static final Logger log = LogManager.getLogger(MainLogInDao.class);
 * 
 * 
 * public User getEmployeeDetails(String userID) throws SQLException,
 * PropertyVetoException { // Employee check User user = null; ResultSet rs =
 * null; Connection connection = null; Statement stmt = null; DateFormat df =
 * new SimpleDateFormat("dd-MM-yyyy"); try { connection =
 * DBConnect.getConnection(); stmt = connection.createStatement(); StringBuffer
 * queryString = new StringBuffer(); queryString.
 * append("SELECT Staff_ID, password, F_name, M_Name, L_Name, Manager_ID, email_ID ,role_id,Joining_Date FROM employee_master WHERE User_ID='"
 * ); queryString.append(userID + "'"); rs =
 * stmt.executeQuery(queryString.toString());
 * 
 * // interacting and check in condition if (rs.next()) { user = new User();
 * user.setStaffID(rs.getInt(1)); user.setPassword(rs.getString(2));
 * user.setFirstName(rs.getString(3)); user.setMiddleName(rs.getString(4));
 * user.setLastName(rs.getString(5)); user.setManagerID(rs.getInt(6));
 * user.setEmailID(rs.getString(7)); user.setRoleId(rs.getInt(8)); String
 * textDtae = df.format(rs.getDate(9)); user.setjDate(textDtae);
 * user.setUserID(userID);
 * 
 * } } finally { DBConnectionHandlerUtil.closeDatabaseResource(rs,stmt,
 * connection); } return user;
 * 
 * }
 * 
 * // method for admin check public boolean isValidAdmin(String staffID) throws
 * SQLException, PropertyVetoException {
 * 
 * boolean isAdmin = false; ResultSet rs = null; Connection connection = null;
 * Statement statement = null; try { connection = DBConnect.getConnection();
 * statement = connection.createStatement(); StringBuffer queryString = new
 * StringBuffer();
 * queryString.append("select count(*) from admin_info where User_ID='");
 * queryString.append(staffID + "'"); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) isAdmin = true; } } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return isAdmin; }
 * 
 * // method for admin check
 * 
 * public boolean isValidAdmin1(int staffID) throws SQLException,
 * PropertyVetoException {
 * 
 * boolean isAdmin = false; ResultSet rs = null; Connection connection = null;
 * Statement statement = null; try { connection = DBConnect.getConnection();
 * statement = connection.createStatement(); StringBuffer queryString = new
 * StringBuffer();
 * queryString.append("select count(*) from admin_info where Staff_ID= ");
 * queryString.append(staffID); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) isAdmin = true;
 * 
 * } } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * 
 * return isAdmin; }
 * 
 * // method for valid Manager
 * 
 * 
 * public boolean isValidManager(int staffID) throws SQLException,
 * PropertyVetoException { // Manager check boolean isManager = false; ResultSet
 * rs = null; StringBuffer queryString = new StringBuffer(); queryString.
 * append("select count(*) from employee_master where manager_ID = ");
 * queryString.append(staffID); Connection connection
 * =DBConnect.getConnection(); Statement statement
 * =connection.createStatement(); try { rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) { isManager = true; } } } finally { rs.close();
 * connection.close(); statement.close(); }
 * 
 * return isManager; }
 * 
 * 
 * public boolean isValidMan(int staffId) throws SQLException,
 * PropertyVetoException { // Manager check boolean isManager = false;
 * 
 * ResultSet rs = null; StringBuffer queryString = new StringBuffer();
 * queryString.append("select count(*) from employee_master where manager_ID ="
 * ); queryString.append(staffId); Connection connection = null; Statement
 * statement = null; try { connection = DBConnect.getConnection(); statement =
 * connection.createStatement(); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) { isManager = true; log.info("he is manager"); } } }
 * finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection);
 * 
 * }
 * 
 * return isManager; }
 * 
 * // valid manager by user_id
 * 
 * public boolean isValidManager(User user) throws SQLException,
 * PropertyVetoException { // Manager check boolean isManager = false;
 * 
 * ResultSet rs = null; StringBuffer queryString = new StringBuffer();
 * queryString.append("select count(*) from employee_master where manager_ID ="
 * ); queryString.append(user.getStaffID()); Connection connection = null;
 * Statement statement = null; try { connection = DBConnect.getConnection();
 * statement = connection.createStatement(); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) { isManager = true; log.info("he is manager"); } } }
 * finally { DBConnectionHandlerUtil.closeDatabaseResource(rs,statement,
 * connection);
 * 
 * }
 * 
 * return isManager; }
 * 
 * public String getProfilePicture(int staffID, String path) throws
 * SQLException, PropertyVetoException, IOException { // Employee
 * 
 * ResultSet rs = null;
 * 
 * String imgPath = ""; Connection connection = null; Statement statement =
 * null; try { connection = DBConnect.getConnection(); statement =
 * connection.createStatement(); StringBuffer queryString = new StringBuffer();
 * 
 * queryString
 * .append("SELECT ep.Profile_Picture FROM employee_master AS em INNER JOIN Employee_Profile AS ep "
 * ); queryString.append(" WHERE em.Staff_ID=ep.Profile_ID AND em.Staff_ID='");
 * queryString.append(staffID);
 * 
 * rs = statement.executeQuery(queryString.toString()); while (rs.next()) { Blob
 * blob = rs.getBlob(1); if (blob != null) { byte barr[] = blob.getBytes(1,
 * (int) blob.length());// 1 means first image String URL = path + staffID +
 * ".jpg"; File file = new File(URL); FileOutputStream fos = new
 * FileOutputStream(file); fos.write(barr); fos.close(); imgPath =
 * "/rms/images/" + staffID + ".jpg"; } else { imgPath =
 * "/rms/images/avatar-1.jpg"; } } } finally {
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection);
 * 
 * } return imgPath; }
 * 
 * public ArrayList<User> getStaffID() throws SQLException,
 * PropertyVetoException {
 * 
 * User user = null; ArrayList<User> userList = new ArrayList<User>();
 * 
 * ResultSet rs = null; PreparedStatement statement = null; StringBuffer
 * queryString = new StringBuffer(); queryString.
 * append(" SELECT staff_ID ,f_Name,l_Name ,User_ID from employee_master ");
 * queryString.append(" WHERE date_of_departure is null ORDER BY F_Name asc");
 * Connection connection =null;
 * 
 * try { connection = DBConnect.getConnection(); statement =
 * connection.prepareStatement(queryString.toString()); rs =
 * statement.executeQuery();
 * 
 * while (rs.next()) { user = new User(); user.setStaffID(rs.getInt(1));
 * user.setStaffName(rs.getString(2) + " " + rs.getString(3));
 * user.setUserID(rs.getString(4)); userList.add(user);
 * 
 * }
 * 
 * } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return userList; }
 * 
 * 
 * public ArrayList<User> getEmpStaffID() throws SQLException,
 * PropertyVetoException {
 * 
 * User user = null; ArrayList<User> userList = new ArrayList<User>();
 * 
 * ResultSet rs = null; PreparedStatement statement = null; StringBuffer
 * queryString = new StringBuffer(); queryString.
 * append(" SELECT staff_ID ,f_Name,l_Name ,User_ID from employee_master ");
 * queryString.append(" WHERE date_of_departure is not null ORDER BY F_Name asc"
 * ); Connection connection =null;
 * 
 * try { connection = DBConnect.getConnection(); statement =
 * connection.prepareStatement(queryString.toString()); rs =
 * statement.executeQuery();
 * 
 * while (rs.next()) { user = new User(); user.setStaffID(rs.getInt(1));
 * user.setStaffName(rs.getString(2) + " " + rs.getString(3));
 * user.setUserID(rs.getString(4)); userList.add(user);
 * 
 * }
 * 
 * } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return userList; }
 * 
 * public HashMap<Integer, String> getRoleList(int userId, User user) throws
 * SQLException, PropertyVetoException {
 * 
 * ResultSet rs = null; Connection connection = null; PreparedStatement stmt =
 * null;
 * 
 * HashMap<Integer, String> map = new HashMap<Integer, String>();
 * 
 * try { StringBuffer queryString = new StringBuffer();
 * queryString.append(" SELECT ur.role_id, rm.role ");
 * queryString.append(" FROM user_role AS ur,");
 * queryString.append(" role_master AS rm");
 * queryString.append(" WHERE ur.user_id = ?");
 * queryString.append(" AND ur.role_id = rm.role_id");
 * queryString.append(" AND ur.deleted_by IS NULL");
 * queryString.append(" AND rm.deleted_by IS NULL");
 * queryString.append(" ORDER BY ur.role_id DESC ");
 * 
 * connection = DBConnect.getConnection(); stmt =
 * connection.prepareStatement(queryString.toString()); stmt.setInt(1, userId);
 * 
 * rs = stmt.executeQuery(); while (rs.next()) { user.setRole(rs.getString(2));
 * user.setRoleId(rs.getInt(1));
 * 
 * map.put(rs.getInt(1), rs.getString(2)); } } catch (Exception e) {
 * log.error("Got Exception while fetching User Details" + e); } finally {
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,stmt, connection);
 * 
 * } return map; }
 * 
 * public HashMap<String, RBAC> getRoleAccessList(int staffID) throws
 * SQLException, PropertyVetoException {
 * 
 * ResultSet rs = null; Connection connection = null; PreparedStatement stmt =
 * null; RBAC rbac = null; HashMap<String, RBAC> map = new HashMap<String,
 * RBAC>();
 * 
 * try { StringBuffer queryString = new StringBuffer();
 * queryString.append(" SELECT use_case_name,");
 * queryString.append(" read_access,"); queryString.append(" write_access,");
 * queryString.append(" edit_access,"); queryString.append(" delete_access");
 * queryString.append(" FROM rbac_master r, use_cases u,user_role ur ");
 * queryString.append(" WHERE ur.user_id = ? ");
 * queryString.append(" AND ur.role_id=r.role_id ");
 * queryString.append(" AND r.use_case_id = u.use_case_id");
 * queryString.append(" AND r.deleted_by IS NULL");
 * queryString.append(" AND u.deleted_by IS NULL");
 * 
 * connection = DBConnect.getConnection(); stmt =
 * connection.prepareStatement(queryString.toString()); stmt.setInt(1, staffID);
 * rs = stmt.executeQuery();
 * 
 * while (rs.next()) { rbac = new RBAC(); rbac.setUseCase(rs.getString(1));
 * rbac.setWriteAccess(rs.getString(2)); rbac.setReadAccess(rs.getString(3));
 * rbac.setEditAccess(rs.getString(4)); rbac.setDeleteAccess(rs.getString(5));
 * 
 * if (rbac.getReadAccess().equals("Y") || rbac.getWriteAccess().equals("Y") ||
 * rbac.getEditAccess().equals("Y") || rbac.getDeleteAccess().equals("Y")) {
 * 
 * rbac.setModuleAccess(true); } else { rbac.setModuleAccess(false); }
 * 
 * map.put(rs.getString(1).trim(), rbac); } } catch (Exception e) {
 * log.error("Got Exception while fetching User Details" + e); } finally {
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,stmt, connection); } return
 * map; }
 * 
 * 
 * public ArrayList< RBAC> getRoleAccessListOfEmployee(int staffID) throws
 * SQLException, PropertyVetoException {
 * 
 * ResultSet rs = null; Connection connection = null; PreparedStatement stmt =
 * null; RBAC rbac = null; ArrayList<RBAC> map = new ArrayList<RBAC>();
 * 
 * try { StringBuffer queryString = new StringBuffer();
 * queryString.append(" SELECT use_case_name,");
 * queryString.append(" read_access,"); queryString.append(" write_access,");
 * queryString.append(" edit_access,"); queryString.append(" delete_access");
 * queryString.append(" FROM rbac_master r, use_cases u,user_role ur ");
 * queryString.append(" WHERE ur.user_id = ? "); queryString.
 * append(" AND (r.read_access='Y' AND r.write_access ='Y' AND r.delete_access ='Y' AND r.edit_access ='Y') "
 * ); queryString.append(" AND ur.role_id=r.role_id ");
 * queryString.append(" AND r.use_case_id = u.use_case_id");
 * queryString.append(" AND r.deleted_by IS NULL");
 * queryString.append(" AND u.deleted_by IS NULL");
 * 
 * connection = DBConnect.getConnection(); stmt =
 * connection.prepareStatement(queryString.toString()); stmt.setInt(1, staffID);
 * rs = stmt.executeQuery();
 * 
 * while (rs.next()) { rbac = new RBAC(); rbac.setUseCase(rs.getString(1));
 * rbac.setWriteAccess(rs.getString(2)); rbac.setReadAccess(rs.getString(3));
 * rbac.setEditAccess(rs.getString(4)); rbac.setDeleteAccess(rs.getString(5));
 * 
 * if (rbac.getReadAccess().equals("Y") || rbac.getWriteAccess().equals("Y") ||
 * rbac.getEditAccess().equals("Y") || rbac.getDeleteAccess().equals("Y")) {
 * 
 * rbac.setModuleAccess(true); } else { rbac.setModuleAccess(false); }
 * 
 * map.add(rbac); } } catch (Exception e) {
 * log.error("Got Exception while fetching User Details" + e); } finally {
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,stmt, connection); } return
 * map; }
 * 
 * public boolean checkUserAccess(int userId, int roleId) throws SQLException,
 * PropertyVetoException { ResultSet rs = null; Connection connection = null;
 * PreparedStatement stmt = null; boolean isAccess = false;
 * 
 * try { StringBuffer queryString = new StringBuffer();
 * queryString.append(" SELECT COUNT(*)");
 * queryString.append(" FROM user_role");
 * queryString.append(" WHERE user_id = ?");
 * queryString.append(" AND role_id = ?");
 * queryString.append(" AND deleted_by IS NULL");
 * 
 * connection = DBConnect.getConnection(); stmt =
 * connection.prepareStatement(queryString.toString()); stmt.setInt(1, userId);
 * stmt.setInt(2, roleId);
 * 
 * rs = stmt.executeQuery(); while (rs.next()) { if (rs.getInt(1) > 0) {
 * isAccess = true; }
 * 
 * } } catch (Exception e) {
 * log.error("Got Exception while fetching User Details" + e); } finally {
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,stmt, connection);
 * 
 * } return isAccess;
 * 
 * }
 * 
 * public boolean isValidHr(String staffID) throws SQLException,
 * PropertyVetoException { // TODO Auto-generated method stub boolean isHr =
 * false; ResultSet rs = null; Connection connection=null; Statement statement =
 * null; try { connection = DBConnect.getConnection(); statement =
 * connection.createStatement(); StringBuffer queryString = new StringBuffer();
 * queryString.
 * append(" select count(*) from user_role ur , role_master rm,employee_master em "
 * ); queryString.append(" where rm.role='Hr' ");
 * queryString.append(" AND  rm.role_id=ur.role_id ");
 * queryString.append(" AND ur.User_ID=em.Staff_ID ");
 * queryString.append("AND em.user_id= '"); queryString.append(staffID + "'");
 * queryString.append(" AND rm.deleted_by is null "); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) isHr = true; } } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return isHr; }
 * 
 * 
 * 
 * public boolean isAccess(String staffID) throws SQLException,
 * PropertyVetoException { // TODO Auto-generated method stub boolean isAccess =
 * false; ResultSet rs = null; Connection connection=null; Statement statement =
 * null; try { connection = DBConnect.getConnection(); statement =
 * connection.createStatement(); StringBuffer queryString = new StringBuffer();
 * queryString.
 * append(" SELECT count(*) FROM rbac_master r, use_cases u,user_role ur ,employee_master em "
 * ); queryString.append("  WHERE em.user_id = '"); queryString.append( staffID
 * + "'"); queryString.append("  AND em.Staff_ID=ur.user_id ");
 * queryString.append(" AND ur.role_id=r.role_id  ");
 * queryString.append("  AND r.use_case_id = u.use_case_id "); queryString.
 * append(" AND (r.read_access='Y' AND r.write_access ='Y' AND r.delete_access ='Y' AND r.edit_access ='Y')  "
 * ); queryString.append(" AND r.deleted_by IS NULL ");
 * queryString.append(" AND u.deleted_by IS NULL "); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) isAccess = true; } } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return isAccess; }
 * 
 * 
 * public boolean isRMSAccess(String staffID) throws SQLException,
 * PropertyVetoException { // TODO Auto-generated method stub boolean isAccess =
 * false; ResultSet rs = null; Connection connection=null; Statement statement =
 * null; try { connection = DBConnect.getConnection(); statement =
 * connection.createStatement(); StringBuffer queryString = new StringBuffer();
 * queryString.
 * append(" SELECT count(*) FROM rbac_master r, use_cases u,user_role ur ,employee_master em "
 * ); queryString.append("  WHERE em.user_id = '"); queryString.append( staffID
 * + "'"); queryString.append("  AND em.Staff_ID=ur.user_id ");
 * queryString.append(" AND ur.role_id=r.role_id  ");
 * queryString.append("  AND r.use_case_id = u.use_case_id "); queryString.
 * append(" AND (r.read_access='Y' OR r.write_access ='Y' OR r.delete_access ='Y' OR r.edit_access ='Y')  "
 * ); queryString.append(" AND u.use_case_name='RMS Dashboard' ");
 * queryString.append(" AND r.deleted_by IS NULL ");
 * queryString.append(" AND u.deleted_by IS NULL "); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) isAccess = true; } } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return isAccess; }
 * 
 * public boolean isPINSAccrss(String userID) throws SQLException,
 * PropertyVetoException { boolean isAccess = false; ResultSet rs = null;
 * Connection connection=null; Statement statement = null; try { connection =
 * DBConnect.getConnection(); statement = connection.createStatement();
 * StringBuffer queryString = new StringBuffer(); queryString.
 * append(" SELECT count(*) FROM rbac_master r, use_cases u,user_role ur ,employee_master em "
 * ); queryString.append("  WHERE em.user_id = '"); queryString.append( userID +
 * "'"); queryString.append("  AND em.Staff_ID=ur.user_id ");
 * queryString.append(" AND ur.role_id=r.role_id  ");
 * queryString.append("  AND r.use_case_id = u.use_case_id "); queryString.
 * append(" AND (r.read_access='Y' OR r.write_access ='Y' OR r.delete_access ='Y' OR r.edit_access ='Y')  "
 * ); queryString.append(" AND u.use_case_name='PINS Dashboard' ");
 * queryString.append(" AND r.deleted_by IS NULL ");
 * queryString.append(" AND u.deleted_by IS NULL "); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) isAccess = true; } } finally {
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return isAccess; }
 * 
 * 
 * 
 * 
 * public boolean isProjectmanagercheck(String staffID) throws SQLException,
 * PropertyVetoException { // TODO Auto-generated method stub boolean
 * isProjectmanager = false; ResultSet rs = null; Connection connection=null;
 * Statement statement = null; try { connection = DBConnect.getConnection();
 * statement = connection.createStatement(); StringBuffer queryString = new
 * StringBuffer();
 * queryString.append(" select count(*) FROM employee_master em, ");
 * queryString.
 * append(" user_role AS ur, role_master AS rm where em.Staff_ID=ur.user_id And ur.role_id=rm.role_id  "
 * ); queryString.append(" And em.User_ID='"+staffID+"' ");
 * queryString.append(" and rm.role='Project Manager' "); rs =
 * statement.executeQuery(queryString.toString()); while (rs.next()) { if
 * (rs.getInt(1) > 0) isProjectmanager = true; } } catch (Exception e) {
 * log.error("Got Exception while fetching User Details" + e); } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return isProjectmanager; }
 * 
 * public boolean isResumebuildercheck(int staffID) throws SQLException,
 * PropertyVetoException { // TODO Auto-generated method stub boolean
 * isResumebuilder = false; ResultSet rs = null; Connection connection=null;
 * Statement statement = null; try { connection = DBConnect.getConnection();
 * statement = connection.createStatement(); StringBuffer queryString = new
 * StringBuffer();
 * queryString.append(" SELECT count(*)  FROM user_role AS ur, role_master ");
 * queryString.append(" AS rm WHERE ur.user_id ="
 * +staffID+" AND ur.role_id = rm.role_id "); queryString.
 * append(" AND ur.deleted_by IS NULL AND rm.deleted_by IS null And   rm.role='Resume Builder' "
 * ); rs = statement.executeQuery(queryString.toString()); while (rs.next()) {
 * if (rs.getInt(1) > 0) isResumebuilder = true; } } catch (Exception e) {
 * log.error("Got Exception while fetching User Details" + e); } finally {
 * 
 * DBConnectionHandlerUtil.closeDatabaseResource(rs,statement, connection); }
 * return isResumebuilder; }
 * 
 * }
 */