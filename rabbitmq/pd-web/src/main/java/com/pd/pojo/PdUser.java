package com.pd.pojo;

import java.io.Serializable;
import java.util.Date;

public class PdUser implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_user.id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_user.username
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_user.password
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_user.phone
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_user.email
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_user.created
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_user.updated
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pd_user
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_user.id
     *
     * @return the value of pd_user.id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_user.id
     *
     * @param id the value for pd_user.id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_user.username
     *
     * @return the value of pd_user.username
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_user.username
     *
     * @param username the value for pd_user.username
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_user.password
     *
     * @return the value of pd_user.password
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_user.password
     *
     * @param password the value for pd_user.password
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_user.phone
     *
     * @return the value of pd_user.phone
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_user.phone
     *
     * @param phone the value for pd_user.phone
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_user.email
     *
     * @return the value of pd_user.email
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_user.email
     *
     * @param email the value for pd_user.email
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_user.created
     *
     * @return the value of pd_user.created
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_user.created
     *
     * @param created the value for pd_user.created
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_user.updated
     *
     * @return the value of pd_user.updated
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_user.updated
     *
     * @param updated the value for pd_user.updated
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_user
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}