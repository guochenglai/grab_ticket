package net.gcl.ticket.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by guochenglai on 1/22/17.
 */
public class Passenger {
    private SimpleBooleanProperty checked = new SimpleBooleanProperty(false);
    private StringProperty name;
    private StringProperty type;
    private StringProperty typeName; //type=1表示是成人
    private StringProperty mobile;//电话号码
    private StringProperty email;//电子邮件
    private StringProperty idType;//
    private StringProperty idTypeName;//idType=1表示是二代身份证
    private StringProperty idNumber;//身份证
    private StringProperty birthday;//生日

    public void setChecked(boolean checked) {
        this.checked = new SimpleBooleanProperty(checked);
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    public void setTypeName(String typeName) {
        this.typeName = new SimpleStringProperty(typeName);
    }

    public void setMobile(String mobile) {
        this.mobile = new SimpleStringProperty(mobile);
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public void setIdType(String idType) {
        this.idType = new SimpleStringProperty(idType);
    }

    public void setIdTypeName(String idTypeName) {
        this.idTypeName = new SimpleStringProperty(idTypeName);
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = new SimpleStringProperty(idNumber);
    }

    public void setBirthday(String birthday) {
        this.birthday = new SimpleStringProperty(birthday);
    }

    public boolean isChecked() {
        return checked.get();
    }

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getTypeName() {
        return typeName.get();
    }

    public StringProperty typeNameProperty() {
        return typeName;
    }

    public String getMobile() {
        return mobile.get();
    }

    public StringProperty mobileProperty() {
        return mobile;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getIdType() {
        return idType.get();
    }

    public StringProperty idTypeProperty() {
        return idType;
    }

    public String getIdTypeName() {
        return idTypeName.get();
    }

    public StringProperty idTypeNameProperty() {
        return idTypeName;
    }

    public String getIdNumber() {
        return idNumber.get();
    }

    public StringProperty idNumberProperty() {
        return idNumber;
    }

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }
    @Override
    public String toString() {
        return name.get();

    }
}
