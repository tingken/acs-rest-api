/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.anemo;

/**
 * This class is a pojo to save the data from the login to Anemometer
 * system.
 */
public class LoginResult {
    public static class Data {
        private int needChangePassword;
        private int role;
        private String sysId = null;
        private String companyUId;
        private String label;
        private String passwordExt = null;
        private String token;
        private String password = null;
        private String des = null;
        private int userLevel;
        private String sysName = null;
        private String userUId;
        private String username;

        // Getter Methods 

        public int getNeedChangePassword() {
            return needChangePassword;
        }

        public int getRole() {
            return role;
        }

        public String getSysId() {
            return sysId;
        }

        public String getCompanyUId() {
            return companyUId;
        }

        public String getLabel() {
            return label;
        }

        public String getPasswordExt() {
            return passwordExt;
        }

        public String getToken() {
            return token;
        }

        public String getPassword() {
            return password;
        }

        public String getDes() {
            return des;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public String getSysName() {
            return sysName;
        }

        public String getUserUId() {
            return userUId;
        }

        public String getUsername() {
            return username;
        }

        // Setter Methods 

        public void setNeedChangePassword(int needChangePassword) {
            this.needChangePassword = needChangePassword;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public void setSysId(String sysId) {
            this.sysId = sysId;
        }

        public void setCompanyUId(String companyUId) {
            this.companyUId = companyUId;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setPasswordExt(String passwordExt) {
            this.passwordExt = passwordExt;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public void setSysName(String sysName) {
            this.sysName = sysName;
        }

        public void setUserUId(String userUId) {
            this.userUId = userUId;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    private int errcode;
    private String errmsg;
    Data DataObject = new Data();

    // Getter Methods 

    public int getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public Data getData() {
        return DataObject;
    }

    // Setter Methods 

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void setData(Data dataObject) {
        this.DataObject = dataObject;
    }
}
