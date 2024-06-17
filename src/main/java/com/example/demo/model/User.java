package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private final String guid= UUID.randomUUID().toString();

    @Column(nullable = true, unique = false)
    private String image_id;

    @Column(nullable = true, unique = false)
    private String phone;

    @Column(nullable = true, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(nullable = false, unique = false)
    private Integer monthly_salary;

    @Column(nullable = false, unique = false)
    private Date salary_credit_date;

    @Column(nullable = false, unique = false)
    private Long account_balance;

    @Column(nullable = false, unique = false)
    private String hashed_password;

    // Constructors
    public User() {
    }

    public User(
        String name,
        String email,
        String username,
        String image_id,
        String phone,
        Integer monthly_salary,
        Date salary_credit_date,
        Long account_balance,
        String hashed_password
    ) {
        this.name = name;
        this.email = email;
        this.username=username;
        this.image_id=image_id;
        this.phone=phone;
        this.created_at= Calendar.getInstance().getTime();
        this.monthly_salary=monthly_salary;
        this.salary_credit_date=salary_credit_date;
        this.account_balance=account_balance;
        this.hashed_password=hashed_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", guid='" + guid + '\'' +
                ", image_id='" + image_id + '\'' +
                ", phone='" + phone + '\'' +
                ", created_at=" + created_at +
                ", monthly_salary=" + monthly_salary +
                ", salary_credit_date=" + salary_credit_date +
                ", account_balance=" + account_balance +
                ", hashed_password='" + hashed_password + '\'' +
                '}';
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGuid() {
        return guid;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Integer getMonthly_salary() {
        return monthly_salary;
    }

    public void setMonthly_salary(Integer monthly_salary) {
        this.monthly_salary = monthly_salary;
    }

    public Date getSalary_credit_date() {
        return salary_credit_date;
    }

    public void setSalary_credit_date(Date salary_credit_date) {
        this.salary_credit_date = salary_credit_date;
    }

    public Long getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(Long account_balance) {
        this.account_balance = account_balance;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }
}
