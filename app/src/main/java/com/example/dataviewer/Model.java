package com.example.dataviewer;

public class Model
{


    private  int  id;
    private String name;
    private String mail;
    private String contact_no;
    private String subOfMessege;
    private String messege;
    private String time;

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", subOfMessege='" + subOfMessege + '\'' +
                ", messege='" + messege + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getSubOfMessege() {
        return subOfMessege;
    }

    public void setSubOfMessege(String subOfMessege) {
        this.subOfMessege = subOfMessege;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Model(int id, String name, String mail, String contact_no, String subOfMessege, String messege, String time) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.contact_no = contact_no;
        this.subOfMessege = subOfMessege;
        this.messege = messege;
        this.time = time;
    }
}
